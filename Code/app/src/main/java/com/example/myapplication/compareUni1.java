package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.alumniInfo;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.feeinfo;
import com.example.myapplication.Classes.reviewInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class compareUni1 extends AppCompatActivity {

    private Button b1, b2, b3, b4, b5, b6, b7;
    private ArrayList<String> depts = new ArrayList<>(), depts1 = new ArrayList<>();
    private List<alumniInfo> arr = new ArrayList<alumniInfo>(), arr1 = new ArrayList<alumniInfo>();
    private List<feeinfo> fees = new ArrayList<feeinfo>(), fees1 = new ArrayList<feeinfo>();
    private List<aidInfo> aid = new ArrayList<aidInfo>(), aid1 = new ArrayList<aidInfo>();
    private List<reviewInfo> reviewInfos= new ArrayList<reviewInfo>(), reviewInfos1 = new ArrayList<reviewInfo>();
    private Student obj;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_uni1);

        currentUser cu  = currentUser.getInstance(obj, null, null);
        obj = cu.getStu();

        String s1 = getIntent().getExtras().getString("name1");
        String s2 = getIntent().getExtras().getString("name2");

        obj.getUnis(this, s1, depts, arr, fees, aid, reviewInfos);
        obj.getUnis(this, s2, depts1, arr1, fees1, aid1, reviewInfos1);

        b1 = findViewById(R.id.about);
        b2 = findViewById(R.id.prgms);
        b3 = findViewById(R.id.alumni);
        b4 = findViewById(R.id.fee);
        b5 = findViewById(R.id.elgb);
        b6 = findViewById(R.id.aid);
        b7 = findViewById(R.id.review);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(compareUni1.this, compareUniAbout.class);
                in.putExtra("name1", s1);
                in.putExtra("name2", s2);

                startActivity(in);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(compareUni1.this, compareUniPrograms.class);
                in.putExtra("name1", s1);
                in.putExtra("name2", s2);
                in.putStringArrayListExtra("d1", depts);
                in.putStringArrayListExtra("d2", depts1);
                startActivity(in);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(compareUni1.this, compareUniAlumni.class);
                in.putExtra("name1", s1);
                in.putExtra("name2", s2);
                in.putExtra("a1", (Serializable) arr);
                in.putExtra("a2", (Serializable) arr1);
                startActivity(in);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(compareUni1.this, compareUniFees.class);
                in.putExtra("name1", s1);
                in.putExtra("name2", s2);
                in.putExtra("a1", (Serializable) fees);
                in.putExtra("a2", (Serializable) fees1);
                startActivity(in);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(compareUni1.this, compareUniEligibility.class);
                in.putExtra("name1", s1);
                in.putExtra("name2", s2);
                startActivity(in);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(compareUni1.this, compareUniAid.class);
                in.putExtra("name1", s1);
                in.putExtra("name2", s2);
                in.putExtra("a1", (Serializable) aid);
                in.putExtra("a2", (Serializable) aid1);
                startActivity(in);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(compareUni1.this, compareUniReviews.class);
                in.putExtra("name1", s1);
                in.putExtra("name2", s2);
                in.putExtra("a1", (Serializable) reviewInfos);
                in.putExtra("a2", (Serializable) reviewInfos1);
                startActivity(in);
            }
        });

    }
}