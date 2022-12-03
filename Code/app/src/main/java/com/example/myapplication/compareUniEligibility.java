package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;

import java.util.ArrayList;
import java.util.List;

public class compareUniEligibility extends AppCompatActivity {
    private ArrayAdapter<String> adapter, adapter1;
    private Spinner acc, acc1;
    private Button b;
    private String item, item1;
    private Student obj1;
    private List<String> arr, arr1;
    private TextView t1, t2, t3, t4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_uni_eligibility);

        currentUser cu  = currentUser.getInstance(obj1, null, null);
        obj1 = cu.getStu();
        String st = obj1.getEducationType();

        String s1 = getIntent().getExtras().getString("name1");
        String s2 = getIntent().getExtras().getString("name2");

        arr = new ArrayList<String>();
        obj1.getAvalaiblePrograms(this, s1, arr);

        arr1 = new ArrayList<String>();
        obj1.getAvalaiblePrograms(this, s2, arr1);



        t1 = findViewById(R.id.uni1);
        t2 = findViewById(R.id.uni2);
        t3 = findViewById(R.id.program);
        t4 = findViewById(R.id.program2);

        t1.setText(s1);
        t2.setText(s2);


        acc = findViewById(R.id.spinnerprogram);
        acc1 = findViewById(R.id.spinnerprogram2);
        b = findViewById(R.id.btnEligibility);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        acc.setAdapter(adapter);
        acc1.setAdapter(adapter1);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (arr.size() != 0 && arr1.size() != 0) {

                    if (obj1.getEligiblityStatus(compareUniEligibility.this, item, s1) == true) {
                        t3.setText("You are eligible to apply in selected program of " + s1);

                        CountDownTimer timer = new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long l) {
                                t3.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFinish() {
                                t3.setVisibility(View.INVISIBLE);
                            }
                        }.start();
                    }
                    if (obj1.getEligiblityStatus(compareUniEligibility.this, item, s1) == false) {
                        t3.setText("You are not eligible to apply in selected program of " + s1);

                        CountDownTimer timer = new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long l) {
                                t3.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFinish() {
                                t3.setVisibility(View.INVISIBLE);
                            }
                        }.start();
                    }
                    if (obj1.getEligiblityStatus(compareUniEligibility.this, item1, s2) == true) {
                        t4.setText("You are eligible to apply in selected program of " + s2);

                        CountDownTimer timer = new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long l) {
                                t4.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFinish() {
                                t4.setVisibility(View.INVISIBLE);
                            }
                        }.start();
                    }
                    if (obj1.getEligiblityStatus(compareUniEligibility.this, item1, s2) == false) {
                        t4.setText("You are not eligible to apply in selected program of " + s2);

                        CountDownTimer timer = new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long l) {
                                t4.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFinish() {
                                t4.setVisibility(View.INVISIBLE);
                            }
                        }.start();
                    }
                } else {
                    Toast.makeText(compareUniEligibility.this, "University does not have any program avalaible", Toast.LENGTH_SHORT).show();
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

        acc1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item1 = parent.getItemAtPosition(i).toString();
                //  Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}