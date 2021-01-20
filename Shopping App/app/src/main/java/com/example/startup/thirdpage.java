package com.example.startup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.startup.ui.Payment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class thirdpage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


//    private Button b_help;
//    private Button b_cart;
//    private Button b_home;
//    private Button b_scan;
//    TextView t;
//    ArrayList<String> arraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdpage);

//        arraylist=new ArrayList<>();

//        Intent i=getIntent();
//        String message=i.getExtras().getString("message");
//        t=findViewById(R.id.textView9);
//        t.setText("Hi " +message.toUpperCase()+ ", Welcome to ShopVite. We wish you safe and comfortable shopping experience " );

//        loadFragment(new home());

     BottomNavigationView navigation = findViewById(R.id.botton_nav);
        navigation.setOnNavigationItemSelectedListener(this);



        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new home()).commit();
        }
    }



    private boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public boolean onNavigationItemSelected(MenuItem item){
        Fragment fragment = null;
        switch(item.getItemId()){
            case R.id.home:
                fragment = new home();
                break;
            case R.id.cart:
                fragment = new cart();
                break;
            case R.id.help:
                fragment = new help();
                break;
        }
        return loadFragment(fragment);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){

            case R.id.profile:
                Toast.makeText(this,"Profile",Toast.LENGTH_LONG).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"Settings",Toast.LENGTH_LONG).show();
                break;
            case R.id.logout:
                Toast.makeText(this,"Thank for shopping with us",Toast.LENGTH_LONG).show();
                finish();
                break;
        }
        return true;
    }






//    public void scanCode(){
//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.setCaptureActivity(CaptureAct.class);
//        integrator.setOrientationLocked(false);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//        integrator.setPrompt("scanning code");
//        integrator.initiateScan();
//    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null) {
//            if (result.getContents() == null) {
//                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
//            } else {
//                try {
//
//                    //org.json.JSONException:Sampler.Value 1234567890128 of type java.lang.Long cannot be converted to JSONObject
//                    //converting the data to json
//                    Log.i("BarcodeValue",result.getContents()+"");
//                    // JSONObject obj = new JSONObject(result.getContents());
//                    //Log.i("BarcodeValue",obj.getString("id")+"*************");
//                    //Toast.makeText(this, obj.getString("id")+"******************", Toast.LENGTH_LONG).show();
//                    t.setText(t.getText() +"\n"+ result.getContents()+"");
//                    arraylist.add(result.getContents());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.i("error",e+"");
//                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
//                }
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }

}
