package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.reviewInfo;

import java.util.List;

public class compareUniReviews extends AppCompatActivity {
    private List<reviewInfo> rlist, rlist1;
    private ListView listView, listview1;
    private TextView t1, t2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_uni_reviews);

        String s1 = getIntent().getExtras().getString("name1");
        String s2 = getIntent().getExtras().getString("name2");

        t1 = findViewById(R.id.uni1);
        t2 = findViewById(R.id.uni2);
        t1.setText(s1);
        t2.setText(s2);

        rlist = (List<reviewInfo>) getIntent().getExtras().getSerializable("a1");
        rlist1 = (List<reviewInfo>) getIntent().getExtras().getSerializable("a2");

        listView = findViewById(R.id.list1);
        listview1 = findViewById(R.id.list2);
        adapterReview ad = new adapterReview(this, R.layout.review_list_row, rlist);
        adapterReview ad1 = new adapterReview(this, R.layout.review_list_row, rlist1);

        listView.setAdapter(ad);
        listview1.setAdapter(ad1);
    }
}