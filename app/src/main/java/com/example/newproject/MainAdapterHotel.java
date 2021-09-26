package com.example.newproject;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapterHotel extends FirebaseRecyclerAdapter<Hotel, MainAdapterHotel.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterHotel(@NonNull FirebaseRecyclerOptions<Hotel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Hotel model) {
        holder.name.setText(model.getName());
        holder.type.setText(model.getType());
        holder.address.setText(model.getAddress());
        holder.city.setText(model.getCity());

        Glide.with(holder.img.getContext()).
                load(model.getImage()).
                placeholder(R.drawable.common_google_signin_btn_icon_dark).circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).into(holder.img);

        holder.btnaddcommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), activity_show_hotel.class);
                i.putExtra("name", model.getName());
                i.putExtra("image", model.getImage());
                v.getContext().startActivity(i);


            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name, type, address, city;
        Button btnaddcommit;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.image);
            name = (TextView)itemView.findViewById(R.id.nametext);
            address = (TextView)itemView.findViewById(R.id.contacttext);
            type = (TextView)itemView.findViewById(R.id.emailtext);
            city = (TextView)itemView.findViewById(R.id.typetext);
            btnaddcommit = (Button)itemView.findViewById(R.id.btnaddcomment);

        }
    }
}
