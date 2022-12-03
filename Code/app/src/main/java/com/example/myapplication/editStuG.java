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
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.UndergradStudent;
import com.example.myapplication.Classes.currentUser;

public class editStuG extends AppCompatActivity {
    private Button b;
    private ArrayAdapter<String> adapter;
    private String []arr = {"BSCS", "BSSE", "BSDS","BBA","BSCV", "BSEE", "BSME"};
    private Spinner acc;
    private String item;
    private EditText cgpa;
    private Student obj;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stu_g);

        currentUser cu  = currentUser.getInstance(obj, null, null);
        obj = cu.getStu();

        cgpa = findViewById(R.id.editTextTextcgpa);
        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.nextbtn);

        cgpa.setText(String.valueOf(((GraduateStudent)obj).getCgpa()));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(cgpa.length() != 0) {
                    if (Float.valueOf(cgpa.getText().toString()) >= 1.0 && Float.valueOf(cgpa.getText().toString()) <= 4.0) {
                        obj.editGradStu(editStuG.this, obj.getName(), Float.valueOf(cgpa.getText().toString()), item);
                        ((GraduateStudent)obj).setCgpa(Float.valueOf(cgpa.getText().toString()));
                        ((GraduateStudent)obj).setBsDeg(item);
                        Intent in = new Intent(editStuG.this, homePage.class);
                        startActivity(in);
                    } else {
                        Toast.makeText(editStuG.this, "Not valid credential", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(editStuG.this, "empty field", Toast.LENGTH_SHORT).show();
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