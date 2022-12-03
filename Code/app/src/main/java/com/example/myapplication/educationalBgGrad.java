package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Classes.GraduateStudent;
import com.example.myapplication.Classes.UndergradStudent;
import com.example.myapplication.Classes.Visitor;
import com.example.myapplication.Classes.accountCreator;

import java.io.Serializable;

public class educationalBgGrad extends AppCompatActivity {
    private Button b;
    private ArrayAdapter<String> adapter;
    private String []arr = {"BSCS", "BSSE", "BSDS","BBA","BSCV", "BSEE", "BSME"};
    private Spinner acc;
    private String item;
    private EditText cgpa;
    private Visitor obj;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_bg_grad);

        obj = new Visitor();

        String username = getIntent().getExtras().getString("uname");
        String email = getIntent().getExtras().getString("email");
        String pass = getIntent().getExtras().getString("pass");
        String edutype = getIntent().getExtras().getString("edutype");
        String fname = getIntent().getExtras().getString("fname");
        String lname = getIntent().getExtras().getString("lname");
        String date = getIntent().getExtras().getString("date");


        cgpa = findViewById(R.id.editTextTextcgpa);
        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.nextbtn);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(cgpa.length() != 0) {
                    if (Float.valueOf(cgpa.getText().toString()) >= 1.0 && Float.valueOf(cgpa.getText().toString()) <= 4.0) {
                        obj.insertGStu(educationalBgGrad.this, username, email, pass, edutype, date, fname, lname, Float.valueOf(cgpa.getText().toString()), item, 0, 0);
                        Intent in = new Intent(educationalBgGrad.this, MainActivity.class);
                        startActivity(in);
                    } else {
                        Toast.makeText(educationalBgGrad.this, "Not valid credential", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(educationalBgGrad.this, "empty field", Toast.LENGTH_SHORT).show();
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