package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Classes.GraduateStudent;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.UndergradStudent;
import com.example.myapplication.Classes.Visitor;
import com.example.myapplication.Classes.currentUser;

import java.io.Serializable;

public class loginCredentialsStudent extends AppCompatActivity {

    private Button l;
    private EditText username;
    private EditText password;
    private Visitor obj;
    private String edu;
    private Student obj1;
    private currentUser cu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_credentials_student);

        username = findViewById(R.id.username);
        password = findViewById(R.id.stuPassword);
        obj = new Visitor();
        l = findViewById(R.id.loginStu);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {

                    if (obj.signInStu(username, password, loginCredentialsStudent.this) == true)
                    {
                            edu = obj.getEducationType(username, password, loginCredentialsStudent.this);
                            if (edu.equals("Undergraduate")) {
                                //    Toast.makeText(loginCredentialsStudent.this, "edutype:" + edu, Toast.LENGTH_SHORT).show();
                                obj1 = (Student) obj.makeUndergradObj(username, password, loginCredentialsStudent.this);
                                //  Toast.makeText(loginCredentialsStudent.this, Integer.toString(((UndergradStudent)obj1).getMarks()), Toast.LENGTH_SHORT).show();
                                cu = currentUser.getInstance(obj1, null, null);

                            } else {
                                //  Toast.makeText(loginCredentialsStudent.this, "edutype:" + edu, Toast.LENGTH_SHORT).show();
                                obj1 = (Student) obj.makeGradObj(username, password, loginCredentialsStudent.this);
                                //   Toast.makeText(loginCredentialsStudent.this, Float.toString(((GraduateStudent)obj1).getCgpa()), Toast.LENGTH_SHORT).show();
                                cu = currentUser.getInstance(obj1, null, null);
                            }
                            Intent in = new Intent(loginCredentialsStudent.this, homePage.class);
                            in.putExtra("edutype", edu);
                            //   in.putExtra("stu", obj1);
                            startActivity(in);
                            finish();
                    }
                    else
                    {
                        Toast.makeText(loginCredentialsStudent.this, "Wrong credentials or Account Disabled", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e)
                {

                    Toast.makeText(loginCredentialsStudent.this, "Wrong credentials or Account Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}