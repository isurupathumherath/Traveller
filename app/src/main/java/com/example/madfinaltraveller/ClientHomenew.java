package com.example.madfinaltraveller;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class ClientHomenew extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String usern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_homenew);

        Intent intent=getIntent();
        usern=intent.getStringExtra("username");

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    public void moveGuide(View view){
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("un",usern);
        startActivity(i);
    }
//
    public void showall(View view){
        Intent i=new Intent(this,activity_show_hotel.class);
        i.putExtra("un",usern);
        startActivity(i);
    }

    public void taxi(View view){
        Intent i=new Intent(this,activity_client_taxi.class);
        i.putExtra("un",usern);
        startActivity(i);
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
                break;
            case R.id.profile:
                Intent in=new Intent(this,updateUser.class);
                in.putExtra("un",usern);
                startActivity(in);
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