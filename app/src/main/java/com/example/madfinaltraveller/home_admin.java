package com.example.madfinaltraveller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
    }

    public void changeFragment(View view){
        Fragment fragment;
        if (view == findViewById(R.id.guidebtn)){
            fragment = new admin_fuide_fragement();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frdefault,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.hotelbtn)){
            fragment = new admin_user_fragement();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frdefault,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.taxibtn)){
            fragment = new fragement_admin_taxi();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frdefault,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.staticbtn)){
            fragment = new fragement_admin_statistic();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frdefault,fragment);
            ft.commit();
        }
    }



}