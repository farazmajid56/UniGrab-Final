package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Objects;

public class selectAccount extends AppCompatActivity{

    private ArrayAdapter<String> adapter;
    private String arr[] = {"Student", "University", "Admin"};
    private Spinner acc;
    private Button b;
    private String item;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_account);

        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.next);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.equals(item, "Student")) {
                    Intent in = new Intent(selectAccount.this, loginCredentialsStudent.class);
                    startActivity(in);
                    finish();
                }
                else if(Objects.equals(item, "University"))
                {
                    Intent in = new Intent(selectAccount.this, loginUni.class);
                    startActivity(in);
                    finish();
                }
                else
                {
                    Intent in = new Intent(selectAccount.this, loginAdmin.class);
                    startActivity(in);
                    finish();
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

     /*   auto = findViewById(R.id.act);
        arr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,accounts);
        auto.setAdapter(arr);
        auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }
        });*/


    }

}