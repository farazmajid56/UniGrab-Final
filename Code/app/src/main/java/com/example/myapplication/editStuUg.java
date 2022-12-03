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

public class editStuUg extends AppCompatActivity {
    private Button b;
    private ArrayAdapter<String> adapter;
    private String []arr = {"Pre Medical", "Pre Engineering", "ICS","FA","Business"};
    private Spinner acc;
    private String item;
    private EditText marks;
    private Student obj;
    private EditText e;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stu_ug);

        currentUser cu  = currentUser.getInstance(obj, null, null);
        obj = cu.getStu();

        marks = findViewById(R.id.marks);
        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.nextbtn);
        marks.setText(String.valueOf(((UndergradStudent)obj).getMarks()));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(marks.length() != 0) {
                    if (Integer.valueOf(marks.getText().toString()) >= 300 && Integer.valueOf(marks.getText().toString()) <= 1000) {
                        obj.editUndergradStu(editStuUg.this, obj.getName(), Integer.valueOf(marks.getText().toString()), item);
                        ((UndergradStudent)obj).setMarks(Integer.valueOf(marks.getText().toString()));
                        ((UndergradStudent)obj).setSubjectCombo(item);
                        Intent in = new Intent(editStuUg.this, homePage.class);
                        startActivity(in);
                    } else {
                        Toast.makeText(editStuUg.this, "Marks should be >= 300 & <= 1000", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(editStuUg.this, "empty field", Toast.LENGTH_SHORT).show();
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