package com.example.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.myapplication.Classes.profinfo;

import java.util.List;
import java.util.Map;




public class AdapterFaculty extends BaseExpandableListAdapter {

    private Context context;
    private Map<String, List<profinfo>> profcollection;
    private List<String> groupList;
    LayoutInflater inflater;

    public AdapterFaculty(Context context, List<String> grouplist, Map<String,List<profinfo>> profCollection)
    {
        this.context=context;
        this.profcollection=profCollection;
        this.groupList=grouplist;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getGroupCount() {
        return profcollection.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return profcollection.get(groupList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return profcollection.get(groupList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String degreename = getGroup(i).toString();
        if(view==null)
        {
            view=inflater.inflate(R.layout.dept_list_row,null);
        }
        TextView item = view.findViewById(R.id.parentDept);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(degreename);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        profinfo obj = (profinfo) getChild(i,i1);
        if(view==null){

            view = inflater.inflate(R.layout.faculty_list_row,null);
        }

        TextView tview = (TextView) view.findViewById(R.id.teacherName);
        TextView tview2 = (TextView) view.findViewById(R.id.teacherDesignation);
        TextView tview3 = (TextView) view.findViewById(R.id.teacherEmail);
        tview.setText(obj.getFname()+" "+obj.getLname());
        tview2.setText(obj.getDesignation());
        tview3.setText(obj.getEmail());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true ;
    }

}
