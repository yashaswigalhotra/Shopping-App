package com.example.startup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button b_login;
    private Button b_Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_login =  findViewById(R.id.b_login);
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
        b_Signup = findViewById(R.id.b_Signup);
        b_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignup();
            }
        });
    }

    private void openLogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
    private void openSignup() {
        Intent intent = new Intent(this, secondpage.class);
        startActivity(intent);
    }
    public void browser1(View view){
        Intent browserIntent =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://smartshopvite.zyrosite.com"));
        startActivity(browserIntent);
    }
}