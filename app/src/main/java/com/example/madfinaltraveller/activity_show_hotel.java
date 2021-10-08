package com.example.madfinaltraveller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class activity_show_hotel extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String usern;
    RecyclerView recyclerView;
    MainAdapterHotel mainAdapterHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hotel);

        recyclerView = (RecyclerView)findViewById(R.id.rvAllHotel);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent=getIntent();
        usern=intent.getStringExtra("un");

        FirebaseRecyclerOptions<Hotel> options =
                new FirebaseRecyclerOptions.Builder<Hotel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel"), Hotel.class)
                        .build();

        mainAdapterHotel = new MainAdapterHotel(options);
        recyclerView.setAdapter(mainAdapterHotel);

        drawerLayout=findViewById(R.id.constraint_layout3);
        navigationView=findViewById(R.id.nav_view3);
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
        mainAdapterHotel.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mainAdapterHotel.stopListening();
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
                break;
        }
        return true;
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

        mainAdapterHotel = new MainAdapterHotel(options);
        mainAdapterHotel.startListening();
        recyclerView.setAdapter(mainAdapterHotel);
    }
}