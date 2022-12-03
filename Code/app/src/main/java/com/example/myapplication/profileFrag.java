package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Classes.GraduateStudent;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.UndergradStudent;
import com.example.myapplication.Classes.currentUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profileFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFrag extends Fragment {

    private TextView t1, t2, t3, t4, t5;
    private Student obj;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profileFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static profileFrag newInstance(String param1, String param2) {
        profileFrag fragment = new profileFrag();
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

        View view = inflater.inflate(R.layout.fragment_profile_frag, container, false);


        t1 = view.findViewById(R.id.textViewProfileName);
        t2 = view.findViewById(R.id.personFirstNameEditText);
        t3 = view.findViewById(R.id.personLastNameEditText);
        t4 = view.findViewById(R.id.editTextDate);
        t5 = view.findViewById(R.id.stuMarks);

        currentUser cu  = currentUser.getInstance(obj, null, null);
        obj = cu.getStu();


        t1.setText(obj.getName());
        t2.setText(obj.getFirstName());
        t3.setText(obj.getLastName());
        t4.setText(obj.getEmail());

        if(obj.getEducationType().equals("Undergraduate")) {
            t5.setText(String.valueOf(obj.getUndergradMarks(getContext(), obj.getName())));
        }
        else
        {
            Toast.makeText(getContext(), String.valueOf(obj.getGradCgpa(getContext(), obj.getName())), Toast.LENGTH_SHORT).show();
            t5.setText( String.valueOf(obj.getGradCgpa(getContext(), obj.getName())) );
        }

        return view;
    }
}