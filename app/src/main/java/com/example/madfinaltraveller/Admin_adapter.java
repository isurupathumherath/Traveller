package com.example.madfinaltraveller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
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

public class Admin_adapter extends FirebaseRecyclerAdapter<TaxiModel,Admin_adapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Admin_adapter(@NonNull FirebaseRecyclerOptions<TaxiModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull TaxiModel model) {
        holder.drivername.setText(model.getDriverName());
        holder.contactno.setText(model.getContactNo());
        holder.vehicleno.setText(model.getVehicleNo());
        holder.taxiid.setText(model.getTaxiId());
        holder.avaarea.setText(model.getAvaArea());
        holder.perkm.setText(model.getPerKm());

        Glide.with(holder.img.getContext())
                .load(model.getProfURL())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);



        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.popup_update))
                        .setExpanded(true,2000)
                        .create();

//                dialogPlus.show();

                View view1 = dialogPlus.getHolderView();

                EditText drivername=view1.findViewById(R.id.et_DriverName);
                EditText contactno=view1.findViewById(R.id.et_ContactNo);
                EditText vehicleno=view1.findViewById(R.id.et_VehicleNo);
                EditText taxiid=view1.findViewById(R.id.et_TaxiId);
                EditText avaarea=view1.findViewById(R.id.et_AvaArea);
                EditText perkm=view1.findViewById(R.id.et_PerKm);
                EditText profurl=view1.findViewById(R.id.et_ProfURL);

                Button btnUpdate=view1.findViewById(R.id.btnUpdate);

                drivername.setText(model.getDriverName());
                contactno.setText(model.getContactNo());
                vehicleno.setText(model.getVehicleNo());
                taxiid.setText(model.getTaxiId());
                avaarea.setText(model.getAvaArea());
                perkm.setText(model.getPerKm());
                profurl.setText(model.getProfURL());

                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        if(TextUtils.isEmpty(drivername.getText().toString())){
                            Toast.makeText(holder.drivername.getContext(),"Enter driver name",Toast.LENGTH_SHORT).show();
                        }

                         else if(TextUtils.isEmpty(contactno.getText().toString())){
                            Toast.makeText(holder.drivername.getContext(),"Enter contact number",Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.isEmpty(vehicleno.getText().toString())){
                            Toast.makeText(holder.drivername.getContext(),"Enter vehicle number",Toast.LENGTH_SHORT).show();
                        }
                         else if(TextUtils.isEmpty(taxiid.getText().toString())){
                            Toast.makeText(holder.drivername.getContext(),"Enter taxi id",Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.isEmpty(avaarea.getText().toString())){
                            Toast.makeText(holder.drivername.getContext(),"Enter available area",Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.isEmpty(perkm.getText().toString())){
                            Toast.makeText(holder.drivername.getContext(),"Enter price perKm",Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.isEmpty(profurl.getText().toString())){
                            Toast.makeText(holder.drivername.getContext(),"Enter url",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            map.put("DriverName", drivername.getText().toString());
                            map.put("ContactNo", contactno.getText().toString());
                            map.put("VehicleNo", vehicleno.getText().toString());
                            map.put("TaxiId", taxiid.getText().toString());
                            map.put("AvaArea", avaarea.getText().toString());
                            map.put("PerKm", perkm.getText().toString());
                            map.put("profURL", profurl.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child("Taxiis")
                                    .child(getRef(position).getKey()).updateChildren(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(holder.drivername.getContext(), "Details updated successfully", Toast.LENGTH_LONG).show();
                                            dialogPlus.dismiss();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            Toast.makeText(holder.drivername.getContext(), "Error while updating !", Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
                                        }
                                    });
                        }

                    }
                });

            }
        });


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.drivername.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted data can't be undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                         FirebaseDatabase.getInstance().getReference().child("Taxiis")
                                 .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.drivername.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_taxi,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView drivername,contactno,vehicleno,taxiid,avaarea,perkm,profurl;

        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.img1);
            drivername=(TextView)itemView.findViewById(R.id.txt_DriverName);
            contactno=(TextView) itemView.findViewById(R.id.txt_ContactNo);
            vehicleno=(TextView) itemView.findViewById(R.id.txt_VehicleNo);
            taxiid=(TextView) itemView.findViewById(R.id.txt_TaxiId);
            avaarea=(TextView) itemView.findViewById(R.id.txt_AvaArea);
            perkm=(TextView) itemView.findViewById(R.id.txt_PerKm);
//            profurl=(TextView) itemView.findViewById(R.id.txt_profURL);

            btnEdit=(Button)itemView.findViewById(R.id.btnEdit);
            btnDelete=(Button)itemView.findViewById(R.id.btnDelete);

        }
    }
}
