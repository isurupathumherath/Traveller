package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_register_hotel extends AppCompatActivity {

    EditText txtName, txtType, txtAddress, txtCity, txtDistrict, txtProvince, txtLocationURL, txtService;
    Button btnSave;
    DatabaseReference dbRef;
    Hotel hotel;

    //Method to clear all user inputss
    private void clearControls() {
        txtName.setText("");
        txtType.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtDistrict.setText("");
        txtProvince.setText("");
        txtLocationURL.setText("");
        txtService.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_hotel);


        txtName = findViewById(R.id.editHotelName);
        txtAddress = findViewById(R.id.editHotelAddress);
        txtLocationURL = findViewById(R.id.editHotelLocationTag);
        txtService = findViewById(R.id.editHotelService);
        txtType = findViewById(R.id.editHotelType);
        txtCity = findViewById(R.id.editHotelCity);
        txtDistrict = findViewById(R.id.editHotelDistrict);

        System.out.println("TYPE:" + txtType);
        btnSave = findViewById(R.id.btnSave);

        hotel = new Hotel();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    public void save() {
        dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel");

        try{
            if(TextUtils.isEmpty(txtName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Hotel Name", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(txtAddress.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Hotel Address", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(txtLocationURL.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Hotel Location ", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(txtService.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Hotel Services ", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(txtType.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Hotel Services ", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(txtCity.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Hotel Services ", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(txtDistrict.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Hotel Services ", Toast.LENGTH_SHORT).show();

            else{
                hotel.setName(txtName.getText().toString().trim());
                hotel.setAddress(txtAddress.getText().toString().trim());
                hotel.setLocationURL(txtLocationURL.getText().toString().trim());
                hotel.setService(txtService.getText().toString().trim());
                hotel.setType(txtType.getText().toString().trim());
                hotel.setCity(txtCity.getText().toString().trim());
                hotel.setDistrict(txtDistrict.getText().toString().trim());

                dbRef.push().setValue(hotel);
//                dbRef.child("htl1").setValue(hotel);

                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_SHORT).show();
        }
    }
}