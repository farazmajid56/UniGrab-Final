package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.currentUserUni;

public class editUniPersonal extends AppCompatActivity {

    private EditText e1, rank, e3, e4, e5, e6, e7;
    private University obj;
    private Button b;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_uni_personal);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = cu.getU();

        b = findViewById(R.id.SignUpButton);
        e1 = findViewById(R.id.editTextPhone);
        rank = findViewById(R.id.editTextPh);
        e3 = findViewById(R.id.editTextlife);
        e4 = findViewById(R.id.latitUniSignUp);
        e5 = findViewById(R.id.longitUniSignUp);
        e6 = findViewById(R.id.editTextfee);
        e7 = findViewById(R.id.addressuni);

        String s1 = obj.getLocation();
        e7.setText(s1);
        String s3 = obj.getPhone();
        e1.setText(s3);
        String s4 = String.valueOf(obj.getRanking());
        rank.setText(s4);
        String s6 = String.valueOf(obj.getLatitude());
        e4.setText(s6);
        String s7 = String.valueOf(obj.getLongitude());
        e5.setText(s7);
        String s8 = String.valueOf(obj.getAdmissionFee());
        e6.setText(s8);
        String s9 = obj.getCampusLife();
        e3.setText(s9);


        String uname = obj.getName();

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(e1.length()!=0 && rank.length()!=0 && e3.length()!=0 && e4.length()!=0 && e5.length()!=0 && e7.length()!=0)
                {

                    Intent in = new Intent(editUniPersonal.this, ManageUniMain.class);
                    obj.editPersonal(editUniPersonal.this,e1.getText().toString(),e3.getText().toString(),Integer.valueOf(rank.getText().toString()),Integer.valueOf(e6.getText().toString()), Double.valueOf(e4.getText().toString()),Double.valueOf(e5.getText().toString()), e7.getText().toString(), uname);

                    cu.setUniFee(Integer.valueOf(e6.getText().toString()));
                    cu.setUniRank(Integer.valueOf(rank.getText().toString()));
                    cu.setUniPh(e1.getText().toString());
                    cu.setUniLife(e3.getText().toString());
                    cu.setUniLoc(e7.getText().toString());
                    cu.setUniLat(Double.valueOf(e4.getText().toString()));
                    cu.setUniLon(Double.valueOf(e5.getText().toString()));

                    startActivity(in);

                }
                else
                {
                    Toast.makeText(editUniPersonal.this, "All fields should be filled correctly", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}