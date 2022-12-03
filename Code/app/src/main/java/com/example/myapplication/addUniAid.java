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
import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.currentUserUni;

public class addUniAid extends AppCompatActivity {
    private EditText e1, e2;
    private University obj;
    private Button b;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_uni_aid);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        e1 = findViewById(R.id.nameAid);
        e2 = findViewById(R.id.detailAid);
        b = findViewById(R.id.NextUniSignUp);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.length()!=0 && e2.length()!=0)
                {
                    if(obj.checkAidUniqueness(addUniAid.this, obj.getName(), e1.getText().toString())) {

                        Intent in = new Intent(addUniAid.this, ManageUniMain.class);
                        obj.addAid(addUniAid.this, obj.getName(), e1.getText().toString(), e2.getText().toString());
                        startActivity(in);
                    }
                    else
                    {
                        Toast.makeText(addUniAid.this, "Aid with entered title already exists", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(addUniAid.this, "All Fields Must Be Filled Correctly", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}