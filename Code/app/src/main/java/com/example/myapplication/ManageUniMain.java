package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.alumniInfo;
import com.example.myapplication.Classes.currentUserUni;
import com.example.myapplication.Classes.feeinfo;
import com.example.myapplication.Classes.reviewInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManageUniMain extends AppCompatActivity {

    private Button bt_Dep;
    private Button bt_Prog, btAlumni, btFaculty, btAid, btAcc, btPrsnl, back;
    private ArrayList<String> depts;
    private List<alumniInfo> arr;
    private List<feeinfo> fees;
    private List<aidInfo> aid;
    private List<reviewInfo> reviewInfos;
    private University obj;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_uni_main);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = (University) cu.getU();

        String s = obj.getName();

        depts = new ArrayList<String>();
        arr = new ArrayList<alumniInfo>();
        fees = new ArrayList<feeinfo>();
        aid = new ArrayList<aidInfo>();
        reviewInfos = new ArrayList<reviewInfo>();

        obj.getUni(ManageUniMain.this, s, depts, arr, fees, aid, reviewInfos);


        bt_Dep = findViewById(R.id.button_department);
        bt_Prog = findViewById(R.id.button_Program);
        btAcc = findViewById(R.id.edtUser);
        btPrsnl = findViewById(R.id.edtPersonal);
        btAid = findViewById(R.id.edtAid);
        btFaculty = findViewById(R.id.button_Faculty);
        btAlumni = findViewById(R.id.button_Alumini);
        back = findViewById(R.id.bck);


        btAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ManageUniMain.this, editUniAccount.class);
                startActivity(in);
            }
        });

        btPrsnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ManageUniMain.this, editUniPersonal.class);
                startActivity(in);
            }
        });

        bt_Dep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ManageUniMain.this, manageUniDept.class);
                startActivity(in);
            }
        });

        bt_Prog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ManageUniMain.this, manageUniProgram.class);
                startActivity(in);
            }
        });

        btAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ManageUniMain.this, manageUniAlumni.class);
                in.putExtra("a", (Serializable) arr);
                startActivity(in);
            }
        });

        btFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ManageUniMain.this, manageUniFaculty.class);
                startActivity(in);
            }
        });

        btAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ManageUniMain.this, manageUniAid.class);
                in.putExtra("a", (Serializable) aid);
                startActivity(in);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ManageUniMain.this, homePageUni.class);
                startActivity(in);
            }
        });


    }

}
