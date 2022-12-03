package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.feeinfo;

import java.util.List;

public class compareUniAid extends AppCompatActivity {
    private List<aidInfo> aidlist, aidlist1;
    private ListView listView, listview1;
    private TextView t1, t2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_uni_aid);

        String s1 = getIntent().getExtras().getString("name1");
        String s2 = getIntent().getExtras().getString("name2");

        t1 = findViewById(R.id.uni1);
        t2 = findViewById(R.id.uni2);
        t1.setText(s1);
        t2.setText(s2);

        aidlist = (List<aidInfo>) getIntent().getExtras().getSerializable("a1");
        aidlist1 = (List<aidInfo>) getIntent().getExtras().getSerializable("a2");

        listView = findViewById(R.id.list1);
        listview1 = findViewById(R.id.list2);
        adapterFinancialAid ad = new adapterFinancialAid(this, R.layout.financial_adi_list_row, aidlist);
        adapterFinancialAid ad1 = new adapterFinancialAid(this, R.layout.financial_adi_list_row, aidlist1);

        listView.setAdapter(ad);
        listview1.setAdapter(ad1);

    }
}