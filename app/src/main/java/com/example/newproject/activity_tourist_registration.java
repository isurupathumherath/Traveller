package com.example.newproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_tourist_registration extends AppCompatActivity {

    EditText ed_regname, ed_regemail, ed_regcontact, ed_regtype, ed_image;

    Guide newtourist;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_registration);

        ed_regname = findViewById(R.id.regname);
        ed_regemail = findViewById(R.id.regemail);
        ed_regcontact= findViewById(R.id.regcontact);
        ed_regtype = findViewById(R.id.regtype);
        ed_image = findViewById(R.id.imgtext);
        newtourist = new Guide();

    }

    public void registerTourist(View v){

        db = FirebaseDatabase.getInstance("https://travellermad-e1782-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Guide");

        if(TextUtils.isEmpty(ed_regname.getText().toString().trim())){
            Toast.makeText(getApplicationContext(),"Please enter tourist name", Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(ed_regemail.getText().toString().trim())){
            Toast.makeText(getApplicationContext(),"Please enter a email", Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(ed_regcontact.getText().toString().trim())){
            Toast.makeText(getApplicationContext(),"Please enter a contact number", Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(ed_regtype.getText().toString().trim())){
            Toast.makeText(getApplicationContext(),"Please choose a type", Toast.LENGTH_LONG).show();
        }else{

//          Tourist newtourist = new Tourist(ed_regname.getText().toString().trim(), ed_regemail.getText().toString().trim(), Integer.parseInt(ed_regcontact.getText().toString().trim()), ed_regtype.getText().toString().trim() );

            newtourist.setName(ed_regname.getText().toString().trim());
            newtourist.setEmail(ed_regemail.getText().toString().trim());
            newtourist.setType(ed_regtype.getText().toString().trim());
            newtourist.setContact(ed_regcontact.getText().toString().trim());
            newtourist.setImage(ed_image.getText().toString().trim());
            db.push().setValue(newtourist);

            Toast.makeText(getApplicationContext(),"Registration successfull",Toast.LENGTH_LONG).show();
        }
    }
}