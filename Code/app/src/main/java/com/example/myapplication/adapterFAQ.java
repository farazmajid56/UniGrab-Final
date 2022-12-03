package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Classes.faqInfo;
import com.example.myapplication.Classes.reviewInfo;

import java.util.List;

public class adapterFAQ extends ArrayAdapter<faqInfo> {
    public adapterFAQ(@NonNull Context context, int resource, @NonNull List<faqInfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        faqInfo obj = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.faq_list_row, parent, false);
        }

        // all views defined in list cell design
        TextView t1 = (TextView) convertView.findViewById(R.id.question);
        TextView t2 = (TextView) convertView.findViewById(R.id.answer);
        t1.setText(obj.getQuestion());
        t2.setText(obj.getAns());
        return convertView;
    }
}
