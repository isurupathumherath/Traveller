package com.example.madfinaltraveller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_register2 extends AppCompatActivity {

    EditText user_n,e_mail,pass,countr_y;
    DatabaseReference db;
    USER user;
    int exist=0;
    String type="Tourist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        user_n=findViewById(R.id.U_name);
        e_mail=findViewById(R.id.E_mail);
        pass=findViewById(R.id.PassWord);
        countr_y=findViewById(R.id.Country);

        user=new USER();
    }

    public void RegisterUser(View view){
        db= FirebaseDatabase.getInstance().getReference().child("User");
        Intent intent=new Intent(this,Loginpage.class);
        String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.child(user_n.getText().toString().trim()).exists()){
                    Toast.makeText(getApplicationContext(),"User Already Exist",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(TextUtils.isEmpty(user_n.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Enter a user name",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(e_mail.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Enter an Email",Toast.LENGTH_SHORT).show();
                    }
                    else if(!e_mail.getText().toString().matches(emailpattern)){
                        Toast.makeText(getApplicationContext(),"Email Entered is not valid",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(pass.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Enter a password",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(countr_y.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Enter a Country",Toast.LENGTH_SHORT).show();
                    }
                    else{

                        user.setUserName(user_n.getText().toString().trim());
                        user.setEmail(e_mail.getText().toString().trim());
                        user.setPassword(pass.getText().toString().trim());
                        user.setCountry(countr_y.getText().toString().trim());
                        user.setType(type);
                        db.child(user.getUserName()).setValue(user);
                        Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
}