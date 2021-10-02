package com.example.madfinaltraveller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class specific_guide_review extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tv;
    String str;
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

}