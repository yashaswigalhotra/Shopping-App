package com.example.startup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.startup.ui.Items;
import com.example.startup.ui.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.text.TextUtils.isEmpty;



public class login extends AppCompatActivity {

    private Object login;
    private Object create;
    EditText ed1, ed2;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        create = findViewById(R.id.create);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        ed1 = findViewById(R.id.editTextTextPersonName);
        ed2 = findViewById(R.id.editTextTextPassword);
        login =  findViewById(R.id.login2);
        ((View) login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(ed1.getText()) || isEmpty(ed2.getText()) ) {
                    Toast.makeText(login.this, "You must enter above credentials to LOG IN", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(login.this, ed1.getText(), Toast.LENGTH_SHORT).show();
                    getData();
                }
            }
        });

        ((View) create).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openSign();
            }
        });

    }

    private void getData(){

        databaseReference.child("users").child("-MLIXFxIWBinS4Qs6_2t").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users post = snapshot.getValue(Users.class);
                //Log.i("DataFromFirebase",post.getPrice()+""+post.getProduct());
                if(ed1.getText().toString().equals(post.getEmail()) && ed2.getText().toString().equals(post.getPassword())){
                    openLogin();
                }
                else{
                    Toast.makeText(login.this, "You must enter correct credentials to LOG IN", Toast.LENGTH_SHORT).show();
                    openLogin();
                }
                // System.out.println(post);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Couldn't find"+ error.getCode());
            }
        });
    }

    private void openLogin() {
        Intent intent = new Intent(this, thirdpage.class);
        intent.putExtra("message",ed1.getText().toString()) ;
        startActivity(intent);
    }

    private void openSign() {
        Intent intent = new Intent(this, secondpage.class);
        startActivity(intent);
    }

}