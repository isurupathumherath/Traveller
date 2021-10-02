package com.example.madfinaltraveller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class activity_admin_taxi extends AppCompatActivity {

    RecyclerView recyclerView;
    Admin_adapter admin_adapter;

    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taxi_admin_main);

        recyclerView=(RecyclerView)findViewById(R.id.RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<TaxiModel> options =
                new FirebaseRecyclerOptions.Builder<TaxiModel>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Taxiis"),TaxiModel.class)
                    .build();

        admin_adapter= new Admin_adapter(options);
        recyclerView.setAdapter(admin_adapter);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Add_taxi.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        admin_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        admin_adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.searchtaxi,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });

        return  super.onCreateOptionsMenu(menu);
    }

    private  void txtSearch(String str)
    {

        FirebaseRecyclerOptions<TaxiModel> options =
                new FirebaseRecyclerOptions.Builder<TaxiModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Taxiis").orderByChild("TaxiId").startAt(str).endAt(str+"~"),TaxiModel.class)
                        .build();
        admin_adapter=new Admin_adapter(options);
        admin_adapter.startListening();
        recyclerView.setAdapter(admin_adapter);

    }

}