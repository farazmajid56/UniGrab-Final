package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Classes.Evaluator;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.uniComparer;
import com.example.myapplication.Classes.viewProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class compareUniPrograms extends AppCompatActivity {

    private List<String> deptlist, deptlist1;
    private List<String> listdegree, listdegree1;
    private Map<String,List<String>> degreelist, degreelist1;
    private ExpandableListView expandablelistview, expandablelistview1;
    private ExpandableListAdapter expandableListAdapter, expandableListAdapter1;
    private String s1, s2;
    private Student obj;
    private TextView t1, t2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_uni_programs);

        currentUser cu  = currentUser.getInstance(obj, null, null);
        obj = cu.getStu();

        s1 = getIntent().getExtras().getString("name1");
        s2 = getIntent().getExtras().getString("name2");

        deptlist = new ArrayList<>();
        deptlist1 = new ArrayList<>();

        deptlist = getIntent().getExtras().getStringArrayList("d1");
        deptlist1 = getIntent().getExtras().getStringArrayList("d2");

        /*obj.getDeptsUni(this, s1, deptlist);
        obj.getDeptsUni(this, s2, deptlist1);*/

       // createCollection(obj, s2, degreelist1, deptlist1, listdegree1);
      //  createCollection(obj, s2, degreelist1, deptlist1, listdegree1);

        createCollection(obj);
        createCollection2(obj);

        t1 = findViewById(R.id.uni1);
        t2 = findViewById(R.id.uni2);
        t1.setText(s1);
        t2.setText(s2);

        expandablelistview = findViewById(R.id.expandableDegrees1);
        expandablelistview1 = findViewById(R.id.expandableDegrees2);

        expandableListAdapter = new deptParentListAdapter(this,deptlist,degreelist);
        expandablelistview.setAdapter(expandableListAdapter);

        expandableListAdapter1 = new deptParentListAdapter(this,deptlist1,degreelist1);
        expandablelistview1.setAdapter(expandableListAdapter1);

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
                Toast.makeText(compareUniPrograms.this,"Selected: " + selected,Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        expandablelistview1.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition=-1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition!=-1 && i!=lastExpandedPosition)
                {
                    expandablelistview1.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition=i;
            }
        });
        expandablelistview1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected =expandableListAdapter1.getChild(i,i1).toString();
                Toast.makeText(compareUniPrograms.this,"Selected: " + selected,Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

   /* private void createCollection(Student obj1, String uniname, Map<String,List<String>> l, List<String> d, List<String> rst) {

        List<String> deg = new ArrayList<>();
        l = new HashMap<String,List<String>>();

        for(int z = 0; z < d.size(); z++)
        {
            deg = obj1.getDeptPrgms(this, uniname, d.get(z));
            loadChild(deg, rst);
            deg.clear();
            l.put(d.get(z),rst);
        }
    }

    private void loadChild(List<String> dept, List<String> rst) {
        rst =new ArrayList<>();
        for(String Dept: dept)
        {
            rst.add(Dept);
        }

    }*/

    private void createCollection(Student obj1) {

        List<String> deg = new ArrayList<>();
        degreelist = new HashMap<String,List<String>>();


        for(int z = 0; z < deptlist.size(); z++)
        {

            deg = obj1.getDeptPrgms(this, s1, deptlist.get(z));
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

    private void createCollection2(Student obj1) {

        List<String> deg = new ArrayList<>();
        degreelist1 = new HashMap<String,List<String>>();


        for(int z = 0; z < deptlist1.size(); z++)
        {

            deg = obj1.getDeptPrgms(this, s2, deptlist1.get(z));
            loadChild2(deg);
            deg.clear();
            degreelist1.put(deptlist1.get(z),listdegree1);
        }
    }

    private void loadChild2(List<String> dept) {
        listdegree1 =new ArrayList<>();
        for(String Dept: dept)
        {
            listdegree1.add(Dept);
        }

    }
}