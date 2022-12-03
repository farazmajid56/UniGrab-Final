package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Eligibility_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Eligibility_frag extends Fragment {

    private ArrayAdapter<String> adapter;
 //   private String arr[] = {"Bachelors", "Masters"};
    private Spinner acc;
    private Button b;
    private String item;
    private Student obj1;
    private String str;
    private List<String> arr;
    private TextView tv;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Eligibility_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Eligibility_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static Eligibility_frag newInstance(String param1, String param2) {
        Eligibility_frag fragment = new Eligibility_frag();
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
        View view = inflater.inflate(R.layout.fragment_eligibility_frag, container, false);


        currentUser cu  = currentUser.getInstance(obj1, null, null);
        obj1 = cu.getStu();
        String st = obj1.getEducationType();
        str = getArguments().getString("universityName");


        arr = new ArrayList<String>();
        obj1.getAvalaiblePrograms(getContext(), str, arr);

        tv = view.findViewById(R.id.program);
        acc = view.findViewById(R.id.spinnerprogram);
        b = view.findViewById(R.id.btnEligibility);
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arr.size() != 0 && arr.size() != 0) {
                    if (obj1.getEligiblityStatus(getContext(), item, str) == true) {
                        tv.setText("You are eligible to apply in selected program of " + str);

                        CountDownTimer timer = new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long l) {
                                tv.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFinish() {
                                tv.setVisibility(View.INVISIBLE);
                            }
                        }.start();
                    } else {
                        tv.setText("You are not eligible to apply in selected program of " + str);

                        CountDownTimer timer = new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long l) {
                                tv.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFinish() {
                                tv.setVisibility(View.INVISIBLE);
                            }
                        }.start();
                    }
                }
                else
                {
                    Toast.makeText(getContext(), "University does not have any program avalaible", Toast.LENGTH_SHORT).show();
                }
            }
        });

        acc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();
                //  Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        return view;
    }


}