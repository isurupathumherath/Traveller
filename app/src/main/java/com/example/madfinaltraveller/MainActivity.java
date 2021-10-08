package com.example.madfinaltraveller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String usern;

    RecyclerView recyclerView;
    com.example.madfinaltraveller.MainAdapter mainadapter;
    Button addComment, viewComments;
    TextView tv ;
    String stringname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        addComment = (Button)findViewById(R.id.btnaddcomment);
        viewComments = (Button)findViewById(R.id.btnseecomment);
        Intent intent=getIntent();
        usern=intent.getStringExtra("un");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Guide> options =
                new FirebaseRecyclerOptions.Builder<Guide>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Guide"), Guide.class)
                        .build();

        mainadapter = new com.example.madfinaltraveller.MainAdapter(options);
        recyclerView.setAdapter(mainadapter);

        drawerLayout=findViewById(R.id.constraint_layout2);
        navigationView=findViewById(R.id.nav_view2);
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
        mainadapter.startListening();

    }


    @Override
    protected void onStop() {
        super.onStop();
        mainadapter.stopListening();
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
        FirebaseRecyclerOptions<Guide> options =
                new FirebaseRecyclerOptions.Builder<Guide>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Guide").orderByChild("name").startAt(str).endAt(str+"~"), Guide.class)
                        .build();

        mainadapter = new com.example.madfinaltraveller.MainAdapter(options);
        mainadapter.startListening();
        recyclerView.setAdapter(mainadapter);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.homepage:
                Intent i2=new Intent(this,ClientHomenew.class);
                i2.putExtra("un",usern);
                startActivity(i2);
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
                break;
            case R.id.viewtaxi:
                Intent i6 = new Intent(this, activity_client_taxi.class);
                i6.putExtra("un",usern);
                startActivity(i6);
                break;
        }
        return true;
    }


}