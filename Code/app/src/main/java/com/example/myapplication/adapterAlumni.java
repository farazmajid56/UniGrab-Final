package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Classes.alumniInfo;

import java.util.ArrayList;
import java.util.List;


public class adapterAlumni extends ArrayAdapter<alumniInfo> {



    public adapterAlumni(@NonNull Context context, int resource, @NonNull List<alumniInfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        alumniInfo obj = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.alumni_list_row, parent, false);
        }

        // all views defined in list cell design
        TextView t1 = (TextView) convertView.findViewById(R.id.alumniName);
        TextView t2 = (TextView) convertView.findViewById(R.id.alumniCompany);
        TextView t3 = (TextView) convertView.findViewById(R.id.alumniBatch);
        t1.setText(obj.getName());
        t2.setText(obj.getCompany());
        t3.setText(obj.getBatch());

        return convertView;
    }

}
