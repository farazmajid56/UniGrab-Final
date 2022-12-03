package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Classes.alumniInfo;
import com.example.myapplication.Classes.feeinfo;
import com.example.myapplication.Classes.viewProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fees_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fees_frag extends Fragment {

    private List<String> listdegree;
    private List<feeinfo> listfees;
    private ListView listView;
    private String str;
    private int feeAdmission;
    TextView t;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fees_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fees_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static fees_frag newInstance(String param1, String param2) {
        fees_frag fragment = new fees_frag();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fees_frag, container, false);


        str = getArguments().getString("universityName");
        listfees = (List<feeinfo>) getArguments().getSerializable("fee");

        viewProfile obj = new viewProfile();
        obj.connectToDb(getContext());
        feeAdmission = obj.getUniAdmissionFee(getContext(), str);

        t = view.findViewById(R.id.admissionfee);
        t.setText("Admission Fees:"+String.valueOf(feeAdmission));
        listView = view.findViewById(R.id.feelist);
        adapterFees ad = new adapterFees(this.getContext(), R.layout.fees_list_row, listfees);
        listView.setAdapter(ad);


        return view;
    }

}