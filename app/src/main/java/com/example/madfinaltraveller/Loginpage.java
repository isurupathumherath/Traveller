package com.example.madfinaltraveller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Loginpage extends AppCompatActivity {

    DatabaseReference db;
    EditText Uname,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        Uname=findViewById(R.id.U_name);
        pass=findViewById(R.id.PassWord);


    }

    public void moveToReg(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void UserLogin(View view){
        Intent i1=new Intent(this,Adminhome.class);
        Intent i2=new Intent(this,Allusers.class);
        db= FirebaseDatabase.getInstance().getReference().child("User").child(Uname.getText().toString().trim());
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    if(snapshot.child("password").getValue().toString().equals(pass.getText().toString())){
                        if(snapshot.child("type").getValue().toString().equals("Tourist")){
                            startActivity(i2);
                        }
                        else{
                            startActivity(i1);
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"does't exist",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void clear(View view){
        Uname.setText("");
        pass.setText("");
    }
}