package com.example.madfinaltraveller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madfinaltraveller.TaxiModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class taxi_client_adapter extends FirebaseRecyclerAdapter <TaxiModel,taxi_client_adapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public taxi_client_adapter(@NonNull FirebaseRecyclerOptions<TaxiModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull TaxiModel model) {
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

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView drivername,contactno,vehicleno,taxiid,avaarea,perkm;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img=(CircleImageView)itemView.findViewById(R.id.c_img1);
            drivername=(TextView)itemView.findViewById(R.id.c_txt_DriverName);
            contactno=(TextView) itemView.findViewById(R.id.c_txt_ContactNo);
            vehicleno=(TextView) itemView.findViewById(R.id.c_txt_VehicleNo);
            taxiid=(TextView) itemView.findViewById(R.id.c_txt_TaxiId);
            avaarea=(TextView) itemView.findViewById(R.id.c_txt_AvaArea);
            perkm=(TextView) itemView.findViewById(R.id.c_txt_PerKm);
        }
    }


}