package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myapplication.Classes.SearchUni;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.viewProfile;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link campusLife_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class campusLife_frag extends Fragment {

    private ListView listView, listView2;
    private ScrollView scroll;
    private int imagelist[] = {R.drawable.picuni, R.drawable.picuni, R.drawable.picuni, R.drawable.picuni, R.drawable.picuni, R.drawable.picuni};
    private TextView textView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public campusLife_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment campusLife_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static campusLife_frag newInstance(String param1, String param2) {
        campusLife_frag fragment = new campusLife_frag();
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

        View view = inflater.inflate(R.layout.fragment_campus_life_frag, container, false);

        String str = getArguments().getString("universityName");

        viewProfile obj = new viewProfile();
        obj.connectToDb(getContext());


        String st = obj.getCampusLife(getContext(), str);

        textView = view.findViewById(R.id.details);
        listView = view.findViewById(R.id.imageslist);
        scroll = view.findViewById(R.id.sv);

        textView.setText(st);

            listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                view.onTouchEvent(motionEvent);
                return true;
            }
        });


       /* AdapterCampusImage ad = new AdapterCampusImage(this.getContext(), imagelist);
        listView.setAdapter(ad);*/


        return view;
    }
}