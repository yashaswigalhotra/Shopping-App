package com.example.startup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.startup.ui.Users;
import com.example.startup.ui.otp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.sql.DatabaseMetaData;

import static android.text.TextUtils.isEmpty;

public class sign extends Fragment {
    private Button b_signup;
    public EditText ed1, ed2,ed3,ed4, phoneno;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    int count = 0;

    private static String value;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.activity_sign, container, false);

        ed1 = v.findViewById(R.id.editTextTextPersonName2);
        ed2 = v.findViewById(R.id.username2);
        ed3 = v.findViewById(R.id.editTextTextPassword3);
        ed4 = v.findViewById(R.id.editTextTextPassword4);
        phoneno = v.findViewById(R.id.phoneno);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        //value = phoneno.getText().toString();

        b_signup = v.findViewById(R.id.b_signup);
        b_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(ed1.getText()) || isEmpty(ed2.getText()) || isEmpty(ed3.getText()) || isEmpty(ed4.getText())){
                    Toast.makeText(getActivity(), "You must enter above credentials to SIGN IN", Toast.LENGTH_SHORT).show();
                }
                else if(!ed3.getText().toString().equalsIgnoreCase(ed4.getText().toString())){
                    Toast.makeText(getActivity(), "Your confirm password doesn't match", Toast.LENGTH_SHORT).show();
                }
                else{
                    savedata();

                }
            }
        });

        return v;
    }

    public void savedata() {
        writeNewUser(count++,ed2.getText().toString(), ed1.getText().toString(), phoneno.getText().toString(), ed3.getText().toString());
        //getClause();
    }
    private void writeNewUser(int userId, String name, String email, String phone, String password){
        Users users = new Users(name,email,phone,password);
        databaseReference.child("users").push().setValue(users);
//          otp ldf = new otp();
//        Bundle args = new Bundle();
//        args.putString("1", phoneno.getText().toString());
//        ldf.setArguments(args);
        secondpage activity = (secondpage)getActivity();
        activity.goToSecondFragment("9815415673");
    }

   /* public void getClause(){
        Query query = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("barcodes").equalTo("")
    }

    */




/*
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.activity_sign);

        b_signup = v.findViewById(R.id.b_signup);
        ((View) b_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(ed1.getText()) || isEmpty(ed2.getText()) || isEmpty(ed3.getText()) || isEmpty(ed4.getText())){
                    Toast.makeText(sign.this, "You must enter above credentials to SIGN IN", Toast.LENGTH_SHORT).show();
                }
                else if(!ed3.getText().toString().equalsIgnoreCase(ed4.getText().toString())){
                    Toast.makeText(sign.this, "Your confirm password doesn't match", Toast.LENGTH_SHORT).show();
                }
                else{
                    openthird();
                }
            }
        });
        ed1 = findViewById(R.id.editTextTextPersonName2);
        ed2 = findViewById(R.id.username2);
        ed3 = findViewById(R.id.editTextTextPassword3);
        ed4 = findViewById(R.id.editTextTextPassword4);
    }
    private void openthird() {
        Intent intent = new Intent(this, thirdpage.class);
        intent.putExtra("message",ed2.getText().toString()) ;
        startActivity(intent);
    }

     */

}