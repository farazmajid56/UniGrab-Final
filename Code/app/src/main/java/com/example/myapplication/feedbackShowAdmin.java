package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.Classes.Admin;
import com.example.myapplication.Classes.accountManager;
import com.example.myapplication.Classes.currentUserAdmin;

import java.util.ArrayList;

public class feedbackShowAdmin extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> listfeedback;
    private Admin obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_show_admin);

        listfeedback = new ArrayList<>();

        currentUserAdmin ca  = currentUserAdmin.getInstance(obj);
        obj = ca.getA();

        obj.getfeedback(this, listfeedback);

        listView = findViewById(R.id.fblist);
        adapterFeedback ad = new adapterFeedback(this, R.layout.feedback_list_row, listfeedback);
        listView.setAdapter(ad);
    }
}