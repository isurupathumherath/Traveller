package com.example.madfinaltraveller;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class admin_fuide_fragement extends Fragment {



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_fuide_fragement, container, false);

        Button button = (Button) rootView.findViewById(R.id.guideadd);
        Button button1 = (Button) rootView.findViewById(R.id.guideview);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), activity_tourist_registration.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), activity_update_guide.class);
                startActivity(intent);
            }
        });

        return rootView;

    }

//
//    public void register(View v){
//
//        Intent intent = new Intent(getActivity(),activity_tourist_registration.class);
//        getActivity().startActivity(intent);
//
//    }






}