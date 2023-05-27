package com.example.uts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class TicketPage extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_page);

        // bottom navbar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_news:
                        startActivity(new Intent(TicketPage.this, NewsPage.class));
                        return true;
                    case R.id.navigation_ticket:
                        return true;
                    case R.id.navigation_history:
                        startActivity(new Intent(TicketPage.this, HistoryPage.class));
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_ticket);

        // recyclerview
        recyclerView = findViewById(R.id.rvTicket);
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket("Dirt Derby Dash","40USD",R.drawable.ticket1));
        tickets.add(new Ticket("Rock Rumble Rally","50USD",R.drawable.ticket2));
        tickets.add(new Ticket("Cliffside Challenge","80USD",R.drawable.ticket3));
        tickets.add(new Ticket("Trail Throwdown","30USD",R.drawable.ticket4));
        tickets.add(new Ticket("Mountain Madness Mayhem","60USD",R.drawable.ticket5));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TicketAdapter(getApplicationContext(),tickets));
    }
}