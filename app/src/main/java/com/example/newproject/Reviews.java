package com.example.newproject;

public class Reviews {

    String username, review;

    public Reviews(){}

    public Reviews(String username, String review) {
        this.username = username;
        this.review = review;
    }

    public String getUsername() {
        return username;
    }

    public String getReview() {
        return review;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
