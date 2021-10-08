package com.example.madfinaltraveller;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class activity_guide_review extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RatingBar r1;

    TextView getnames;
    String str,image;
    EditText review;
    DatabaseReference db;
    ImageView img;
    Reviews newReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_review);

        getnames = (TextView)findViewById(R.id.getname);
        review = (EditText)findViewById(R.id.review);
        img = (ImageView)findViewById(R.id.item_img);
        r1 = findViewById(R.id.ratingBar);


        Intent intent = getIntent();
        str = intent.getStringExtra("name");
        image = intent.getStringExtra("image");
        getnames.setText(str);
        newReview = new Reviews();


        Picasso.get()
                .load(image)
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(img);

        drawerLayout=findViewById(R.id.constraint_layout1);
        navigationView=findViewById(R.id.nav_view1);
        toolbar=findViewById(R.id.toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

//        img.setImageDrawable(res);
    }

    public void enterReview(View v){

        db = FirebaseDatabase.getInstance("https://travellermad-e1782-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Reviews");

        if(TextUtils.isEmpty(review.getText().toString().trim())){
            Toast.makeText(getApplicationContext(),"Please enter a review", Toast.LENGTH_LONG).show();
        }else{


            newReview.setReview(review.getText().toString().trim());
            newReview.setUsername(getnames.getText().toString().trim());
            newReview.setRatings(String.valueOf(r1.getRating()));

            db.push().setValue(newReview);

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Your review has been published",Toast.LENGTH_LONG).show();

        }

    }

    public void go_back(View v){

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.homepage:
                Intent i2=new Intent(this,ClientHomenew.class);
                startActivity(i2);
                break;
            case R.id.log_o:
                Intent i0=new Intent(this,Loginpage.class);
                startActivity(i0);
                break;
            case R.id.viewHotel:
                Intent i = new Intent(this, activity_show_hotel.class);
                startActivity(i);
                break;
            case R.id.viewguide:
                Intent i1 = new Intent(this, MainActivity.class);
                startActivity(i1);
                break;
            case R.id.viewtaxi:
                Intent i6 = new Intent(this, activity_client_taxi.class);
                startActivity(i6);
                break;
        }
        return true;
    }
}