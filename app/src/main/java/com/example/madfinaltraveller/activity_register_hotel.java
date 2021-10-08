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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
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

public class activity_register_hotel extends AppCompatActivity {

    EditText txtName, txtType, txtAddress, txtCity, txtDistrict, txtProvince, txtLocationURL, txtService;
    Button btnSave;
    ImageButton imageButton;
    DatabaseReference dbRef;
    Hotel hotel;
    Uri filepath;
    Bitmap bitmap;
    CircleImageView ed_image;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    //Method to clear all user inputss
    private void clearControls() {
        txtName.setText("");
        txtType.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtDistrict.setText("");
        txtLocationURL.setText("");
        txtService.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_hotel);

        txtName = findViewById(R.id.editHotelName);
        txtAddress = findViewById(R.id.editHotelAddress);
        txtLocationURL = findViewById(R.id.editHotelLocationTag);
        txtService = findViewById(R.id.editHotelService);
        txtType = findViewById(R.id.editHotelType);
        txtCity = findViewById(R.id.editHotelCity);
        txtDistrict = findViewById(R.id.editHotelDistrict);

        imageButton = findViewById(R.id.imageButton);
        ed_image = findViewById(R.id.imageHtl);
        btnSave = findViewById(R.id.btnSave);

        hotel = new Hotel();

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                openFileChooser();
                Dexter.withActivity(activity_register_hotel.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {

                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response)
                            {

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
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

    public void save() {

        if(filepath == null){

            Toast.makeText(this, "Please Select a Profile Image", Toast.LENGTH_SHORT).show();
        }else {
            dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel");

            try {

                if (TextUtils.isEmpty(txtName.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter Hotel Name", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtAddress.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter Hotel Address", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtLocationURL.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter Hotel Location ", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtService.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter Hotel Services ", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtType.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter Hotel Services ", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtCity.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter Hotel Services ", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtDistrict.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter Hotel Services ", Toast.LENGTH_SHORT).show();

                else {

                    final ProgressDialog dialog = new ProgressDialog(this);
                    dialog.setTitle("File Uploader");
                    dialog.show();
                    System.out.println("Image Uploading");

                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    final StorageReference uploader = storage.getReference("ImageHotel" + new Random().nextInt(50));
                    uploader.putFile(filepath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    System.out.println("Image Uploading");
                                    uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                            dialog.dismiss();

                                            ed_image.setImageResource(R.drawable.ic_launcher_background);

                                            hotel.setName(txtName.getText().toString().trim());
                                            hotel.setAddress(txtAddress.getText().toString().trim());
                                            hotel.setLocationURL(txtLocationURL.getText().toString().trim());
                                            hotel.setService(txtService.getText().toString().trim());
                                            hotel.setType(txtType.getText().toString().trim());
                                            hotel.setCity(txtCity.getText().toString().trim());
                                            hotel.setDistrict(txtDistrict.getText().toString().trim());
                                            hotel.setImage(uri.toString());

                                            dbRef.push().setValue(hotel);
//                                          dbRef.child("htl1").setValue(hotel);

                                            Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                                            clearControls();

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
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}