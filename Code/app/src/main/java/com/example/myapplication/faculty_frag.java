package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.myapplication.Classes.SearchUni;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.profinfo;
import com.example.myapplication.Classes.viewProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link faculty_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class faculty_frag extends Fragment {

    private List<String> listdepartment;
    private List<profinfo> listprof;
    private Map<String,List<profinfo>> proflist;
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

    public faculty_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment faculty_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static faculty_frag newInstance(String param1, String param2) {
        faculty_frag fragment = new faculty_frag();
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

        View view = inflater.inflate(R.layout.fragment_faculty_frag, container, false);
        str = getArguments().getString("universityName");
        listdepartment = new ArrayList<String>();
        listdepartment = getArguments().getStringArrayList("dept");

        viewProfile obj = new viewProfile();
        obj.connectToDb(getContext());

        createCollection(obj);
        //-----------------------------//
        expandablelistview=view.findViewById(R.id.expandabledept);
        expandableListAdapter= new AdapterFaculty(this.getContext(),listdepartment,proflist);
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

        List<profinfo> prof = new ArrayList<profinfo>();
        proflist = new HashMap<String,List<profinfo>>();


        for(int z = 0; z < listdepartment.size(); z++)
        {
            obj.getFacultyOfDept(getContext(), str, listdepartment.get(z), prof);
            loadChild(prof);
            prof.clear();
            proflist.put(listdepartment.get(z),listprof);
        }
    }

    private void loadChild(List<profinfo> obj) {
        listprof = new ArrayList<>();
        for(profinfo i: obj)
        {
            listprof.add(i);
        }
    }

}