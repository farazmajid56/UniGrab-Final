package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


public class MainActivity extends AppCompatActivity {

    private Button b;
    private Button b2;

    private TextView textView;
    private static String ip = "10.0.2.2";       //ALWAYS CHANGE IP TO CURRENT WIFI
    private static String port = "1433";
    public static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "UniGrab";
    private static String username = "sa";
    private static String password = "123456";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;

    @Override
    public void onBackPressed() {
        Intent back = new Intent(MainActivity.this, MainActivity.class);
        //back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(back);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);
        b = findViewById(R.id.button4);

        b2 = findViewById(R.id.button5);

        /*description_webscrape dw = new description_webscrape();
        dw.execute();*/


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
           // textView.setText("Success");

        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
            textView.setText("Error");
        } catch (SQLException e) {
            e.printStackTrace();
            textView.setText("Failure");
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, selectAccount.class);
                startActivity(in);
                finish();
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            Intent in = new Intent(MainActivity.this, selectAccount_signup.class);
            startActivity(in);

            }
        });


    }


  /*  private class description_webscrape extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            org.jsoup.nodes.Document document = null;
            try {
                document = (Document) Jsoup.connect("https://www.hec.gov.pk/english/scholarshipsgrants/postdoc/Pages/default.aspx").get();
            } catch (IOException e) {

            }
            org.jsoup.select.Elements elements = document.getElementsByClass("ms-rtestate-field");
            String extracted = elements.text();
            textView.setText(extracted);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {

        }

    }*/



}

