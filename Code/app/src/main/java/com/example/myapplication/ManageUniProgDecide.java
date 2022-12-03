package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ManageUniProgDecide extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    String arr[] = {"Undergraduate", "Graduate"};
    Spinner acc;
    Button b;
    String item;
    String prog = "Graduate";
    @SuppressLint("MissingInflatedId")
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_uni_program);

        int DepAdd = getIntent().getExtras().getInt("Adddep");

        // int DepAdd = 1;

        acc = findViewById(R.id.spinnerUniProgram);
        b = findViewById(R.id.button_next_to_program_Input);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int depadd = DepAdd;

                if(Objects.equals(item, "Undergraduate")) {
                    Intent in = new Intent(ManageUniProgDecide.this, ManageUniProgInput.class);
                    in.putExtra("Program", 0);
                    in.putExtra("Adddep", depadd);
                    startActivity(in);
                }
                if(Objects.equals(item, "Graduate"))
                {
                    Intent in = new Intent(ManageUniProgDecide.this, ManageUniProgInput.class);
                    in.putExtra("Program", 1);
                    in.putExtra("Adddep", depadd);
//                    Toast.makeText(ManageUniProg.this, prog,
//                            Toast.LENGTH_LONG).show();
                    startActivity(in);
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
