package com.example.madfinaltraveller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class deluser extends AppCompatActivity {

    EditText name,email,country;
    Button del;
    String str,em,co;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deluser);

        name=findViewById(R.id.username);
        del=findViewById(R.id.delete);

        Intent intent=getIntent();
        str=intent.getStringExtra("name");
        name.setText(str);

    }

    public void deleteUSER(View view){
        DatabaseReference deb= FirebaseDatabase.getInstance().getReference().child("User");
        Intent i=new Intent(this,Allusers.class);

        deb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                db=FirebaseDatabase.getInstance().getReference().child("User").child(str);
                db.removeValue();
                Toast.makeText(getApplicationContext(),"Data deleted successfully",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}