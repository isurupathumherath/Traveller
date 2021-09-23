package com.example.newproject;

public class Guide {

    Guide(){}
    String name, contact, image, email, type;

    public Guide(String name, String contact, String image, String email, String type) {
        this.name = name;
        this.contact = contact;
        this.image = image;
        this.email = email;
        this.type = type;
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
