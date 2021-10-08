package com.example.madfinaltraveller;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class activity_tourist_registration extends AppCompatActivity {

    EditText ed_regname, ed_regemail, ed_regcontact, ed_regtype, ed_currency;
    CircleImageView ed_image;
    ImageButton imgbtn;
    Guide newtourist;
    Button regsubmit;
    Uri filepath;
    Bitmap bitmap;
    AwesomeValidation awesomeValidation;
    DatabaseReference db;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_registration);


        regsubmit = findViewById(R.id.regsubmit);
        imgbtn = findViewById(R.id.imgbtn);
        ed_image = findViewById(R.id.imgtext);
        newtourist = new Guide();

        imgbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//
//                openFileChooser();
                Dexter.withActivity(activity_tourist_registration.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {

                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response)
                            {



                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                Log.d("asdsa","asdasd");
                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image File"),1);

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //add validation for first name
        awesomeValidation.addValidation(this,R.id.regname, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        //add validation for last name
        awesomeValidation.addValidation(this,R.id.regtype, RegexTemplate.NOT_EMPTY,R.string.invalid_type);
        //add validation for email
        awesomeValidation.addValidation(this,R.id.regemail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        //add validation for phone number
        awesomeValidation.addValidation(this,R.id.regcontact,"[0-9]{3}[0-9]{3}[0-9]{4}$",R.string.invalid_contact);
        //add validation for address
        awesomeValidation.addValidation(this,R.id.currencytxt, RegexTemplate.NOT_EMPTY,R.string.invalid_currency);

        regsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (awesomeValidation.validate()){

                    uploadtofirebase();
                }else{

                    Toast.makeText(getApplicationContext(),
                            "Validation Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1  && resultCode==RESULT_OK)
        {
            filepath=data.getData();
            try{
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                ed_image.setImageBitmap(bitmap);
            }catch (Exception ex)
            {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadtofirebase() {

        if(filepath == null){

            Toast.makeText(this, "Please Select a Profile Image", Toast.LENGTH_SHORT).show();
        }else {

            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("File Uploader");
            dialog.show();

            ed_regname = findViewById(R.id.regname);
            ed_regemail = findViewById(R.id.regemail);
            ed_regcontact= findViewById(R.id.regcontact);
            ed_regtype = findViewById(R.id.regtype);
            ed_currency = findViewById(R.id.currencytxt);

            FirebaseStorage storage = FirebaseStorage.getInstance();
            final StorageReference uploader = storage.getReference("Image1" + new Random().nextInt(50));
            uploader.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    dialog.dismiss();
                                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                                    DatabaseReference dbref = db.getReference("Guide");
                                    String Email = ed_regemail.getText().toString().trim();

                                    Guide obj = new Guide(ed_regname.getText().toString(), ed_regemail.getText().toString(), ed_regcontact.getText().toString(), ed_regtype.getText().toString(), ed_currency.getText().toString(), uri.toString());
                                    dbref.push().setValue(obj);

                                    Toast.makeText(activity_tourist_registration.this, "User added", Toast.LENGTH_SHORT).show();
                                    ed_regname.setText("");
                                    ed_regemail.setText("");
                                    ed_regcontact.setText("");
                                    ed_regtype.setText("");
                                    ed_currency.setText("");

                                    ed_image.setImageResource(R.drawable.ic_launcher_background);

                                }
                            });
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            float percent = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            dialog.setMessage("Uploaded :" + (int) percent + " %");
                        }
                    });
        }


    }

    public void go_back(View view){

        Intent intent = new Intent(this,activity_update_guide.class);
        startActivity(intent);

    }

//    public void registerTourist(View v){
//
//        db = FirebaseDatabase.getInstance("https://travellermad-e1782-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Guide");
//
//        if(TextUtils.isEmpty(ed_regname.getText().toString().trim())){
//            Toast.makeText(getApplicationContext(),"Please enter tourist name", Toast.LENGTH_LONG).show();
//        }else if(TextUtils.isEmpty(ed_regemail.getText().toString().trim())){
//            Toast.makeText(getApplicationContext(),"Please enter a email", Toast.LENGTH_LONG).show();
//        }
//        else if(TextUtils.isEmpty(ed_regcontact.getText().toString().trim())){
//            Toast.makeText(getApplicationContext(),"Please enter a contact number", Toast.LENGTH_LONG).show();
//        }
//        else if(TextUtils.isEmpty(ed_regtype.getText().toString().trim())){
//            Toast.makeText(getApplicationContext(),"Please choose a type", Toast.LENGTH_LONG).show();
//        }else if(TextUtils.isEmpty(ed_currency.getText().toString().trim())){
//            Toast.makeText(getApplicationContext(),"Please enter a currency", Toast.LENGTH_LONG).show();
//        }else {
//
////          Tourist newtourist = new Tourist(ed_regname.getText().toString().trim(), ed_regemail.getText().toString().trim(), Integer.parseInt(ed_regcontact.getText().toString().trim()), ed_regtype.getText().toString().trim() );
//
//            newtourist.setName(ed_regname.getText().toString().trim());
//            newtourist.setEmail(ed_regemail.getText().toString().trim());
//            newtourist.setType(ed_regtype.getText().toString().trim());
//            newtourist.setContact(ed_regcontact.getText().toString().trim());
////            newtourist.setImage(ed_image.getText().toString().trim());
//            newtourist.setCurrency(ed_currency.getText().toString().trim());
//            db.push().setValue(newtourist);
//
//            Toast.makeText(getApplicationContext(), "Registration successfull", Toast.LENGTH_LONG).show();
//            ed_regname.getText().clear();
//            ed_regemail.getText().clear();
//            ed_regtype.getText().clear();
//            ed_regcontact.getText().clear();
////            ed_image.getText().clear();
//            ed_currency.getText().clear();
//
//
//        }
//    }
}