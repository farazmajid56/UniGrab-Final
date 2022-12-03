package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.imageClass;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link postFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class postFrag extends Fragment {

    private ListView ilist, vlist, tlist;
    private Student obj1;
    private String str;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public postFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment postFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static postFrag newInstance(String param1, String param2) {
        postFrag fragment = new postFrag();
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
        View view = inflater.inflate(R.layout.fragment_post, container, false);


        currentUser cu  = currentUser.getInstance(obj1, null, null);
        obj1 = cu.getStu();
        str = getArguments().getString("universityName");

        ArrayList <String> images = new ArrayList<>();
        ArrayList <String> caps = new ArrayList<>();
        ArrayList <String> vids = new ArrayList<>();
        ArrayList <String> txts = new ArrayList<>();

        obj1.getImageList(getContext(), images, caps,str);
        obj1.getVideoList(getContext(), vids, str);
        obj1.getTextList(getContext(), txts, str);

        List<imageClass> imgbm = new ArrayList<imageClass>();

        for(int z = 0; z < images.size(); z++) {
            byte[] decodeString = Base64.decode(images.get(z), Base64.DEFAULT);
            Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
            imgbm.add(new imageClass(decodebitmap, caps.get(z)));
        }



        ilist = view.findViewById(R.id.imageslist);
        vlist = view.findViewById(R.id.videolist);
        tlist = view.findViewById(R.id.textlist);

        AdapterCampusImage ad = new AdapterCampusImage(this.getContext(), imgbm);
        ilist.setAdapter(ad);

        ArrayAdapter<String> ad1 = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, vids);
        vlist.setAdapter(ad1);

        ArrayAdapter<String> ad2 = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, txts);
        tlist.setAdapter(ad2);

        ilist.setOnTouchListener(new View.OnTouchListener() {
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

        tlist.setOnTouchListener(new View.OnTouchListener() {
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

        vlist.setOnTouchListener(new View.OnTouchListener() {
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

        vlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(item));
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.setPackage("com.google.android.youtube");
                startActivity(in);
            }
        });


        return view;
    }
}