package com.example.myapplication.Classes;

import android.graphics.Bitmap;

public class imageClass {

    private Bitmap image;
    private String caption;

    public imageClass(Bitmap bp, String c)
    {
        this.image = bp;
        this.caption = c;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getCaption() {
        return caption;
    }
}
