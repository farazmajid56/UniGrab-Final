package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.Classes.SearchUni;
import com.example.myapplication.Classes.alumniInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlumniPlacement_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlumniPlacement_frag extends Fragment {

    private List<alumniInfo> alumnilist;
    private ListView listView;
    private String str;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AlumniPlacement_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlumniPlacement_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static AlumniPlacement_frag newInstance(String param1, String param2) {
        AlumniPlacement_frag fragment = new AlumniPlacement_frag();
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

        View view = inflater.inflate(R.layout.fragment_alumni_placement_frag, container, false);

        str = getArguments().getString("universityName");
        alumnilist = (List<alumniInfo>) getArguments().getSerializable("alumnis");

        listView = view.findViewById(R.id.alumnilist);
        adapterAlumni ad = new adapterAlumni(this.getContext(), R.layout.alumni_list_row, alumnilist);
        listView.setAdapter(ad);

        return view;
    }
}