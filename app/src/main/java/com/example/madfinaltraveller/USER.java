package com.example.madfinaltraveller;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.Date;

public class USER {
    private String userName;
    private String password;
    private String Email;
    private String country;
    private String id;
    private Date today;
    private String type;
    private FirebaseDatabase db;
    private DatabaseReference ref;

    public USER() {
    }

    public USER(String userName, String password, String email, String country,String id,Date today,String type) {
        this.userName = userName;
        this.password = password;
        this.Email = email;
        this.country = country;
        this.id=id;
        this.today=today;
        this.type=type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void deleteuser(String name){
        ref.child(name).setValue(null);
    }
}
