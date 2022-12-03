package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.alumniInfo;
import com.example.myapplication.Classes.currentUserUni;
import com.example.myapplication.Classes.profinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class editUniFaculty2 extends AppCompatActivity {

    private List<profinfo> proflist;
    private ListView listView;
    private University obj;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_faculty2);

        String str = getIntent().getExtras().getString("dname");

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        proflist = new ArrayList<profinfo>();
        obj.getFacultyContent(editUniFaculty2.this, obj.getName(), str, proflist);


        listView = findViewById(R.id.facultylist);
        adapterProf ad = new adapterProf(this, R.layout.faculty_list_row, proflist);
        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                profinfo ai = (profinfo) adapterView.getItemAtPosition(i);
                Intent in = new Intent(editUniFaculty2.this, editUniFaculty3.class);
                in.putExtra("dname", str);
                in.putExtra("a", (Serializable) ai);
                startActivity(in);

            }
        });

    }
}