package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter mainadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Guide> options =
                new FirebaseRecyclerOptions.Builder<Guide>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Guide"), Guide.class)
                        .build();

        mainadapter = new MainAdapter(options);
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
}