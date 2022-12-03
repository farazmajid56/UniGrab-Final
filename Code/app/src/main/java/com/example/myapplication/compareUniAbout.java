package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;

public class compareUniAbout extends AppCompatActivity {
    private TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
    private Student obj;
    private String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
    private int i1,i2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_uni_about);

        s1 = getIntent().getExtras().getString("name1");
        s2 = getIntent().getExtras().getString("name2");

        t1 = findViewById(R.id.uni1);
        t2 = findViewById(R.id.ph1);
        t3 = findViewById(R.id.email1);
        t4 = findViewById(R.id.rank1);
        t5 = findViewById(R.id.addr1);
        t6 = findViewById(R.id.campuslife1);

        t7 = findViewById(R.id.uni2);
        t8 = findViewById(R.id.ph2);
        t9 = findViewById(R.id.email2);
        t10 = findViewById(R.id.rank2);
        t11 = findViewById(R.id.addr2);
        t12 = findViewById(R.id.campuslife2);

        currentUser cu  = currentUser.getInstance(obj, null, null);
        obj = cu.getStu();

        s3 = obj.getUniPhone(this, s1);
        s4 = obj.getUniEmail(this, s1);
        i1 = obj.getUniRanking(this, s1);
        s5 = obj.getUniAddress(this, s1);
        s6 = obj.getUniCampusLife(this, s1);

        s7 = obj.getUniPhone(this, s2);
        s8 = obj.getUniEmail(this, s2);
        i2 = obj.getUniRanking(this, s2);
        s9 = obj.getUniAddress(this, s2);
        s10 = obj.getUniCampusLife(this, s2);

        t1.setText(s1);
        t2.setText(s3);
        t3.setText(s4);
        t4.setText(String.valueOf(i1));
        t5.setText(s5);
        t6.setText(s6);

        t7.setText(s2);
        t8.setText(s7);
        t9.setText(s8);
        t10.setText(String.valueOf(i2));
        t11.setText(s9);
        t12.setText(s10);

    }
}