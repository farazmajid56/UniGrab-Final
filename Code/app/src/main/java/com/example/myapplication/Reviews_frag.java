package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.myapplication.Classes.SearchUni;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.reviewInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Reviews_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reviews_frag extends Fragment {

    private List<reviewInfo> listreview;
    private ListView listView;
    private RatingBar ratingBar;
    private Button submit;
    private EditText editText;
    private Student obj1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Reviews_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reviews_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static Reviews_frag newInstance(String param1, String param2) {
        Reviews_frag fragment = new Reviews_frag();
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
        View view = inflater.inflate(R.layout.fragment_reviews_frag, container, false);


        /*SearchUni obj = new SearchUni();
        obj.connectToDb(getContext());*/
        String str = getArguments().getString("universityName");
        listreview = (List<reviewInfo>) getArguments().getSerializable("r");
      //  Toast.makeText(getContext(), "reviews:"+String.valueOf(listreview.size()), Toast.LENGTH_SHORT).show();

        currentUser cu  = currentUser.getInstance(obj1, null, null);
        obj1 = cu.getStu();


        listView = view.findViewById(R.id.reviewlist);
        adapterReview ad = new adapterReview(this.getContext(), R.layout.review_list_row, listreview);
        listView.setAdapter(ad);

        submit = view.findViewById(R.id.sub);
        editText = view.findViewById(R.id.writereview);
        ratingBar = view.findViewById(R.id.rate);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().length() != 0)
                {
                    obj1.submitReview(getContext(), str,  Math.round(ratingBar.getRating()), editText.getText().toString());
                    Toast.makeText(getContext(), "Review submitted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Review field is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}