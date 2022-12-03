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

import com.example.myapplication.Classes.Evaluator;
import com.example.myapplication.Classes.GraduateStudent;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.UndergradStudent;
import com.example.myapplication.Classes.currentUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class programSelectionEligibility extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private List<String> arr;
    private List<String> arr1;
    private Spinner acc;
    private Button b;
    private String item;
    private Student obj1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_selection_eligibility);

      //  String st = getIntent().getExtras().getString("type");
     //   obj1 = (Student) getIntent().getExtras().getSerializable("object");

        currentUser cu  = currentUser.getInstance(obj1, null, null);
        obj1 = cu.getStu();
        String st = obj1.getEducationType();

     /*   Toast.makeText(this, "Type:"+st, Toast.LENGTH_SHORT).show();
        if(st.equals("Undergraduate"))
        {
            Toast.makeText(this, Integer.toString(((UndergradStudent)obj1).getMarks()), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, Float.toString(((GraduateStudent)obj1).getCgpa()), Toast.LENGTH_SHORT).show();
        }
*/

        arr = new ArrayList<String>();
        obj1.getAllAvalaiblePrograms(this, arr);
      //  Toast.makeText(this, "Size:"+String.valueOf(arr.size()), Toast.LENGTH_SHORT).show();

        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.nextbtn);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String>rst = obj1.getEligibleUniList(programSelectionEligibility.this, item);
             //   Toast.makeText(programSelectionEligibility.this, "Unis:"+String.valueOf(rst.size()), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(programSelectionEligibility.this, uniList.class);
                in.putExtra("arr", rst);
                startActivity(in);

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