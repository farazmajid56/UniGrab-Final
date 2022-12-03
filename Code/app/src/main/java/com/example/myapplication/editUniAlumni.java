package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.Classes.alumniInfo;

import java.io.Serializable;
import java.util.List;

public class editUniAlumni extends AppCompatActivity {

    private List<alumniInfo> alumnilist;
    private ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_alumni);

        alumnilist = (List<alumniInfo>) getIntent().getExtras().getSerializable("a");

        listView = findViewById(R.id.list);
        adapterAlumni ad = new adapterAlumni(this, R.layout.alumni_list_row, alumnilist);
        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                alumniInfo ai = (alumniInfo) adapterView.getItemAtPosition(i);
                Intent in = new Intent(editUniAlumni.this, editUniAlumni2.class);
                in.putExtra("a", (Serializable) ai);
                startActivity(in);
            }
        });

    }
}