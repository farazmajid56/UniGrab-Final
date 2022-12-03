package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class introactivity extends AppCompatActivity {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iv = (ImageView)findViewById(R.id.imageView2);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.blink_anim);
        iv.startAnimation(a);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(introactivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },3000);

    }
}