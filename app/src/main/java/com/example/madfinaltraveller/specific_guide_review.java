package com.example.madfinaltraveller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class specific_guide_review extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener   {

    RecyclerView recyclerView;
    TextView tv;
    String str;
    String usern;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    com.example.madfinaltraveller.specific_guide_adapter guide_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_guide_review);


        recyclerView = (RecyclerView)findViewById(R.id.srv);
        tv = (TextView)findViewById(R.id.textView25);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        str = intent.getStringExtra("name");
        tv.setText(str);

        Intent intent2=getIntent();
        usern=intent2.getStringExtra("un");


        /*nav*/
        drawerLayout=findViewById(R.id.drawer_layout7);
        navigationView=findViewById(R.id.nav_view7);
        toolbar=findViewById(R.id.toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        /*nav*/

        FirebaseRecyclerOptions<Reviews> options =
                new FirebaseRecyclerOptions.Builder<Reviews>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Reviews").orderByChild("username").equalTo(str), Reviews.class)
                        .build();

        guide_adapter = new com.example.madfinaltraveller.specific_guide_adapter(options);
        recyclerView.setAdapter(guide_adapter);
    }

    protected void onStart() {
        super.onStart();
        guide_adapter.startListening();

    }


    @Override
    protected void onStop() {
        super.onStop();
        guide_adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.homepage:
                Intent i9=new Intent(this,ClientHomenew.class);
                i9.putExtra("un",usern);
                startActivity(i9);
                break;
            case R.id.profile:
                break;
            case R.id.log_o:
                Intent i0=new Intent(this,Loginpage.class);
                i0.putExtra("un",usern);
                startActivity(i0);
                break;
            case R.id.viewHotel:
                Intent i = new Intent(this, activity_show_hotel.class);
                i.putExtra("un",usern);
                startActivity(i);
                break;
            case R.id.viewguide:
                Intent i1 = new Intent(this, MainActivity.class);
                i1.putExtra("un",usern);
                startActivity(i1);
                break;
            case R.id.viewtaxi:
                break;
        }
        return true;
    }
}