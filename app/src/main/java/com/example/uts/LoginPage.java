package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
            editor.putString("mae","mae");
            editor.putString("mae"+"pass","123");
            editor.apply();
        }

        setListener();
    }

    private void setListener(){
        loginButton.setOnClickListener(e -> {
            if (emailTextField.getText().toString().equals(sp.getString(emailTextField.getText().toString(),""))
                    && passwordTextField.getText().toString().equals(sp.getString(emailTextField.getText().toString()+"pass",""))){
                showToast("Login Success");
                Intent intent = new Intent(this, NewsPage.class);
                intent.putExtra("loggedIn",emailTextField.getText().toString());
                startActivity(intent);
            }
            else {
                showToast("Login Failed");
            }
        });

        signUpHereText.setOnClickListener(e -> {
            Intent intent = new Intent(this, RegisterPage.class);
            startActivity(intent);
        });
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}