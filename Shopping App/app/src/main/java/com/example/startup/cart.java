package com.example.startup;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.startup.ui.Items;
import com.example.startup.ui.Users;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;

public class cart extends Fragment {
    //    TextView t;
  ArrayList<String> arraylist;
//
//    TextView a;
//    TextView b;
//    TextView c;
//    TextView d;
//    TextView sum;
//    String total;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private SurfaceView surfaceView;
    private BarcodeDetector barcodeDectector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    //This class provides methods to play DTMF tones
    private ToneGenerator toneGen1;
    private TextView barcodeText;
    private String barcodeData;
    private Object payment;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_cart, container, false);
        toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC,     100);
        surfaceView = v.findViewById(R.id.surface_view);
        barcodeText = v.findViewById(R.id.barcode_text);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        intiliseDectectorsAndSources();
        arraylist=new ArrayList<>();
        payment = v.findViewById(R.id.payment);
//        a = v.findViewById(R.id.a);
//        b = findViewById(R.id.b);
//        c = findViewById(R.id.c);
//        d = findViewById(R.id.d);
//        sum = findViewById(R.id.sum);
//        new AsyncTaskRunner().execute();

        return v;


        //Toolbar toolbar = getView().findViewById(R.id.toolbar);

    }
  private void intiliseDectectorsAndSources(){

      BarcodeDetector barcodeDectector = new BarcodeDetector.Builder(getActivity()).setBarcodeFormats(Barcode.ALL_FORMATS).build();
        cameraSource = new CameraSource.Builder(getActivity(),barcodeDectector).setRequestedPreviewSize(1920,1080).setAutoFocusEnabled(true).build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try{
                    if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                        cameraSource.start(surfaceView.getHolder());
                    }else{
                        ActivityCompat.requestPermissions(getActivity(),new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (Exception e) {
                    Log.i("Error",e+"");
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        barcodeDectector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcode = detections.getDetectedItems();
                if(barcode.size() != 0){
                    barcodeText.post(new Runnable() {

                        @Override
                        public void run() {

                            if (barcode.valueAt(0).email != null) {
                             barcodeText.removeCallbacks(null);
                                barcodeData = barcode.valueAt(0).email.address.toString();
                               // arraylist.add(barcodeData);
                                //barcodeText.setText((CharSequence) arraylist);
                              //  barcodeText.setText(barcodeData);
                                getData();
                                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
                            } else {
                                barcodeData = barcode.valueAt(0).displayValue;
                                barcodeText.setText(barcodeData);
                                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);

                            }
                        }
                    });

                }

                getData();
            }
        });
    }
    int count =0;
    private void getData(){
       // barcodeData="0";

        databaseReference.child("Barcode").child(barcodeData).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Items post = snapshot.getValue(Items.class);
                Log.d("DataFromFirebase",post.getPrice()+""+post.getProduct());
                barcodeText.setText(1  +"   "+ post.getProduct() + "           " + post.getPrice());
               // System.out.println(post);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Couldn't find"+ error.getCode());
            }
        });
    }
/*
    private void getAllData(){
            databaseReference.child("Barcode").addValueEventListener(new ValueEventListener(){
                public void onDataChange(DataSnapshot dataSnapshot){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        if(barcodeData == dataSnapshot.toString()){
                            Items post = snapshot.getValue(Items.class);
                            barcodeText.setText(barcodeText.getText().toString() + post.getProduct() + "@@@" + post.getPrice() );
                            System.out.println(post);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("The read failed"+ error.getCode());
                }
            });
    }


 */

}










//    public void scanCode(){
//        IntentIntegrator integrator = new IntentIntegrator(getActivity());
//        integrator.setCaptureActivity(CaptureAct.class);
//        integrator.setOrientationLocked(false);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//        integrator.setPrompt("scanning code");
//        integrator.initiateScan();
//    }
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null) {
//            if (result.getContents() == null) {
//                Toast.makeText(getActivity(), "Result Not Found", Toast.LENGTH_LONG).show();
//            } else {
//                try {
//                    arraylist = new ArrayList<String>();
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
//                    Toast.makeText(getActivity(), result.getContents(), Toast.LENGTH_LONG).show();
//                }
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
//}