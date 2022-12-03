package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Classes.accountCreator;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class signup_personalinfo_stu extends AppCompatActivity {

    Button b;
    ArrayAdapter<String> adapter;
    String []arr = {"Graduate", "Undergraduate"};
    Spinner acc;
    String item;
    EditText fname;
    EditText lname;
    EditText date;
    TextView error;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_personalinfo_stu);


        fname = findViewById(R.id.editTextTextPersonName1);
        lname = findViewById(R.id.editTextTextPersonName2);
        date = findViewById(R.id.editTextTextdate);
        error = findViewById(R.id.error);



        String username = getIntent().getExtras().getString("uname");
        String email = getIntent().getExtras().getString("email");
        String pass = getIntent().getExtras().getString("pass");

        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.button2);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fname.length()!=0 && lname.length()!=0 && date.length()!=0)
                {
                    if (item == "Undergraduate") {
                        Intent in = new Intent(signup_personalinfo_stu.this, educationalbg_fsc.class);
                        in.putExtra("date", date.getText().toString());
                        in.putExtra("uname", username);
                        in.putExtra("email", email);
                        in.putExtra("pass", pass);
                        in.putExtra("edutype", item);
                        in.putExtra("fname", fname.getText().toString());
                        in.putExtra("lname", lname.getText().toString());
                        startActivity(in);
                    } else if (item == "Graduate") {
                        Intent in = new Intent(signup_personalinfo_stu.this, educationalBgGrad.class);
                        in.putExtra("date", date.getText().toString());
                        in.putExtra("uname", username);
                        in.putExtra("email", email);
                        in.putExtra("pass", pass);
                        in.putExtra("edutype", item);
                        in.putExtra("fname", fname.getText().toString());
                        in.putExtra("lname", lname.getText().toString());
                        startActivity(in);
                    }
                }
                else
                {
                    error.setText("All fields should be filled correctly");

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
        });
        acc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();
                //  Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}