package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;

import java.util.ArrayList;
import java.util.List;

public class compareUnis extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arr;
    private ArrayList<String> arr1;
    private Spinner acc, acc1;
    private Button b;
    private String item1, item2;
    private Student obj1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_unis);

        currentUser cu  = currentUser.getInstance(obj1, null, null);
        obj1 = cu.getStu();

        arr = new ArrayList<String>();
        obj1.getUnis(this, arr);

        acc = findViewById(R.id.uni1);
        acc1 = findViewById(R.id.uni2);
        b = findViewById(R.id.nextbtn);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);
        acc1.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(item1.equals(item2))) {
                    Intent in = new Intent(compareUnis.this, compareUni1.class);
                    in.putExtra("name1", item1);
                    in.putExtra("name2", item2);
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(compareUnis.this, "Cannot select same universities", Toast.LENGTH_SHORT).show();
                }

            }
        });

        acc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item1 = parent.getItemAtPosition(i).toString();
                //  Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        acc1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item2 = parent.getItemAtPosition(i).toString();
                //  Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}