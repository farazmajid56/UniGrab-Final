package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class manageUniProgram extends AppCompatActivity {

    private Button b1, b2, b3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_uni_program);

        b1 = findViewById(R.id.addprg);
        b2 = findViewById(R.id.edtprg);
        b3 = findViewById(R.id.delprg);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(manageUniProgram.this, ManageUniProgDecide.class);
                in.putExtra("Adddep", 0);
                startActivity(in);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(manageUniProgram.this, editUniProgram.class);
                startActivity(in);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(manageUniProgram.this, ManageUniProgDel.class);
                startActivity(in);
            }
        });
    }
}