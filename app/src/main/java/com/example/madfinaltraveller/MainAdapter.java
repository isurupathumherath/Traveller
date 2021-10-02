package com.example.madfinaltraveller;

import android.content.Intent;
import android.view.LayoutInflater;
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

public class MainAdapter extends FirebaseRecyclerAdapter<Guide, MainAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public MainAdapter(@NonNull FirebaseRecyclerOptions<Guide> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Guide model) {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.contact.setText(model.getContact());
        holder.type.setText(model.getType());
        holder.currency.setText(model.getCurrency());

        Glide.with(holder.img.getContext()).
                load(model.getImage()).
                placeholder(R.drawable.common_google_signin_btn_icon_dark).circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).into(holder.img);


        holder.btnaddcommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), activity_guide_review.class);
                i.putExtra("name", model.getName());
                i.putExtra("image", model.getImage());
                v.getContext().startActivity(i);


            }
        });

        holder.btnseecomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), specific_guide_review.class);
                i.putExtra("name", model.getName());
                i.putExtra("image", model.getImage());
                v.getContext().startActivity(i);


            }
        });
<<<<<<< HEAD
=======

        holder.btnseecurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), currency_change.class);
                i.putExtra("currency", model.getCurrency());
                v.getContext().startActivity(i);


            }
        });
>>>>>>> 856eaaae4e0de787a427742ed236ffaa2f2fe2ed
    }





    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
<<<<<<< HEAD
        TextView name, contact, email, type;
        Button btnaddcommit, btnseecomment;
=======
        TextView name, contact, email, type, currency;
        Button btnaddcommit, btnseecomment, btnseecurrency;
>>>>>>> 856eaaae4e0de787a427742ed236ffaa2f2fe2ed

        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            img = (CircleImageView)itemView.findViewById(R.id.img1);
            name = (TextView)itemView.findViewById(R.id.nametext);
            contact = (TextView)itemView.findViewById(R.id.contacttext);
            currency = (TextView)itemView.findViewById(R.id.currencytext);
            email = (TextView)itemView.findViewById(R.id.emailtext);
            type = (TextView)itemView.findViewById(R.id.typetext);
            btnaddcommit = (Button)itemView.findViewById(R.id.btnaddcomment);
            btnseecomment = (Button)itemView.findViewById(R.id.btnseecomment);
<<<<<<< HEAD
=======
            btnseecurrency = (Button)itemView.findViewById(R.id.btnseecurrency);

>>>>>>> 856eaaae4e0de787a427742ed236ffaa2f2fe2ed


        }

    }
}
