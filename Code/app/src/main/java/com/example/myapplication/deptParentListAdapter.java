package com.example.myapplication;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import java.util.List;
import java.util.Map;

public class deptParentListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private Map<String,List<String>> degreecollection;
    private List<String> groupList;
    LayoutInflater inflater;

    public deptParentListAdapter(Context context, List<String> grouplist, Map<String,List<String>> degreeCollection)
    {
        this.context=context;
        this.degreecollection=degreeCollection;
        this.groupList=grouplist;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getGroupCount() {
        return degreecollection.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return degreecollection.get(groupList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return degreecollection.get(groupList.get(i)).get(i1);
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
        String deptname = getGroup(i).toString();
        if(view==null)
        {
            view=inflater.inflate(R.layout.dept_list_row,null);
        }
        TextView item = view.findViewById(R.id.parentDept);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(deptname);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String Details = getChild(i,i1).toString();
        if(view==null){

            view = inflater.inflate(R.layout.degree_list_row,null);
        }
        TextView item = view.findViewById(R.id.childDegree);
        item.setText(Details);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true ;
    }
}
