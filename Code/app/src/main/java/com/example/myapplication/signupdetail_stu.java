package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Classes.accountCreator;

import java.util.Objects;

public class signupdetail_stu extends AppCompatActivity {

    Button b;
    EditText username;
    EditText email;
    EditText pass1;
    EditText pass2;
    TextView error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupdetail_stu);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        pass1 = findViewById(R.id.editTextTextPassword);
        pass2 = findViewById(R.id.editTextTextPassword2);
        b = findViewById(R.id.button2);
        error = findViewById(R.id.error);

        accountCreator obj = new accountCreator();
        obj.connectToDb(signupdetail_stu.this);




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(email.length()!=0 && username.length()!=0 && pass1.length()!=0 && pass2.length()!=0)
                {
                    if(pass1.length() > 6)
                    {
                        if (Objects.equals(pass1.getText().toString(), pass2.getText().toString())) {
                            if (obj.checkUsername(signupdetail_stu.this, String.valueOf(username.getText())) == true && obj.checkUniquenessEmail(signupdetail_stu.this, String.valueOf(email.getText())) == true) {
                                Toast.makeText(signupdetail_stu.this, "aagya", Toast.LENGTH_SHORT).show();

                                Intent in = new Intent(signupdetail_stu.this, signup_personalinfo_stu.class);

                                in.putExtra("uname", username.getText().toString());
                                in.putExtra("email", email.getText().toString());
                                in.putExtra("pass", pass1.getText().toString());
                                startActivity(in);
                            } else {
                                error.setText("Account with entered username/email already exists");

                                CountDownTimer timer = new CountDownTimer(3000, 1000) {
                                    @Override
                                    public void onTick(long l) {
                                        error.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onFinish() {
                                        error.setVisibility(View.INVISIBLE);
                                    }
                                }.start();
                            }
                        } else {
                            error.setText("Password not same");

                            CountDownTimer timer = new CountDownTimer(3000, 1000) {
                                @Override
                                public void onTick(long l) {
                                    error.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onFinish() {
                                    error.setVisibility(View.INVISIBLE);
                                }
                            }.start();
                        }
                    }
                    else
                    {
                        error.setText("Password length must be greater than 6");

                        CountDownTimer timer = new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long l) {
                                error.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFinish() {
                                error.setVisibility(View.INVISIBLE);
                            }
                        }.start();
                    }
                }
                else
                {
                    error.setText("All fields should be filled correctly");

                    CountDownTimer timer = new CountDownTimer(3000,1000) {
                        @Override
                        public void onTick(long l) {
                            error.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFinish() {
                            error.setVisibility(View.INVISIBLE);
                        }
                    }.start();
                }
            }
        });
    }


}