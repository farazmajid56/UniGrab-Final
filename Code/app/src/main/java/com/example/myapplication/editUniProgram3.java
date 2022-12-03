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

import java.util.Objects;

public class editUniProgram3 extends AppCompatActivity {

    private EditText e1, e2, e3, e4;
    private University obj;
    private Button b;
    private feeinfo f;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_program3);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        String d = getIntent().getExtras().getString("dname");
        String p = getIntent().getExtras().getString("pname");

        e1 = findViewById(R.id.prg_name);
        e2 = findViewById(R.id.minmarks);
        e3 = findViewById(R.id.creditHr);
        e4 = findViewById(R.id.feePerCreditHr);
        b = findViewById(R.id.nxt);

        f = obj.getProgFeeInfo(editUniProgram3.this, obj.getName(), d, p);
        int m = obj.getProgUGMinMarks(editUniProgram3.this, obj.getName(), d, p);

        e1.setText(p);
        e2.setText(String.valueOf(m));
        e3.setText(String.valueOf(f.getCreditHrs()));
        e4.setText(String.valueOf(f.getFeePerCdthr()));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.length()!=0 && e2.length()!=0 && e3.length()!=0 && e4.length()!=0)
                {
                    if(Integer.valueOf(e2.getText().toString()) > 400 && Integer.valueOf(e2.getText().toString()) <= 1100 && Integer.valueOf(e3.getText().toString()) > 0 && Integer.valueOf(e4.getText().toString()) > 0)
                    {
                        if(!(p.equals(e1.getText().toString())))
                        {
                            if (obj.checkProgramUniqueness(editUniProgram3.this, obj.getName(), d, e1.getText().toString()) == true) {

                               /* Toast.makeText(University_SignUp.this, uname.getText().toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(University_SignUp.this, email.getText().toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(University_SignUp.this, pass.getText().toString(), Toast.LENGTH_SHORT).show();*/

                                Intent in = new Intent(editUniProgram3.this, ManageUniMain.class);
                                obj.editUGProgram(editUniProgram3.this, obj.getName(), d, e1.getText().toString(), Integer.valueOf(e3.getText().toString()), Integer.valueOf(e4.getText().toString()), Integer.valueOf(e2.getText().toString()), p);
                                startActivity(in);

                            } else {
                                Toast.makeText(editUniProgram3.this, "Program with entered username already exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if( m != Integer.valueOf(e2.getText().toString()) || f.getCreditHrs() != Integer.valueOf(e3.getText().toString()) || f.getFeePerCdthr() != Integer.valueOf(e4.getText().toString()))
                        {
                            Intent in = new Intent(editUniProgram3.this, ManageUniMain.class);
                            obj.editUGProgram(editUniProgram3.this, obj.getName(), d, e1.getText().toString(), Integer.valueOf(e3.getText().toString()), Integer.valueOf(e4.getText().toString()), Integer.valueOf(e2.getText().toString()), p);
                            Toast.makeText(editUniProgram3.this, "Successfully Edited", Toast.LENGTH_SHORT).show();
                            startActivity(in);
                        }
                        else
                        {
                            Toast.makeText(editUniProgram3.this, "Edit something first", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(editUniProgram3.this, "Invalid fee/marks credentials", Toast.LENGTH_SHORT).show();

                    }

                }
                else
                {
                    Toast.makeText(editUniProgram3.this, "All Fields Must Be Filled Correctly", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}