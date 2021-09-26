package com.example.madfinaltraveller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class activity_sample_landing_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_landing_page);
    }

    public void goToAddHotelActivity(View view) {
        Intent intent = new Intent(this, activity_register_hotel.class);
        startActivity(intent);
    }

    public void goToUpdateActivity(View view) {
        Intent intent = new Intent(this, activity_update_hotel_layout.class);
        startActivity(intent);
    }

    public void goToShowHotelActivity(View view) {
        Intent intent = new Intent(this, activity_show_hotel.class);
        startActivity(intent);
    }

}