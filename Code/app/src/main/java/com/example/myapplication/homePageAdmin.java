package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Classes.Admin;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.currentUserAdmin;

public class homePageAdmin extends AppCompatActivity {
    private Button feedbackBtn;
    private Button _lout;
    private Button _ManAcc;
    private Admin obj;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_admin);

        currentUserAdmin ca  = currentUserAdmin.getInstance(obj);
        obj = ca.getA();
        Toast.makeText(this, "username:"+obj.getName(), Toast.LENGTH_SHORT).show();

        feedbackBtn = findViewById(R.id.fb);
        _lout = findViewById(R.id._lout);
        _ManAcc = findViewById(R.id._ManAcc);

        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(homePageAdmin.this, feedbackShowAdmin.class);
                startActivity(in);
            }
        });

        _lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent back = new Intent(homePageAdmin.this, MainActivity.class);
                    //back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(back);
                    finish();
            }
        });

        _ManAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(homePageAdmin.this, com.example.myapplication.Admin_Home.class);
                //back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(back);
            }
        });
    }
}