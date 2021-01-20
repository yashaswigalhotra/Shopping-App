package com.example.startup;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class home extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.home, container, false);
        Toolbar tb = v.findViewById(R.id.tool);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);
        return v;

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


}