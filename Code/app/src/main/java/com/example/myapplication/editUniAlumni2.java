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
import com.example.myapplication.Classes.feeinfo;

public class editUniAlumni2 extends AppCompatActivity {

    private EditText e1, e2, e3;
    private University obj;
    private Button b;
    private alumniInfo ai;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_alumni2);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        ai = (alumniInfo) getIntent().getExtras().getSerializable("a");

        e1 = findViewById(R.id.alumname);
        e2 = findViewById(R.id.pc);
        e3 = findViewById(R.id.editBatch);
        b = findViewById(R.id.button2);


        e1.setText(ai.getName());
        e2.setText(ai.getCompany());
        e3.setText(String.valueOf(ai.getBatch()));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.length()!=0 && e2.length()!=0 && e3.length()!=0)
                {
                    if(Integer.valueOf(e3.getText().toString()) > 1980 && Integer.valueOf(e3.getText().toString()) <= 2022)
                    {
                        Intent in = new Intent(editUniAlumni2.this, ManageUniMain.class);
                        obj.editAlumni(editUniAlumni2.this, obj.getName(), e1.getText().toString(), e2.getText().toString(), Integer.valueOf(e3.getText().toString()), ai.getName());
                        startActivity(in);
                    }
                    else
                    {
                        Toast.makeText(editUniAlumni2.this, "Invalid batch credential", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(editUniAlumni2.this, "All Fields Must Be Filled Correctly", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}