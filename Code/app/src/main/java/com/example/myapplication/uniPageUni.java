package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.alumniInfo;
import com.example.myapplication.Classes.currentUserUni;
import com.example.myapplication.Classes.feeinfo;
import com.example.myapplication.Classes.reviewInfo;
import com.example.myapplication.Classes.viewProfile;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class uniPageUni extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView tname;
    private View headerview;
    private ArrayList<String> depts;
    private List<alumniInfo> arr;
    private List<feeinfo> fees;
    private List<aidInfo> aid;
    private List<reviewInfo> reviewInfos;
    private University obj;

    private void loadFragment(Fragment fragment, boolean flag, Bundle b) {
        fragment.setArguments(b);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!flag){
            ft.add(R.id.container1 , fragment);
        }else{
            ft.replace(R.id.container1 , fragment);
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_page_uni);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = (University) cu.getU();

        drawerLayout = findViewById(R.id.side_menu_uni);
        navigationView = findViewById(R.id.sidenavuni);
        toolbar = findViewById(R.id.toolbar1);
        headerview = navigationView.getHeaderView(0);
        tname = (TextView) headerview.findViewById(R.id.menu_uniname);

        String s = obj.getName();
        tname.setText(s);

        depts = new ArrayList<String>();
        arr = new ArrayList<alumniInfo>();
        fees = new ArrayList<feeinfo>();
        aid = new ArrayList<aidInfo>();
        reviewInfos = new ArrayList<reviewInfo>();

        obj.getUni(uniPageUni.this, s, depts, arr, fees, aid, reviewInfos);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        Bundle bundle = new Bundle();
        bundle.putString("universityName", s);

        loadFragment(new aboutFrag(),false, bundle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.menu_faculty)
                {
                    Bundle b = new Bundle();
                    b.putString("universityName", s);
                    b.putStringArrayList("dept", depts);
                    // b.putSerializable("obj", obj);
                    loadFragment(new faculty_frag(), true, b);
                }
                else if(id == R.id.menu_fees)
                {
                    Bundle b = new Bundle();
                    b.putString("universityName", s);
                    b.putSerializable("fee", (Serializable) fees);
                    loadFragment(new fees_frag(), true, b);
                }
                else if(id == R.id.menu_degree)
                {
                    Bundle b = new Bundle();
                    b.putString("universityName", s);
                    b.putStringArrayList("dept", depts);
                    //   b.putSerializable("obj", obj);
                    loadFragment(new programsOffered_frag(), true, b);
                }
                else if(id == R.id.menu_alumni)
                {
                    Bundle b = new Bundle();
                    b.putString("universityName", s);
                    b.putSerializable("alumnis", (Serializable) arr);
                    loadFragment(new AlumniPlacement_frag(), true, b);
                }
                else if(id == R.id.menu_aid)
                {
                    Bundle b = new Bundle();
                    b.putString("universityName", s);
                    b.putSerializable("a", (Serializable) aid);
                    loadFragment(new FinancialAid_frag(), true, b);
                }
                else if(id == R.id.menu_review)
                {
                    Bundle b = new Bundle();
                    b.putString("universityName", s);
                    b.putSerializable("r", (Serializable) reviewInfos);
                    loadFragment(new reviewsFragUni(), true, b);
                }
                else if(id == R.id.menu_about)
                {
                    loadFragment(new aboutFrag(), true, bundle);
                }
                else if(id == R.id.menu_post)
                {
                    loadFragment(new postFragUni(), true, bundle);
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }
}