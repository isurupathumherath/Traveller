package com.example.madfinaltraveller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.MyViewholder> {

    Context context;
    ArrayList<USER> list;

    public AdapterUser(Context context, ArrayList<USER> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewholder(v);
    }

    public void Deleteuser(int position){
        list.remove(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        USER user=list.get(position);

        holder.firstname.setText(user.getUserName());
        holder.country.setText(user.getCountry());
        holder.email.setText(user.getEmail());

        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),deluser.class);
                i.putExtra("name",user.getUserName());
                //i.putExtra("mail",user.getEmail());
                //i.putExtra("country",user.getCountry());
                view.getContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder{
        TextView firstname,country,email;
        Button btndel;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i=new Intent(view.getContext(),Allusers.class);
//                    i.putExtra("title", (Parcelable) list.get(getAdapterPosition()));
//                    view.getContext().startActivity(i);
//                }
//            });
            btndel=itemView.findViewById(R.id.btnD);
            firstname=itemView.findViewById(R.id.textView3name);
            country=itemView.findViewById(R.id.textView3country);
            email=itemView.findViewById(R.id.textView3mail);
        }
    }
}
