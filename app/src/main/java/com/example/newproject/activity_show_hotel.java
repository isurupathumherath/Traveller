package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class activity_show_hotel extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapterHotel mainAdapterHotel;
    Button addComment, viewComments;
    TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hotel);

        recyclerView = (RecyclerView)findViewById(R.id.rvAllHotel);
        addComment = (Button)findViewById(R.id.btnaddcomment);
        viewComments = (Button)findViewById(R.id.btnseecomment);
        tv = (TextView)findViewById(R.id.nametext);

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