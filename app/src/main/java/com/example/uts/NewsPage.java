package com.example.uts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class NewsPage extends AppCompatActivity {

    RecyclerView recyclerView;
    SharedPreferences sp;
    ImageButton logoutBtn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sp = getSharedPreferences("loggedIn", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("loggedIn",extras.getString("loggedIn"));
            editor.apply();
        }

        // bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_news:
                        return true;
                    case R.id.navigation_ticket:
                        startActivity(new Intent(NewsPage.this, TicketPage.class));
                        return true;
                    case R.id.navigation_history:
                        startActivity(new Intent(NewsPage.this, HistoryPage.class));
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_news);

        // recyclerview
        recyclerView = findViewById(R.id.rvNews);
        ArrayList<News> news = new ArrayList<>();
        news.add(new News("Richard won again!","30 April 2023",R.drawable.news1));
        news.add(new News("Richard won first place!","20 April 2023",R.drawable.news2));
        news.add(new News("Highest jump ever?","18 March 2023",R.drawable.news3));
        news.add(new News("Fun in the city!","08 February 2023",R.drawable.news4));
        news.add(new News("Two winner! How?","07 January 2023",R.drawable.news5));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NewsAdapter(getApplicationContext(),news));

        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
        });

    }
}