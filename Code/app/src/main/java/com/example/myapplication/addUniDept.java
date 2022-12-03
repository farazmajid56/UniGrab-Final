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

public class addUniDept extends AppCompatActivity {

    private EditText e;
    private Button b;
    private University obj;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_uni_dept);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = (University) cu.getU();

        e = findViewById(R.id.n);
        b = findViewById(R.id.next);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e.length() != 0)
                {
                    if (obj.checkUniqueDept(addUniDept.this, obj.getName(),e.getText().toString()) == true) {

                        Intent in = new Intent(addUniDept.this, ManageUniMain.class);
                        obj.addDept(addUniDept.this, obj.getName(), e.getText().toString());
                        startActivity(in);

                    } else {
                        Toast.makeText(addUniDept.this, "Department with entered name already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(addUniDept.this, "Enter a name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}