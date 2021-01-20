package com.example.startup.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.startup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp extends Fragment {
    private FirebaseAuth mAuth;
    private EditText otpno;
    private Button btnOTP;
    private String mVerificationId;
    public String phone;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.otp, container, false);
        String phoneNumber=getArguments().getString("Phone");
        Log.i("PhoneNumber",phoneNumber+"%%%0");
        otpno = v.findViewById(R.id.otpno);
        btnOTP=v.findViewById(R.id.btnotp);
        mAuth = FirebaseAuth.getInstance();
        getOTP();
        return v;
    }

    public void getOTP() {
        if(btnOTP.getText().toString().equalsIgnoreCase("Get Otp")) {
            otpno.setVisibility(View.VISIBLE);
            btnOTP.setText("verify");
        //    mAuth = FirebaseAuth.getInstance();
//            phone = getArguments().getString("1");
            sendVerificationCode("9463882213");

        }else if(btnOTP.getText().toString().equalsIgnoreCase("verify"))
        {
            verifyVerificationCode(otpno.getText().toString());
        }
    }

    public void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                getActivity(),
                mCallbacks);
    }
    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
//                editTextCode.setText(code);
                //verifying the code
                //verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.i("Error",e+"");
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };


    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity

                            Toast.makeText(getActivity(), "Successfull", Toast.LENGTH_SHORT).show();
                            //  Intent i=new Intent(Firebase_activity.this,SaveRetriveDataF.class);
                            // i.putExtra("Phone","+91"+phone.getText().toString());
                            // startActivity(i);


                        } else {
                            Toast.makeText(getActivity(), "Please enter valid otp.", Toast.LENGTH_SHORT).show();

                            //verification unsuccessful.. display an error message

                        }
                    }
                });
    }

}