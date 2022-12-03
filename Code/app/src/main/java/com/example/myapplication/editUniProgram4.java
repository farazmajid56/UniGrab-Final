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
import com.example.myapplication.Classes.feeinfo;

public class editUniProgram4 extends AppCompatActivity {

    private EditText e1, e2, e3, e4;
    private University obj;
    private Button b;
    private feeinfo f;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_program4);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        String d = getIntent().getExtras().getString("dname");
        String p = getIntent().getExtras().getString("pname");

        e1 = findViewById(R.id.prg_name);
        e2 = findViewById(R.id.minmarks);
        e3 = findViewById(R.id.creditHr);
        e4 = findViewById(R.id.feePerCreditHr);
        b = findViewById(R.id.nxt);

        f = obj.getProgFeeInfo(editUniProgram4.this, obj.getName(), d, p);
        float m = obj.getProgGMinCGPA(editUniProgram4.this, obj.getName(), d, p);

        e1.setText(p);
        e2.setText(String.valueOf(m));
        e3.setText(String.valueOf(f.getCreditHrs()));
        e4.setText(String.valueOf(f.getFeePerCdthr()));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.length()!=0 && e2.length()!=0 && e3.length()!=0 && e4.length()!=0)
                {
                    if(Float.valueOf(e2.getText().toString()) >= 1.0f && Float.valueOf(e2.getText().toString()) <= 4.0f && Integer.valueOf(e3.getText().toString()) > 0 && Integer.valueOf(e4.getText().toString()) > 0)
                    {
                        if(!(p.equals(e1.getText().toString())))
                        {
                            if (obj.checkProgramUniqueness(editUniProgram4.this, obj.getName(), d, e1.getText().toString()) == true) {

                                Intent in = new Intent(editUniProgram4.this, ManageUniMain.class);
                                obj.editGProgram(editUniProgram4.this, obj.getName(), d, e1.getText().toString(), Integer.valueOf(e3.getText().toString()), Integer.valueOf(e4.getText().toString()), Float.valueOf(e2.getText().toString()), p);
                                startActivity(in);

                            } else {
                                Toast.makeText(editUniProgram4.this, "Program with entered username already exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if( m != Float.valueOf(e2.getText().toString()) || f.getCreditHrs() != Integer.valueOf(e3.getText().toString()) || f.getFeePerCdthr() != Integer.valueOf(e4.getText().toString()))
                        {
                            Intent in = new Intent(editUniProgram4.this, ManageUniMain.class);
                            obj.editGProgram(editUniProgram4.this, obj.getName(), d, e1.getText().toString(), Integer.valueOf(e3.getText().toString()), Integer.valueOf(e4.getText().toString()), Float.valueOf(e2.getText().toString()), p);
                            Toast.makeText(editUniProgram4.this, "Successfully Edited", Toast.LENGTH_SHORT).show();
                            startActivity(in);
                        }
                        else
                        {
                            Toast.makeText(editUniProgram4.this, "Edit something first", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(editUniProgram4.this, "Invalid fee/gpa credentials", Toast.LENGTH_SHORT).show();

                    }

                }
                else
                {
                    Toast.makeText(editUniProgram4.this, "All Fields Must Be Filled Correctly", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}