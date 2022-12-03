package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Classes.Department;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.Visitor;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.currentUserUni;

import java.util.ArrayList;

public class loginUni extends AppCompatActivity {

    Button l;
    EditText username;
    EditText password;
    Visitor obj;
    String edu;
    University obj1;
    currentUserUni cu;
    TextView t;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_uni);


        username = findViewById(R.id.usernamee);
        password = findViewById(R.id.unipassword);
        obj = new Visitor();
        l = findViewById(R.id.unilogin);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {

                    if (obj.signInUni(username, password, loginUni.this) == true)
                    {
                        obj1 = obj.makeUni(username, password, loginUni.this);
                        Intent in = new Intent(loginUni.this, homePageUni.class);
                        cu = currentUserUni.getInstance(obj1);
                      //  in.putExtra("name", username.getText().toString());
                        startActivity(in);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(loginUni.this, "Wrong credentials or Account Disabled", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e)
                {

                    Toast.makeText(loginUni.this, "Wrong credentials or Account Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}