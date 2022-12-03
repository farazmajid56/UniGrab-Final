package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.currentUserUni;
import com.example.myapplication.Classes.profinfo;

public class addFacultyUni extends AppCompatActivity {

    private EditText e1, e2, e3, e4;
    private University obj;
    private Button b;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty_uni);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        String str = getIntent().getExtras().getString("dname");

        e1 = findViewById(R.id.username);
        e2 = findViewById(R.id.email);
        e3 = findViewById(R.id.editTextTextPassword);
        e4 = findViewById(R.id.editTextTextPassword2);
        b = findViewById(R.id.button2);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.length()!=0 && e2.length()!=0 && e3.length()!=0 && e4.length() != 0)
                {
                    if(obj.checkFacultyUniqueness(addFacultyUni.this, obj.getName(), e3.getText().toString()))
                    {
                        Intent in = new Intent(addFacultyUni.this, ManageUniMain.class);
                        obj.addFaculty(addFacultyUni.this, obj.getName(), str, e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString());
                        startActivity(in);
                    }
                    else
                    {
                        Toast.makeText(addFacultyUni.this, "Professor with given email already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(addFacultyUni.this, "All Fields Must Be Filled Correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}