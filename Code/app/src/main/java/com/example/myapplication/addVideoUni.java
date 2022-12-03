package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Classes.managePost;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addVideoUni extends AppCompatActivity {

    private EditText ev;
    private Button ok;
    private managePost m;
    private String uname;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video_uni);

        ok = findViewById(R.id.post);
        ev = findViewById(R.id.imagedesc);

        uname = getIntent().getExtras().getString("name");
        m = new managePost();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ev.getText().length() != 0)
                {
                    if(isYoutubeUrl(ev.getText().toString()) == true)
                    {
                        m.connectToDb(addVideoUni.this);
                        m.insertVideo(addVideoUni.this, uname, ev.getText().toString());
                        //   Toast.makeText(addVideoUni.this, "Video posted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(addVideoUni.this, "not valid youtube url", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(addVideoUni.this, "Enter url first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isYoutubeUrl(String youTubeURl)
    {
        boolean success;
        String pattern = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+";
        if (!youTubeURl.isEmpty() && youTubeURl.matches(pattern))
        {
            success = true;
        }
        else
        {
            // Not Valid youtube URL
            success = false;
        }
        return success;
    }

}