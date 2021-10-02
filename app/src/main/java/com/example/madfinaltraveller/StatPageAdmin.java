package com.example.madfinaltraveller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatPageAdmin extends AppCompatActivity {

    EditText tot_guide,tot_hotel,tot_user;
    DatabaseReference db,db1,db2;
    public int u_count=0,g_count=0,h_count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_page_admin);

        tot_guide=findViewById(R.id.tot_guides);
        tot_hotel=findViewById(R.id.tot_hotels);
        tot_user=findViewById(R.id.tot_users);

        db= FirebaseDatabase.getInstance().getReference().child("User");
        db1= FirebaseDatabase.getInstance().getReference().child("Guide");
        db2= FirebaseDatabase.getInstance().getReference().child("Hotel");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    u_count=(int) dataSnapshot.getChildrenCount();
                    tot_user.setText(String.valueOf(u_count));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    g_count=(int) dataSnapshot.getChildrenCount();
                    tot_guide.setText(String.valueOf(g_count));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    h_count=(int) dataSnapshot.getChildrenCount();
                    tot_hotel.setText(String.valueOf(h_count));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}