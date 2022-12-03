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
import com.example.myapplication.Classes.alumniInfo;
import com.example.myapplication.Classes.currentUserUni;

public class editUniAid2 extends AppCompatActivity {

    private EditText e1, e2;
    private University obj;
    private Button b;
    private aidInfo ai;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_aid2);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        ai = (aidInfo) getIntent().getExtras().getSerializable("a");

        e1 = findViewById(R.id.nameAid);
        e2 = findViewById(R.id.detailAid);
        b = findViewById(R.id.NextUniSignUp);


        e1.setText(ai.getName());
        e2.setText(ai.getDetail());

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.length()!=0 && e2.length()!=0)
                {
                    if(!(ai.getName().equals(e1.getText().toString())))
                    {
                        if(obj.checkAidUniqueness(editUniAid2.this, obj.getName(), e1.getText().toString())) {

                            Intent in = new Intent(editUniAid2.this, ManageUniMain.class);
                            obj.editAid(editUniAid2.this, obj.getName(), e1.getText().toString(), e2.getText().toString(), ai.getName());
                            Toast.makeText(editUniAid2.this, "Edited successfully", Toast.LENGTH_SHORT).show();
                            startActivity(in);
                        }
                        else
                        {
                            Toast.makeText(editUniAid2.this, "Aid with entered title already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Intent in = new Intent(editUniAid2.this, ManageUniMain.class);
                        obj.editAid(editUniAid2.this, obj.getName(), e1.getText().toString(), e2.getText().toString(), ai.getName());
                        Toast.makeText(editUniAid2.this, "Edited successfully", Toast.LENGTH_SHORT).show();
                        startActivity(in);
                    }
                }
                else
                {
                    Toast.makeText(editUniAid2.this, "All Fields Must Be Filled Correctly", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}