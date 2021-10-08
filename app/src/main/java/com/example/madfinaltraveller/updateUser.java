package com.example.madfinaltraveller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateUser extends AppCompatActivity {
    TextView username;
    EditText password,email,country;
    DatabaseReference db;
    String usern;
    USER u0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);


        Intent intent=getIntent();
        usern=intent.getStringExtra("un");
        username=findViewById(R.id.user_na);
        password=findViewById(R.id.pw_u);
        email=findViewById(R.id.email_u);
        country=findViewById(R.id.country_u);

        u0=new USER();
        db= FirebaseDatabase.getInstance().getReference().child("User").child(usern);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    username.setText(dataSnapshot.child("userName").getValue().toString());
                    password.setText(dataSnapshot.child("password").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    country.setText(dataSnapshot.child("country").getValue().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void updateUsers(View view){
        Intent i=new Intent(this,ClientHomenew.class);
        DatabaseReference db2=FirebaseDatabase.getInstance().getReference().child("User").child(usern);
        db2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                u0.setEmail(email.getText().toString().trim());
                u0.setCountry(country.getText().toString().trim());
                u0.setPassword(password.getText().toString().trim());
                u0.setType("Tourist");
                u0.setUserName(usern);

                db2.setValue(u0);

                Toast.makeText(getApplicationContext(), "User Updated", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}