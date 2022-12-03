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

public class editUniDept2 extends AppCompatActivity {

    private EditText e;
    private Button b;
    private University obj;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_dept2);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = (University) cu.getU();

        e = findViewById(R.id.n);
        b = findViewById(R.id.next);

        String str = getIntent().getExtras().getString("dname");
        e.setText(str);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e.length() != 0)
                {
                    if(!(str.equals(e.getText().toString()))) {
                        if (obj.checkUniqueDept(editUniDept2.this, obj.getName(),e.getText().toString()) == true) {

                                   /* Toast.makeText(University_SignUp.this, uname.getText().toString(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(University_SignUp.this, email.getText().toString(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(University_SignUp.this, pass.getText().toString(), Toast.LENGTH_SHORT).show();*/

                            Intent in = new Intent(editUniDept2.this, ManageUniMain.class);
                            obj.editDept(editUniDept2.this, obj.getName(), e.getText().toString() , str);
                            startActivity(in);

                        } else {
                            Toast.makeText(editUniDept2.this, "Account with entered username already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(editUniDept2.this, "Enter a new name first", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(editUniDept2.this, "Eter a name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}