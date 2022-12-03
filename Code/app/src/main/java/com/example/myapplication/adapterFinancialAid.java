package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Classes.aidInfo;

import java.util.List;


public class adapterFinancialAid extends ArrayAdapter<aidInfo> {

    public adapterFinancialAid(@NonNull Context context, int resource, @NonNull List<aidInfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        aidInfo obj = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.financial_adi_list_row, parent, false);
        }

        // all views defined in list cell design
        TextView t1 = (TextView) convertView.findViewById(R.id.aidName);
        TextView t2 = (TextView) convertView.findViewById(R.id.aidDetail);
        t1.setText(obj.getName());
        t2.setText(obj.getDetail());

        return convertView;
    }
}
