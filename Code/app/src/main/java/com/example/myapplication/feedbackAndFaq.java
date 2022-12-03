package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.myapplication.Classes.SearchUni;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.faqInfo;
import com.example.myapplication.Classes.reviewInfo;

import java.util.ArrayList;
import java.util.List;

public class feedbackAndFaq extends AppCompatActivity {

    List<faqInfo> listfaq;
    ListView listView;
    Button submit;
    EditText editText;
    Student obj1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_and_faq);

        listfaq = new ArrayList<faqInfo>();
        currentUser cu  = currentUser.getInstance(obj1, null, null);
        obj1 = cu.getStu();
        obj1.getFaqs(this, listfaq);

        listView = findViewById(R.id.faqslist);
        editText = findViewById(R.id.writereview);
        submit = findViewById(R.id.sub);
        adapterFAQ ad = new adapterFAQ(this, R.layout.faq_list_row, listfaq);
        listView.setAdapter(ad);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().length() != 0)
                {
                    obj1.giveFeedback(feedbackAndFaq.this, editText.getText().toString());
                    Toast.makeText(feedbackAndFaq.this, "Feedback submitted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(feedbackAndFaq.this, "Review field is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}