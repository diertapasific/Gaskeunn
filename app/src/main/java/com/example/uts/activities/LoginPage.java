package com.example.uts.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uts.R;

import java.util.Map;

public class LoginPage extends AppCompatActivity {

    private EditText emailTextField, passwordTextField;
    private Button loginButton;
    private TextView signUpHereText;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        initialize();
    }

    private void initialize(){
        emailTextField = findViewById(R.id.emailLoginField);
        passwordTextField = findViewById(R.id.passwordLoginField);
        loginButton = findViewById(R.id.signInButton);
        signUpHereText = findViewById(R.id.signUpHere);

        sp = getSharedPreferences("UserInfo", MODE_PRIVATE);

        Map<String, ?> allEntries = sp.getAll();
        if (allEntries.isEmpty()) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("mae.com","mae.com");
            editor.putString("mae.com"+"pass","123");
            editor.putString("mae.com"+"name","MaeChuuya");
            editor.apply();
        }

        setListener();
    }

    private void setListener(){
        loginButton.setOnClickListener(e -> {
            String email = emailTextField.getText().toString();
            String password = passwordTextField.getText().toString();

            if (isInputValid(email, password)){
                showToast("Login Success");
                startNewspage();
            }else {
                showToast("Login Failed");
            }
        });

        signUpHereText.setOnClickListener(e -> {
            startRegisterPage();
        });
    }

    private boolean isInputValid(String email, String password){
        String sharedPreferencesEmail = sp.getString(email, "");
        String sharedPreferencesPassword = sp.getString(email + "pass", "");

        return email.equals(sharedPreferencesEmail) && password.equals(sharedPreferencesPassword);
    }

    private void startNewspage(){
        Intent intent = new Intent(this, NewsPage.class);
        intent.putExtra("loggedIn",emailTextField.getText().toString());
        startActivity(intent);
        finish();
    }

    private void startRegisterPage(){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}