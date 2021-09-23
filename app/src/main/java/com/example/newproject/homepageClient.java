package com.example.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class homepageClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_client);
    }

    //The side pannel is activated from here
    public void Clientsidepanel(View view){
        Intent intent=new Intent(this, com.example.newproject.SidePannelClient.class);
        startActivity(intent);
    }
}