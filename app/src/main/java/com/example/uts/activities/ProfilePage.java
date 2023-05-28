package com.example.uts.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.uts.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfilePage extends AppCompatActivity {
    SharedPreferences sp,sp2;
    TextView name, email;
    Button logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // bottom navbar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_news:
                        startActivity(new Intent(ProfilePage.this, NewsPage.class));
                        return true;
                    case R.id.navigation_ticket:
                        startActivity(new Intent(ProfilePage.this, RoutePage.class));
                        return true;
                    case R.id.navigation_history:
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_history);

        sp = getSharedPreferences("loggedIn", MODE_PRIVATE);
        sp2 = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String loggedIn = sp.getString("loggedIn","");

        String loggedInName = sp2.getString(loggedIn+"name","");
        String loggedInMail = sp2.getString(loggedIn,"");

        name = findViewById(R.id.profileName);
        email = findViewById(R.id.profileMail);
        logoutBtn = findViewById(R.id.logoutBtn);

        name.setText(loggedInName);
        email.setText(loggedInMail);

        logoutBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
        });

    }
}