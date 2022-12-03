package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;

import java.util.ArrayList;
import java.util.List;

public class filterUniDeg1 extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    Student obj1;
    List<String> arr;
    Spinner acc;
    Button b;
    String item;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_uni_deg1);

        currentUser cu  = currentUser.getInstance(obj1, null, null);
        obj1 = cu.getStu();

        arr = new ArrayList<String>();
        obj1.getAllPrograms(this, arr);


        acc = findViewById(R.id.spinnerdeg);
        b = findViewById(R.id.nextbtn);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String>rst = obj1.getFilteredUniListDeg(filterUniDeg1.this, item);
              //  Toast.makeText(filterUniDeg1.this, "Unis:"+String.valueOf(rst.size()), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(filterUniDeg1.this, uniList.class);
                in.putExtra("arr", rst);
                startActivity(in);

            }
        });

        acc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}