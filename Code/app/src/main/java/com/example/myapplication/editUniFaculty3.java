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
import com.example.myapplication.Classes.alumniInfo;
import com.example.myapplication.Classes.currentUserUni;
import com.example.myapplication.Classes.profinfo;

public class editUniFaculty3 extends AppCompatActivity {

    private EditText e1, e2, e3, e4;
    private University obj;
    private Button b;
    private profinfo ai;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_faculty3);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        ai = (profinfo) getIntent().getExtras().getSerializable("a");

        String str = getIntent().getExtras().getString("dname");

        e1 = findViewById(R.id.username);
        e2 = findViewById(R.id.email);
        e3 = findViewById(R.id.editTextTextPassword);
        e4 = findViewById(R.id.editTextTextPassword2);
        b = findViewById(R.id.button2);


        e1.setText(ai.getFname());
        e2.setText(ai.getLname());
        e3.setText(ai.getEmail());
        e4.setText(ai.getDesignation());

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.length()!=0 && e2.length()!=0 && e3.length()!=0 && e4.length() != 0)
                {
                    if(!(ai.getEmail().equals(e3.getText().toString())))
                    {
                        if(obj.checkFacultyUniqueness(editUniFaculty3.this, obj.getName(), e3.getText().toString()))
                        {
                            Intent in = new Intent(editUniFaculty3.this, ManageUniMain.class);
                            obj.editFaculty(editUniFaculty3.this, obj.getName(), str, e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString(), ai.getEmail());
                            startActivity(in);
                        }
                        else
                        {
                            Toast.makeText(editUniFaculty3.this, "Professor with given email already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Intent in = new Intent(editUniFaculty3.this, ManageUniMain.class);
                        obj.editFaculty(editUniFaculty3.this, obj.getName(), str, e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString(), ai.getEmail());
                        startActivity(in);
                    }
                }
                else
                {
                    Toast.makeText(editUniFaculty3.this, "All Fields Must Be Filled Correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}