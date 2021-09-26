package com.example.madfinaltraveller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class activity_update_hotel_layout extends AppCompatActivity {

    RecyclerView recyclerView;
    hotelUpdateAdapter hotelUpdateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hotel_layout);

        recyclerView = (RecyclerView)findViewById(R.id.rvUpdateHotel);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Hotel> options =
                new FirebaseRecyclerOptions.Builder<Hotel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel"), Hotel.class)
                        .build();

        hotelUpdateAdapter = new hotelUpdateAdapter(options);
        recyclerView.setAdapter(hotelUpdateAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        hotelUpdateAdapter.startListening();

    }


    @Override
    protected void onStop() {
        super.onStop();
        hotelUpdateAdapter.stopListening();
    }
}