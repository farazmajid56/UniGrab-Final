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

import java.util.Objects;

public class editUniAccount extends AppCompatActivity {

    private University obj;
    private EditText e1, e2, e3,e4;
    private Button b;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_account);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = (University) cu.getU();

        String name = obj.getName();
        String email = obj.getEmail();
        String pass = obj.getPassword();

        e1 = findViewById(R.id.UsernameUniSignUp);
        e2 = findViewById(R.id.EmailUniSignUp);
        e3 = findViewById(R.id.PasswordUniSignUp);
        e4 = findViewById(R.id.PasswordReEnterUniSign);
        b = findViewById(R.id.NextUniSignUp);

        e1.setText(name);
        e2.setText(email);
        e3.setText(pass);
        e4.setText(pass);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.length()!=0 && e2.length()!=0 && e3.length()!=0 && e4.length()!=0)
                {
                    if(e3.length() > 6)
                    {
                        if (Objects.equals(e3.getText().toString(), e4.getText().toString()))
                        {
                            if(!(name.equals(e1.getText().toString())))
                            {
                                if (obj.checkName(editUniAccount.this, e1.getText().toString()) == true) {

                                   /* Toast.makeText(University_SignUp.this, uname.getText().toString(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(University_SignUp.this, email.getText().toString(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(University_SignUp.this, pass.getText().toString(), Toast.LENGTH_SHORT).show();*/

                                    Intent in = new Intent(editUniAccount.this, ManageUniMain.class);
                                    obj.editAcc(editUniAccount.this, e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), name);

                                    cu.setUniName(e1.getText().toString());
                                    cu.setUniEmail(e2.getText().toString());
                                    cu.setUniPass(e3.getText().toString());
                                   /* obj.setUsername(e1.getText().toString());
                                    obj.setEmail(e2.getText().toString());
                                    obj.setPassword(e3.getText().toString());*/

                                    startActivity(in);

                                } else {
                                    Toast.makeText(editUniAccount.this, "Account with entered username already exists", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else if( (!(email.equals(e2.getText().toString()))) || (!(pass.equals(e3.getText().toString()))) )
                            {
                                Intent in = new Intent(editUniAccount.this, ManageUniMain.class);
                                obj.editAcc(editUniAccount.this, e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), name);
                                Toast.makeText(editUniAccount.this, "Successfully Edited", Toast.LENGTH_SHORT).show();
                                startActivity(in);
                            }
                            else
                            {
                                Toast.makeText(editUniAccount.this, "Edit something first", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(editUniAccount.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else
                    {
                        Toast.makeText(editUniAccount.this, "Passwords Must Be > 6 Characters", Toast.LENGTH_SHORT).show();

                    }

                }
                else
                {
                    Toast.makeText(editUniAccount.this, "All Fields Must Be Filled Correctly", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }
}