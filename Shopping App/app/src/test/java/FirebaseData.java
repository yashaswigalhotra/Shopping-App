/*package com.example.startup.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationbar.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseData extends AppCompatActivity {

    private String phone = "9815415673";
    private EditText name;
    private TextView userNamePhone;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_retrive_data_f);


        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        name = findViewById(R.id.name);
        userNamePhone = findViewById(R.id.rname);
        Intent i = getIntent();
//        phone=i.getExtras().getString("Phone");


    }

    public void savedata(View view) {
        writeNewUser(count++, name.getText().toString(), phone);
        getClause();
    }

    private void writeNewUser(int userId, String name, String phone) {
        Users user = new Users(name, phone);

        mDatabaseReference.child("users").child(userId + "").setValue(user);
    }

    private void getData() {
        mDatabaseReference.child("users").child("0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users post = dataSnapshot.getValue(Users.class);
                userNamePhone.setText(post.phone + "@@@" + post.username);
                System.out.println(post);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private void getAllData() {
        mDatabaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Users post = snapshot.getValue(Users.class);
                    userNamePhone.setText(userNamePhone.getText().toString()+post.phone + "@@@" + post.username);
                    System.out.println(post);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private void getClause(){
        Query query = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("username").equalTo("aastha");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    Users users = childSnapshot.getValue(Users.class);

                    if(users.getUsername().equalsIgnoreCase("aastha")) {
                        // Here is your desired location
                        userNamePhone.setText(userNamePhone.getText().toString()+"\n"+users.getUsername()+"");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}

 */