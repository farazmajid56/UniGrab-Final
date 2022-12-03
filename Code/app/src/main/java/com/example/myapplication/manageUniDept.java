package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.currentUserUni;

import java.util.ArrayList;
import java.util.List;

public class manageUniDept extends AppCompatActivity {

    private Button b1, b2, b3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_uni_dept);

        b1 = findViewById(R.id.adddept);
        b2 = findViewById(R.id.edtdept);
        b3 = findViewById(R.id.deldept);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(manageUniDept.this, addUniDept.class);
                startActivity(in);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(manageUniDept.this, editUniDept.class);
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