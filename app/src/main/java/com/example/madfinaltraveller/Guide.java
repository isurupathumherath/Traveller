package com.example.madfinaltraveller;

public class Guide {

    Guide(){}
    String name, contact, image, email, type, currency;

    public Guide(String name, String email, String contact, String type, String currency, String image) {
        this.name = name;
        this.contact = contact;
        this.image = image;
        this.email = email;
        this.type = type;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }


    public String getContact() {
        return contact;
    }

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }
}
