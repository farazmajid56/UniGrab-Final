package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class adapterDegreeList extends BaseAdapter {
    Context context;
    String[] Listdegree;
    LayoutInflater inflator;

    public adapterDegreeList(Context ctx, String[] degreeList)
    {
        this.context = ctx;
        this.Listdegree = degreeList;
        inflator = LayoutInflater.from(ctx);
    }


    @Override
    public int getCount() {
        return Listdegree.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        v = inflator.inflate(R.layout.degree_list_row, null);
        TextView tview = (TextView) v.findViewById(R.id.childDegree);
        tview.setText(Listdegree[i]);
        return v;
    }

}
