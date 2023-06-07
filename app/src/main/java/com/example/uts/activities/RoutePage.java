package com.example.uts.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.uts.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class RoutePage extends AppCompatActivity {
    Spinner from,to;
    ImageView map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_page);
        from = findViewById(R.id.fromSpinner);
        to = findViewById(R.id.toSpinner);
        map = findViewById(R.id.mapView);

        String[] fromStations = {"-", "Station A", "Station B", "Station C"};
        String[] toStations = {"-", "Station D", "Station E", "Station F"};

        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fromStations);
        fromAdapter.setDropDownViewResource(R.layout.spinner_item);
        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toStations);
        toAdapter.setDropDownViewResource(R.layout.spinner_item);

        from.setAdapter(fromAdapter);
        to.setAdapter(toAdapter);

        spinnerListener();

        // bottom navbar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_news:
                        startActivity(new Intent(RoutePage.this, NewsPage.class));
                        finish();
                        return true;
                    case R.id.navigation_ticket:
                        return true;
                    case R.id.navigation_history:
                        startActivity(new Intent(RoutePage.this, ProfilePage.class));
                        finish();
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_ticket);
    }

    private void spinnerListener() {
        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateImageView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected (if needed)
            }
        });

        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateImageView();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected (if needed)
            }
        });

    }
    private void updateImageView() {
        String selectedItem1 = (String) from.getSelectedItem();
        String selectedItem2 = (String) to.getSelectedItem();

        if (selectedItem1.equals("Station A") && selectedItem2.equals("Station D")) {
            map.setImageResource(R.drawable.atod);
        } else if (selectedItem1.equals("Station A") && selectedItem2.equals("Station E")) {
            map.setImageResource(R.drawable.atoe);
        } else if (selectedItem1.equals("Station A") && selectedItem2.equals("Station F")) {
            map.setImageResource(R.drawable.atof);
        } else if (selectedItem1.equals("Station B") && selectedItem2.equals("Station D")) {
            map.setImageResource(R.drawable.btod);
        }else if (selectedItem1.equals("Station B") && selectedItem2.equals("Station E")) {
            map.setImageResource(R.drawable.btoe);
        }else if (selectedItem1.equals("Station B") && selectedItem2.equals("Station F")) {
            map.setImageResource(R.drawable.btof);
        }else if (selectedItem1.equals("Station C") && selectedItem2.equals("Station D")) {
            map.setImageResource(R.drawable.ctod);
        }else if (selectedItem1.equals("Station C") && selectedItem2.equals("Station E")) {
            map.setImageResource(R.drawable.ctoe);
        }else if (selectedItem1.equals("Station C") && selectedItem2.equals("Station F")) {
            map.setImageResource(R.drawable.ctof);
        } else {
            map.setImageResource(R.drawable.not_available);
        }
    }
}