package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class loginpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
    }

    public void c(View view){
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }
//        public void Adminsidepanel(View view){
//        Intent intent=new Intent(this,Register.class);
//        startActivity(intent);
//    }
}