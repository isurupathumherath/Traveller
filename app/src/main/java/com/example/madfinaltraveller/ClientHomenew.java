package com.example.madfinaltraveller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ClientHomenew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_homenew);
    }

    public void moveGuide(View view){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void showall(View view){
        Intent i=new Intent(this,activity_show_hotel.class);
        startActivity(i);
    }

}