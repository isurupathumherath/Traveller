package com.example.madfinaltraveller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Allusers extends AppCompatActivity {

    TextView text;
    RecyclerView recyclerView;
    AdapterUser myadapter;
    ArrayList<USER> list;
    DatabaseReference db;
    //String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allusers);

        //text=findViewById(R.id.textView3name);

        //Intent i=getIntent();
        //title=i.getStringExtra("title");
        //Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
        recyclerView=findViewById(R.id.recycler_view);
        db=FirebaseDatabase.getInstance().getReference().child("User");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        myadapter=new AdapterUser(this,list);
        recyclerView.setAdapter(myadapter);


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    USER user=dataSnapshot1.getValue(USER.class);
                    list.add(user);
                }
                myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        //itemTouchHelper.attachToRecyclerView(recyclerView);
    }


//    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            return false;
//        }
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//            int position=viewHolder.getAdapterPosition();
//            switch (direction){
//                case ItemTouchHelper.LEFT:
//                    myadapter.Deleteuser(position);
//                    myadapter.notifyItemRemoved(position);
//                    break;
//                case ItemTouchHelper.RIGHT:
//                    break;
//            }
//        }
//    };

//    public  void deleteb(){
//        Toast.makeText(getApplicationContext(),title, Toast.LENGTH_SHORT).show();
//    }

}