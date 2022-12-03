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

import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.currentUserUni;

import java.util.ArrayList;
import java.util.List;

public class editUniProgram2 extends AppCompatActivity {

    private List<String> list;
    private Spinner acc;
    private String item;
    private University obj;
    private Button b;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_program2);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        String str = getIntent().getExtras().getString("dname");

        list  = obj.getDeptPrgms(editUniProgram2.this, str);


        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.next);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.size() != 0) {
                    if (obj.checkProgramUG(editUniProgram2.this, obj.getName(), str, item) == true) {
                        Intent in = new Intent(editUniProgram2.this, editUniProgram3.class);
                        in.putExtra("pname", item);
                        in.putExtra("dname", str);
                        startActivity(in);
                    } else {
                        Toast.makeText(editUniProgram2.this, "in else", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(editUniProgram2.this, editUniProgram4.class);
                        in.putExtra("pname", item);
                        in.putExtra("dname", str);
                        startActivity(in);
                    }
                }
                else
                {
                    Toast.makeText(editUniProgram2.this, "No programs avalaible to select", Toast.LENGTH_SHORT).show();
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