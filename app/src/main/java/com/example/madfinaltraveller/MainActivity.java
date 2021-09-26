package com.example.madfinaltraveller;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Guide> options =
                new FirebaseRecyclerOptions.Builder<Guide>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Guide"), Guide.class)
                        .build();

        mainadapter = new com.example.madfinaltraveller.MainAdapter(options);
        recyclerView.setAdapter(mainadapter);


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





}