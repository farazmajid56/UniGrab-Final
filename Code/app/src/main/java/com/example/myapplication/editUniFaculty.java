package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.currentUserUni;

import java.util.ArrayList;
import java.util.List;

public class editUniFaculty extends AppCompatActivity {

    private List<String> list;
    private Spinner acc;
    private String item;
    private University obj;
    private Button b;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_faculty);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        list = new ArrayList<>();
        obj.getDeptsUni(editUniFaculty.this, obj.getName(), list);


        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.next);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.size() != 0) {
                    Intent in = new Intent(editUniFaculty.this, editUniFaculty2.class);
                    in.putExtra("dname", item);
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(editUniFaculty.this, "No department avalaible to select", Toast.LENGTH_SHORT).show();
                }
            }
        });

        acc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();
                //  Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}