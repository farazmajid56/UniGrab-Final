package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addPostUni extends AppCompatActivity {

    Button b1, b2, b3;
    private String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post_uni);

        uname = getIntent().getExtras().getString("name");

        b1 = findViewById(R.id.img);
        b2 = findViewById(R.id.txt);
        b3 = findViewById(R.id.vid);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(addPostUni.this, addImageUni.class);
                in.putExtra("name", uname);
                startActivity(in);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(addPostUni.this, addStatusUni.class);
                in.putExtra("name", uname);
                startActivity(in);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(addPostUni.this, addVideoUni.class);
                in.putExtra("name", uname);
                startActivity(in);
            }
        });

    }
}