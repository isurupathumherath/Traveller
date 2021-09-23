package com.example.newproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText user_name, email, password;
    Button submit;
    RadioButton mal_e, femal_e;
    FirebaseDatabase mydb;
    DatabaseReference reference;
    String gen = "";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_name =findViewById(R.id.Username);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        submit = findViewById(R.id.Submit);
        mal_e = findViewById(R.id.Male);
        femal_e = findViewById(R.id.female);

        //reference = FirebaseDatabase.getInstance("https://travellermad-e1782-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("User");


    }

//    private boolean validateName(){
//        String val=user_name.getEditText().getText().toString();
//        String nowhitespace="(?=\\s+$)";
//
//        if(val.isEmpty()){
//            user_name.setError("Field cannot be empty");
//            return false;
//        }
//        else if(val.length()>=15){
//            user_name.setError("Uesrname too lang");
//            return false;
//        }
//        else if(!val.matches(nowhitespace)){
//            user_name.setError("White space allowed");
//            return false;
//        }
//        else{
//            user_name.setError(null);
//            user_name.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validatemail(){
//        String val=email.getEditText().getText().toString();
//        String emailpattern="[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
//
//        if(val.isEmpty()){
//            email.setError("Field cannot be empty");
//            return false;
//        }
//        else if(!val.matches(emailpattern)){
//            email.setError("Invalid email address");
//            return false;
//        }
//        else{
//            email.setError(null);
//            email.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validatepassword(){
//        String val=password.getEditText().getText().toString();
//        String passwordval="^"+".{4,}";
//        if(val.isEmpty()){
//            password.setError("Field cannot be empty");
//            return false;
//        }
//        else if(!val.matches(passwordval)){
//            password.setError("Invalid password entered");
//            return false;
//        }
//        else{
//            password.setError(null);
//            return true;
//        }
//    }
//
//    public void insertUserData(View view) {
//
//        if(!validateName()| !validatemail()| !validatepassword()){
//            return;
//        }
//
//        String name=user_name.getEditText().getText().toString();
//        String pw=password.getEditText().getText().toString();
//        String mail=email.getEditText().getText().toString();
//        String gender=gen;
//
//        user u0=new user(name,mail,gender,pw);
//
//        reference.child(name).setValue(u0);
//        Toast.makeText(Register.this,"Sussessfully Registered",Toast.LENGTH_SHORT).show();
////        Intent intent=new Intent(this,homepageClient.class);
////        startActivity(intent);
//    }


}