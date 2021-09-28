package com.example.madfinaltraveller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class newAdminhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_adminhome);
    }

    public void moveGuide(View view){
        Intent i=new Intent(this,activity_update_guide.class);
        startActivity(i);
    }

    public void all(View view){
        Intent i=new Intent(this,Allusers.class);
        startActivity(i);
    }

    public void UpdateHotel(View view){
        Intent i=new Intent(this,activity_update_hotel.class);
        startActivity(i);
    }

    public void hotel_redirect(View view){
        Intent i=new Intent(this,activity_sample_landing_page.class);
        startActivity(i);
    }
}