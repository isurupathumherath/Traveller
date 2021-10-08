package com.example.madfinaltraveller;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

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

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search22);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query );
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<Hotel> options =
                new FirebaseRecyclerOptions.Builder<Hotel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel").orderByChild("name").startAt(str).endAt(str+"~"), Hotel.class)
                        .build();

        hotelUpdateAdapter = new hotelUpdateAdapter(options);
        hotelUpdateAdapter.startListening();
        recyclerView.setAdapter(hotelUpdateAdapter);
    }
}