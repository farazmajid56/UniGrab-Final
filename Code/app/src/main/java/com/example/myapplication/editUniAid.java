package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.alumniInfo;

import java.io.Serializable;
import java.util.List;

public class editUniAid extends AppCompatActivity {

    private List<aidInfo> alist;
    private ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_aid);


        alist = (List<aidInfo>) getIntent().getExtras().getSerializable("a");

        listView = findViewById(R.id.aidlist);
        adapterFinancialAid ad = new adapterFinancialAid(this, R.layout.alumni_list_row, alist);
        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                aidInfo ai = (aidInfo) adapterView.getItemAtPosition(i);
                Intent in = new Intent(editUniAid.this, editUniAid2.class);
                in.putExtra("a", (Serializable) ai);
                startActivity(in);
            }
        });
    }
}