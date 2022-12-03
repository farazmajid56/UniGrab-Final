package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Classes.alumniInfo;
import com.example.myapplication.Classes.feeinfo;

import java.util.List;

public class adapterFees extends ArrayAdapter<feeinfo> {

    public adapterFees(@NonNull Context context, int resource, @NonNull List<feeinfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        feeinfo obj = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fees_list_row, parent, false);
        }

        // all views defined in list cell design
        TextView t1 = (TextView) convertView.findViewById(R.id.feeperch);
        TextView t2 = (TextView) convertView.findViewById(R.id.credithrs);
        TextView t4 = (TextView) convertView.findViewById(R.id.program);
        t1.setText("Fee per credit hour: "+Integer.toString(obj.getFeePerCdthr()));
        t2.setText("Credit Hours: "+Integer.toString(obj.getCreditHrs()));
        t4.setText(obj.getDegree());

        return convertView;
    }
}
