package com.example.madfinaltraveller;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class fragement_admin_taxi extends Fragment {

    Button taxiadd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragement_admin_taxi, container, false);

        Button button = (Button) rootView.findViewById(R.id.taxiadd);
        Button button1 = (Button) rootView.findViewById(R.id.taxiview);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add_taxi.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), activity_admin_taxi.class);
                startActivity(intent);
            }
        });

        return rootView;


    }




}

