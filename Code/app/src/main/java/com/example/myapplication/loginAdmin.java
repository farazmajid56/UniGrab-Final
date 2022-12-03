package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Classes.Admin;
import com.example.myapplication.Classes.Visitor;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.currentUserAdmin;

public class loginAdmin extends AppCompatActivity {

    private Button l;
    private EditText username;
    private EditText password;
    private Visitor obj;
    private Admin obj1;
    private currentUserAdmin ca;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        username = findViewById(R.id.usernameadmin);
        password = findViewById(R.id.stuPassword);
        obj = new Visitor();
        l = findViewById(R.id.loginadminn);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (obj.signInAdmin(username, password, loginAdmin.this) == true) {

                        obj1 = obj.makeAdmin(username, password, loginAdmin.this);
                        ca = currentUserAdmin.getInstance(obj1);
                        Intent in = new Intent(loginAdmin.this, homePageAdmin.class);
                        startActivity(in);
                        finish();

                    } else {
                        Toast.makeText(loginAdmin.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(loginAdmin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}