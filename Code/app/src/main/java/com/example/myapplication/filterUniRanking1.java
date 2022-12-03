package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;

import java.util.ArrayList;

public class filterUniRanking1 extends AppCompatActivity {

    EditText t1, t2;
    Button b;
    Student obj1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_uni_ranking1);

        currentUser cu  = currentUser.getInstance(obj1, null, null);
        obj1 = cu.getStu();

        t1 = findViewById(R.id.lowerbound);
        t2 = findViewById(R.id.upperbound);
        b = findViewById(R.id.ok);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.valueOf(t1.getText().toString()) < Integer.valueOf(t2.getText().toString()))
                {
                    ArrayList<String> rst = obj1.getFilteredUniListRanking(filterUniRanking1.this, Integer.valueOf(t1.getText().toString()), Integer.valueOf(t2.getText().toString()));
                    Intent in = new Intent(filterUniRanking1.this, uniList.class);
                    in.putExtra("arr", rst);
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(filterUniRanking1.this, "Lower bound must be smaller than upper bound", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}