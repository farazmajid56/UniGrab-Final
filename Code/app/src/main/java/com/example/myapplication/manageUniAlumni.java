package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Classes.alumniInfo;

import java.io.Serializable;
import java.util.List;

public class manageUniAlumni extends AppCompatActivity {

    private Button b1, b2, b3;
    private List<alumniInfo> arr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_uni_alumni);

        b1 = findViewById(R.id.addalumni);
        b2 = findViewById(R.id.edtalumni);
        b3 = findViewById(R.id.delalumni);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(manageUniAlumni.this, addUniAlumni.class);
                startActivity(in);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(manageUniAlumni.this, editUniAlumni.class);
                arr = (List<alumniInfo>) getIntent().getExtras().getSerializable("a");
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