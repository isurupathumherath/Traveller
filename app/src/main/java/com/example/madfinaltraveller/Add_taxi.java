package com.example.madfinaltraveller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Add_taxi extends AppCompatActivity {

    EditText drivername,contactno,vehicleno,taxiid,avaarea,perkm,profurl;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_taxi);

        drivername=(EditText) findViewById(R.id.et_DriverName);
        contactno=(EditText) findViewById(R.id.et_ContactNo);
        vehicleno=(EditText) findViewById(R.id.et_VehicleNo);
        taxiid=(EditText) findViewById(R.id.et_TaxiId);
        avaarea=(EditText) findViewById(R.id.et_AvaArea);
        perkm=(EditText) findViewById(R.id.et_PerKm);
        profurl=(EditText) findViewById(R.id.et_ProfURL);

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnBack=(Button) findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });


//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//
//            }
//        });
    }


    private void insertData()
    {

        Map<String,Object> map=new HashMap<>();
        map.put("DriverName",drivername.getText().toString());
        map.put("ContactNo",contactno.getText().toString());
        map.put("VehicleNo",vehicleno.getText().toString());
        map.put("TaxiId",taxiid.getText().toString());
        map.put("AvaArea",avaarea.getText().toString());
        map.put("PerKm",perkm.getText().toString());
        map.put("profURL",profurl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Taxiis").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Add_taxi.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(Add_taxi.this, "Error while adding data", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void clearAll()
    {
        drivername.setText("");
        contactno.setText("");
        vehicleno.setText("");
        taxiid.setText("");
        avaarea.setText("");
        perkm.setText("");
        profurl.setText("");
    }


}