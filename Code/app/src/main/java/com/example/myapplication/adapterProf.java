package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Classes.feeinfo;
import com.example.myapplication.Classes.profinfo;

import java.util.List;

public class adapterProf extends ArrayAdapter<profinfo> {

    public adapterProf(@NonNull Context context, int resource, @NonNull List<profinfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        profinfo obj = (profinfo) getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.faculty_list_row, parent, false);
        }

        // all views defined in list cell design
        TextView tview = (TextView) convertView.findViewById(R.id.teacherName);
        TextView tview2 = (TextView) convertView.findViewById(R.id.teacherDesignation);
        TextView tview3 = (TextView) convertView.findViewById(R.id.teacherEmail);
        tview.setText(obj.getFname()+" "+obj.getLname());
        tview2.setText(obj.getDesignation());
        tview3.setText(obj.getEmail());

        return convertView;
    }
}
