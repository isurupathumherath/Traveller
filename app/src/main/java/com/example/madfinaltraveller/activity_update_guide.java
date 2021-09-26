package com.example.madfinaltraveller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class activity_update_guide extends AppCompatActivity {

    RecyclerView recyclerView1;
    UpdateAdapter mainadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_guide);

        recyclerView1 = (RecyclerView)findViewById(R.id.rv1);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Guide> options =
                new FirebaseRecyclerOptions.Builder<Guide>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Guide"), Guide.class)
                        .build();

        mainadapter = new UpdateAdapter(options);
        recyclerView1.setAdapter(mainadapter);
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

        mainadapter = new UpdateAdapter(options);
        mainadapter.startListening();
        recyclerView1.setAdapter(mainadapter);
    }


}