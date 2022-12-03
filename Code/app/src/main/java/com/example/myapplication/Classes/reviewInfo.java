package com.example.myapplication.Classes;

import java.io.Serializable;

public class reviewInfo implements Serializable {
    private String review;
    private int rating;

    public reviewInfo(String text, int rating)
    {
        this.review = text;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }
}
