package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Classes.SearchUni;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.viewProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link programsOffered_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class programsOffered_frag extends Fragment {

    private List<String> deptlist;
    private List<String> listdegree;
    private Map<String,List<String>> degreelist;
    private ExpandableListView expandablelistview;
    private ExpandableListAdapter expandableListAdapter;
    private String str;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public programsOffered_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment programsOffered_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static programsOffered_frag newInstance(String param1, String param2) {
        programsOffered_frag fragment = new programsOffered_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_programs_offered_frag, container, false);

        str = getArguments().getString("universityName");
        deptlist = new ArrayList<String>();
        deptlist = getArguments().getStringArrayList("dept");

        viewProfile obj = new viewProfile();
        obj.connectToDb(getContext());


        /*Toast.makeText(getContext(), "name: " + str, Toast.LENGTH_SHORT).show();
        for(int z = 0; z < departments.size(); z++)
        {
            Toast.makeText(getContext(), "Dept: " + departments.get(z), Toast.LENGTH_SHORT).show();
        }*/


   //     createGroupList();
        createCollection(obj);
        //-----------------------------//
        expandablelistview=view.findViewById(R.id.expandableDegrees);
        expandableListAdapter= new deptParentListAdapter(this.getContext(),deptlist,degreelist);
        expandablelistview.setAdapter(expandableListAdapter);
        expandablelistview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition=-1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition!=-1 && i!=lastExpandedPosition)
                {
                    expandablelistview.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition=i;
            }
        });
        expandablelistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected =expandableListAdapter.getChild(i,i1).toString();
                Toast.makeText(getContext(),"Selected: " + selected,Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return view;
    }


    private void createCollection(viewProfile obj) {

        List<String> deg = new ArrayList<>();
        degreelist = new HashMap<String,List<String>>();

        for(int z = 0; z < deptlist.size(); z++)
        {
            obj.getProgramsOfDept(getContext(), str, deptlist.get(z), deg);
            loadChild(deg);
            deg.clear();
            degreelist.put(deptlist.get(z),listdegree);
        }
    }

    private void loadChild(List<String> dept) {
        listdegree =new ArrayList<>();
        for(String Dept: dept)
        {
            listdegree.add(Dept);
        }

    }


}