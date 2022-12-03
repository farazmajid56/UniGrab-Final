package com.example.myapplication;

import static com.example.myapplication.MainActivity.Classes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Classes.*;
import com.example.myapplication.adapterAdminHome;
//import com.google.firebase.firestore.auth.User;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class Admin_Home extends AppCompatActivity {
    accountManager AM = new accountManager();
    ArrayList<User> uList = new ArrayList<>();

    @Override
    public void onBackPressed() {
        Intent back = new Intent(Admin_Home.this, homePageAdmin.class);
        back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(back);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

//        EditText _email = findViewById(R.id.admin_email);
//        EditText _pass = findViewById(R.id.admin_pass);
//        Button login = findViewById(R.id.admin_login);
//        TextView conStatus = findViewById(R.id.con_status);
//        TextView _welcome = findViewById(R.id.welcome);
//        String email = _email.getText().toString();
//        String pass = _pass.getText().toString();

        getAllUsers();

        Toast.makeText(this,"Successful Operation", Toast.LENGTH_SHORT).show();

        RecyclerView RV = findViewById(R.id.Admin_RV);

        adapterAdminHome ad = new adapterAdminHome(this,uList);
        RV.setAdapter(ad);
        RV.setLayoutManager(new LinearLayoutManager(this ));

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy)
    }

    protected void getAllUsers() {
        if(AM.viewAccountList(this,uList)){
            Toast.makeText(this,"Successful Operation", Toast.LENGTH_SHORT).show();
        }
        else{

            Toast.makeText(this,"Error in DB Connection", Toast.LENGTH_SHORT).show();
        }
    }
}
