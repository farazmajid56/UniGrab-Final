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

import com.example.myapplication.Classes.reviewInfo;

import java.util.List;

public class adapterReview extends ArrayAdapter<reviewInfo> {

    public adapterReview(@NonNull Context context, int resource, @NonNull List<reviewInfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        reviewInfo obj = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.review_list_row, parent, false);
        }

        // all views defined in list cell design
        TextView t1 = (TextView) convertView.findViewById(R.id.review);
        RatingBar r = (RatingBar) convertView.findViewById(R.id.bar);
        t1.setText(obj.getReview());
        r.setNumStars(obj.getRating());
        return convertView;
    }
}
