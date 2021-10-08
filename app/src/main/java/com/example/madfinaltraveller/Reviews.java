package com.example.madfinaltraveller;

public class Reviews {

    String username, review,ratings;


    public Reviews(){}

    public Reviews(String username, String review) {
        this.username = username;
        this.review = review;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
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
