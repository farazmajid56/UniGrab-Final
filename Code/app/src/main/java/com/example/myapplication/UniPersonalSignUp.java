package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.Visitor;
import com.example.myapplication.Classes.accountCreator;

public class UniPersonalSignUp extends AppCompatActivity
{
    private Button b;
    private String uname;
    private EditText e1, rank, e3, e4, e5, e6, e7;
    private String addr;
    private String email;
    private String pass;
    private Visitor obj;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_personal_sign_up);

        obj = new Visitor();
        // obj.connectToDb(UniPersonalSignUp.this);


        b = findViewById(R.id.SignUpButton);
        e1 = findViewById(R.id.editTextPhone);
        rank = findViewById(R.id.editTextPh);
        e3 = findViewById(R.id.editTextlife);
        e4 = findViewById(R.id.latitUniSignUp);
        e5 = findViewById(R.id.longitUniSignUp);
        e6 = findViewById(R.id.editTextfee);
        e7 = findViewById(R.id.addressuni);

        uname=getIntent().getExtras().getString("name");
        email=getIntent().getExtras().getString("email");
        pass=getIntent().getExtras().getString("pass");

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(e1.length()!=0 && rank.length()!=0 && e3.length()!=0 && e4.length()!=0 && e5.length()!=0 && e7.length()!=0)
                {

                    Intent in = new Intent(UniPersonalSignUp.this, MainActivity.class);
                  //  Toast.makeText(UniPersonalSignUp.this, "aagya", Toast.LENGTH_SHORT).show();
                    obj.insertUni(UniPersonalSignUp.this,e1.getText().toString(),e3.getText().toString(),Integer.valueOf(rank.getText().toString()),Integer.valueOf(e6.getText().toString()),uname,Double.valueOf(e4.getText().toString()),Double.valueOf(e5.getText().toString()), e7.getText().toString(),email,pass);
                    in.putExtra("name",uname);
                    startActivity(in);

                }
                else
                {
                    Toast.makeText(UniPersonalSignUp.this, "All fields should be filled correctly", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}