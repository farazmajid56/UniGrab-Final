
package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class uniList extends AppCompatActivity {

    private uniinfo unilist[];
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private TextView tv, textView;
    private ListView listView;
    private CardView c;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_list);

        tv = findViewById(R.id.status);
        textView = findViewById(R.id.tview);

        ArrayList<String> unis = (ArrayList<String>) getIntent().getSerializableExtra("arr");

        if(unis.isEmpty())
        {
            textView.setVisibility(View.INVISIBLE);
            tv.setText("NO UNIVERSITIES TO SHOW");
            tv.setVisibility(View.VISIBLE);
        }
        else
        {
            textView.setVisibility(View.VISIBLE);

            unilist = new uniinfo[unis.size()];

            for (int z = 0; z < unis.size(); z++) {
                unilist[z] = new uniinfo(unis.get(z), R.drawable.picuni);
            }


            listView = findViewById(R.id.unilist);
            adapterUniList ad = new adapterUniList(this, R.layout.uni_list_row, unilist);
            listView.setAdapter(ad);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    uniinfo u = (uniinfo) adapterView.getItemAtPosition(i);
                  //  Toast.makeText(uniList.this, "Item: " + u.uniName, Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(uniList.this, uniPageStudent.class);
                    in.putExtra("uniname", u.uniName);
                    startActivity(in);
                }
            });


        }
    }

}


