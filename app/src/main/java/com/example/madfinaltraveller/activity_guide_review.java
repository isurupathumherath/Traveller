package com.example.madfinaltraveller;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_guide_review extends AppCompatActivity {

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
        Intent intent = getIntent();
        str = intent.getStringExtra("name");
        image = intent.getStringExtra("image");
        getnames.setText(str);
        newReview = new Reviews();

//        img.setImageDrawable(res);
    }

    public void enterReview(View v){

        db = FirebaseDatabase.getInstance("https://travellermad-e1782-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Reviews");

        if(TextUtils.isEmpty(review.getText().toString().trim())){
            Toast.makeText(getApplicationContext(),"Please enter a review", Toast.LENGTH_LONG).show();
        }else{

//          Tourist newtourist = new Tourist(ed_regname.getText().toString().trim(), ed_regemail.getText().toString().trim(), Integer.parseInt(ed_regcontact.getText().toString().trim()), ed_regtype.getText().toString().trim() );

            newReview.setReview(review.getText().toString().trim());
            newReview.setUsername(getnames.getText().toString().trim());

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
}