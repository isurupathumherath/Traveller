package com.example.madfinaltraveller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mian_item_hotel, parent, false);
        return new MainAdapterHotel.myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name, type, address, city;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.imageHotel);
            name = (TextView)itemView.findViewById(R.id.nameHotel);
            address = (TextView)itemView.findViewById(R.id.hotelAddress);
            type = (TextView)itemView.findViewById(R.id.hotelType);
            city = (TextView)itemView.findViewById(R.id.hotelCity);

        }
    }
}
