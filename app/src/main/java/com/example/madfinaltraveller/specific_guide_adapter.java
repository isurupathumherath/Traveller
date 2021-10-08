package com.example.madfinaltraveller;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public class specific_guide_adapter extends FirebaseRecyclerAdapter<Reviews, specific_guide_adapter.myViewHolder> {

    public specific_guide_adapter(@NonNull FirebaseRecyclerOptions<Reviews> options) {
        super(options);
    }


    protected void onBindViewHolder(@NonNull specific_guide_adapter.myViewHolder holder, int position, @NonNull Reviews model) {
        holder.comment.setText(model.getReview());
        holder.r1.setRating(Float.parseFloat(model.getRatings()));



    }


    @NonNull
    @Override
    public specific_guide_adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specific_guide_item, parent, false);
        return new specific_guide_adapter.myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView comment;
        RatingBar r1;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            comment = (TextView)itemView.findViewById(R.id.commentText);
            r1 = (RatingBar)itemView.findViewById(R.id.MyratingBar);

        }

    }
}
