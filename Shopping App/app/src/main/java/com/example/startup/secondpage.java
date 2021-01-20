package com.example.startup;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.startup.ui.otp;

public class secondpage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondpage);

        //loadFragment(new sign());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.sign, new sign()).commit();
        }
    }

    //    public boolean loadFragment(Fragment fragment){
//        if(fragment != null){
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.sign,fragment)
//                    .commit();
//            return true;
//        }
//        return false;
//    }
    public void goToSecondFragment(String value) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment secondFragment = new otp();
        Bundle args=new Bundle();
        args.putString("Phone",value);
        secondFragment.setArguments(args);
        ft.replace(R.id.sign, secondFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }
}

