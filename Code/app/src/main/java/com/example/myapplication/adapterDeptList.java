package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class adapterDeptList extends BaseAdapter {
    Context context;
    String[] Listdept;
    LayoutInflater inflator;

    public adapterDeptList(Context ctx, String[] deptList)
    {
        this.context = ctx;
        this.Listdept = deptList;
        inflator = LayoutInflater.from(ctx);
    }


    @Override
    public int getCount() {
        return Listdept.length;
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
        v = inflator.inflate(R.layout.dept_list_row, null);
        TextView tview = (TextView) v.findViewById(R.id.parentDept);
        tview.setText(Listdept[i]);
        return v;
    }

}
