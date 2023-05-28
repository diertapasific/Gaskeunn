package com.example.uts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts.model.NewsApiResponse;
import com.example.uts.model.NewsHeadlines;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class NewsPage extends AppCompatActivity implements SelectListener{

    RecyclerView recyclerView;
    SharedPreferences sp;
    NewsAdapter adapter;
    ProgressDialog dialog;

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
                        startActivity(new Intent(NewsPage.this, ProfilePage.class));
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_news);

        dialog = new ProgressDialog(this);
        dialog.show();

        // recyclerview
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener);

    }
    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new NewsAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(this, NewsDetailPage.class).putExtra("data", headlines));
    }
}