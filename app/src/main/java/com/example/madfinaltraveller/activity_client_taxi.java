package com.example.madfinaltraveller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class activity_client_taxi extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String usern;

    RecyclerView recyclerView;
    taxi_client_adapter taxi_clientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_taxi);

        recyclerView=(RecyclerView) findViewById(R.id.RV_client);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent=getIntent();
        usern=intent.getStringExtra("un");

        FirebaseRecyclerOptions<TaxiModel> options =
                new FirebaseRecyclerOptions.Builder<TaxiModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Taxiis"),TaxiModel.class)
                        .build();

        taxi_clientAdapter = new taxi_client_adapter(options);
        recyclerView.setAdapter(taxi_clientAdapter);


        drawerLayout=findViewById(R.id.constraint_layout5);
        navigationView=findViewById(R.id.nav_view5);
        toolbar=findViewById(R.id.toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        taxi_clientAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        taxi_clientAdapter.stopListening();
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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Taxiis").orderByChild("AvaArea").startAt(str).endAt(str+"~"),TaxiModel.class)
                        .build();
        taxi_clientAdapter=new taxi_client_adapter(options);
        taxi_clientAdapter.startListening();
        recyclerView.setAdapter(taxi_clientAdapter);

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.homepage:
                Intent i2=new Intent(this,ClientHomenew.class);
                startActivity(i2);
                break;
            case R.id.profile:
                Intent in=new Intent(this,updateUser.class);
                in.putExtra("un",usern);
                startActivity(in);
                break;
            case R.id.log_o:
                Intent i0=new Intent(this,Loginpage.class);
                startActivity(i0);
                break;
            case R.id.viewHotel:
                Intent i = new Intent(this, activity_show_hotel.class);
                startActivity(i);
                break;
            case R.id.viewguide:
                Intent i1 = new Intent(this, MainActivity.class);
                startActivity(i1);
                break;
            case R.id.viewtaxi:
                Intent i6 = new Intent(this, activity_client_taxi.class);
                startActivity(i6);
                break;
        }
        return true;
    }


}