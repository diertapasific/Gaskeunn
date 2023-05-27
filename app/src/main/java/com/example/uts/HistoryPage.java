package com.example.uts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Map;

public class HistoryPage extends AppCompatActivity {

    RecyclerView recyclerView;
    SharedPreferences sp,sp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        // bottom navbar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_news:
                        startActivity(new Intent(HistoryPage.this, NewsPage.class));
                        return true;
                    case R.id.navigation_ticket:
                        startActivity(new Intent(HistoryPage.this, TicketPage.class));
                        return true;
                    case R.id.navigation_history:
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_history);

        // recyclerview
        sp = getSharedPreferences("loggedIn", MODE_PRIVATE);
        String loggedIn = sp.getString("loggedIn","");

        sp2 = getSharedPreferences(loggedIn+"cart", MODE_PRIVATE);

        Map<String, ?> allEntries = sp2.getAll();
        int count = allEntries.size();

        recyclerView = findViewById(R.id.rvHistory);
        ArrayList<History> histories = new ArrayList<>();
        for (int i=0; i<count;i++){
            String counter = Integer.toString(i);
            if (sp2.getString("name"+counter,"").equals("")){
                continue;
            }
            histories.add(new History(sp2.getString("name"+counter,"")
                    ,sp2.getString("q"+counter,"")
                    ,sp2.getString("date"+counter,"")));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HistoryAdapter(getApplicationContext(),histories));

    }
}