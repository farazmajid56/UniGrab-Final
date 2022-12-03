package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Classes.GraduateStudent;
import com.example.myapplication.Classes.SearchUni;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.UndergradStudent;
import com.example.myapplication.Classes.currentUser;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFrag extends Fragment {

    private Button buni;
    private Button elg;
    private Button Search;
    private Button Feedback;
    private Button comparison;
    private Button back;
    private AutoCompleteTextView act;
    private ArrayList<String> arrUnis;
    private Student obj1;
    private String str;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeFrag() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFrag newInstance(String param1, String param2) {
        homeFrag fragment = new homeFrag();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);
        buni = view.findViewById(R.id.unis);
        elg = view.findViewById(R.id.eligibility);
        act = view.findViewById(R.id.autotext);
        Search = view.findViewById(R.id.search);
        Feedback = view.findViewById(R.id.feedback);
        comparison = view.findViewById(R.id.compare);
        back = view.findViewById(R.id.bck);

        currentUser cu  = currentUser.getInstance(obj1, null, null);
        obj1 = cu.getStu();
        String st = obj1.getEducationType();


        SearchUni obj = new SearchUni();
        obj.connectToDb(getContext());

        arrUnis = new ArrayList<String>();
        obj.getUnis(getContext(),arrUnis);

        ArrayAdapter<String> actadapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrUnis);
        act.setAdapter(actadapter);
        act.setThreshold(1);


        buni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> arr = new ArrayList<String>();
                /*for(int z = 0; z < arr.size(); z++)
                {
                    Toast.makeText(getContext(),arr.get(z), Toast.LENGTH_SHORT).show();
                }*/
                obj.getUnis(getContext(), arr);
                Intent in = new Intent(getContext(), Universities.class);
                in.putExtra("allunis", arr);
                startActivity(in);
            }
        });

        elg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), programSelectionEligibility.class);
              //  in.putExtra("type", str);
             //   in.putExtra("object", (Serializable) obj1);
                startActivity(in);

            }
        });

        Search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), act.getText(), Toast.LENGTH_SHORT).show();
                if(act.length() != 0) {
                    Intent in = new Intent(getContext(), uniPageStudent.class);
                    in.putExtra("uniname", act.getText().toString());
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(getContext(), "Search Field Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), feedbackAndFaq.class);
                startActivity(in);
            }
        });

        comparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), compareUnis.class);
                startActivity(in);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(getContext(), MainActivity.class);
                cu.logout();
                startActivity(in);
            }

        });

        return view;
    }
}