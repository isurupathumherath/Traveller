package com.example.newproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class hotelUpdateAdapter extends FirebaseRecyclerAdapter<Hotel, hotelUpdateAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public hotelUpdateAdapter(@NonNull FirebaseRecyclerOptions<Hotel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull Hotel model) {
        holder.name.setText(model.getName());
        holder.type.setText(model.getType());
        holder.address.setText(model.getAddress());
        holder.city.setText(model.getCity());

        Glide.with(holder.img.getContext()).
                load(model.getImage()).
                placeholder(R.drawable.common_google_signin_btn_icon_dark).circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).into(holder.img);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_hotel_update_popup))
                        .setExpanded(true,2000)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.hotelName);
                EditText address = view.findViewById(R.id.hotelAddress);
                EditText city = view.findViewById(R.id.hotelCity);
                EditText district = view.findViewById(R.id.hotelDistrict);
                EditText locatoinURL = view.findViewById(R.id.hotelLocationTag);
                EditText service = view.findViewById(R.id.hotelService);
                EditText type = view.findViewById(R.id.hotelType);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                name.setText(model.getName());
                address.setText(model.getAddress());
                city.setText(model.getCity());
                district.setText(model.getDistrict());
                locatoinURL.setText(model.getLocationURL());
                service.setText(model.getService());
                type.setText(model.getType());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map =  new HashMap<>();
                        map.put("name", name.getText().toString());
                        map.put("address", address.getText().toString());
                        map.put("city", city.getText().toString());
                        map.put("district", district.getText().toString());
                        map.put("locatoinURL", locatoinURL.getText().toString());
                        map.put("service", service.getText().toString());
                        map.put("type", type.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Hotel")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error while updating", Toast.LENGTH_LONG).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });



            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure you want to delete");
                builder.setMessage("Once you deleted this that action cannot be undone");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Hotel")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_update_hotel, parent, false);
        return new hotelUpdateAdapter.myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name, type, address, city;
        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.imageHotel);
            name = (TextView)itemView.findViewById(R.id.nameHotel);
            address = (TextView)itemView.findViewById(R.id.hotelAddress);
            type = (TextView)itemView.findViewById(R.id.hotelType);
            city = (TextView)itemView.findViewById(R.id.hotelCity);

            btnEdit = (Button)itemView.findViewById(R.id.buttonEditHotel);
            btnDelete = (Button)itemView.findViewById(R.id.buttonDeleteHotel);

        }
    }
}
