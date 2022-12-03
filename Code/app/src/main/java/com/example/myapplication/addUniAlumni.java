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

public class addUniAlumni extends AppCompatActivity {
    private EditText e1, e2, e3;
    private University obj;
    private Button b;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_uni_alumni);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        e1 = findViewById(R.id.alumname);
        e2 = findViewById(R.id.pc);
        e3 = findViewById(R.id.editBatch);
        b = findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.length()!=0 && e2.length()!=0 && e3.length()!=0)
                {
                    if(Integer.valueOf(e3.getText().toString()) > 1980 && Integer.valueOf(e3.getText().toString()) <= 2022)
                    {
                        Intent in = new Intent(addUniAlumni.this, ManageUniMain.class);
                        obj.addAlumni(addUniAlumni.this, obj.getName(), e1.getText().toString(), e2.getText().toString(), Integer.valueOf(e3.getText().toString()));
                        startActivity(in);
                    }
                    else
                    {
                        Toast.makeText(addUniAlumni.this, "Invalid batch credential", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(addUniAlumni.this, "All Fields Must Be Filled Correctly", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}