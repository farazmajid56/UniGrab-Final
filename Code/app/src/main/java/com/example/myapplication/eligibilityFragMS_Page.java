package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class eligibilityFragMS_Page extends AppCompatActivity {

    private Button btn;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility_frag_ms_page);
        btn = findViewById(R.id.checkEligibility);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t = findViewById(R.id.status);
                t.setText("Button dab gya");

                CountDownTimer timer = new CountDownTimer(3000,1000) {
                    @Override
                    public void onTick(long l) {
                        t.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFinish() {
                        t.setVisibility(View.INVISIBLE);
                    }
                }.start();



            }
        });
    }


}