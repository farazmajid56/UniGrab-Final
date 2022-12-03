package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.alumniInfo;

import java.io.Serializable;
import java.util.List;

public class manageUniAid extends AppCompatActivity {

    private Button b1, b2, b3;
    private List<aidInfo> arr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_uni_aid);

        b1 = findViewById(R.id.addaid);
        b2 = findViewById(R.id.edtaid);
        b3 = findViewById(R.id.delaid);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(manageUniAid.this, addUniAid.class);
                startActivity(in);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(manageUniAid.this, editUniAid.class);
                arr = (List<aidInfo>) getIntent().getExtras().getSerializable("a");
                in.putExtra("a", (Serializable) arr);
                startActivity(in);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}