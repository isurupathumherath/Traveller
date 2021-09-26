package com.example.madfinaltraveller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class activity_show_hotel extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapterHotel mainAdapterHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hotel);

        recyclerView = (RecyclerView)findViewById(R.id.rvAllHotel);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Hotel> options =
                new FirebaseRecyclerOptions.Builder<Hotel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel"), Hotel.class)
                        .build();

        mainAdapterHotel = new MainAdapterHotel(options);
        recyclerView.setAdapter(mainAdapterHotel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapterHotel.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mainAdapterHotel.stopListening();
    }
}