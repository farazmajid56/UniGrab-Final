package com.example.myapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Classes.SearchUni;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.alumniInfo;
import com.example.myapplication.Classes.currentUser;
import com.example.myapplication.Classes.feeinfo;
import com.example.myapplication.Classes.reviewInfo;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class uniPageStudent extends AppCompatActivity {
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
    private Student obj;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_page_student);

        currentUser cu  = currentUser.getInstance(obj, null, null);
        obj = cu.getStu();

        SearchUni obj1 = new SearchUni();
        obj1.connectToDb(uniPageStudent.this);

        drawerLayout = findViewById(R.id.side_menu);
        navigationView = findViewById(R.id.sidenav);
        toolbar = findViewById(R.id.toolbar1);
        headerview = navigationView.getHeaderView(0);
        tname = (TextView) headerview.findViewById(R.id.menu_uniname);

        String s = getIntent().getExtras().getString("uniname");
        tname.setText(s);

        depts = new ArrayList<String>();
        arr = new ArrayList<alumniInfo>();
        fees = new ArrayList<feeinfo>();
        aid = new ArrayList<aidInfo>();
        reviewInfos = new ArrayList<reviewInfo>();

        //obj.getUniveristy(uniPageStudent.this, s, depts, arr, fees, aid, reviewInfos);   //gets uni content from db

        obj.getUniContent(uniPageStudent.this, s, depts, arr, fees, aid, reviewInfos);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        Bundle bundle = new Bundle();
        bundle.putString("universityName", s);

        loadFragment(new campusLife_frag(),false, bundle);
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
                    loadFragment(new Reviews_frag(), true, b);
                }
                else if(id == R.id.menu_eligibility)
                {
                    loadFragment(new Eligibility_frag(), true, bundle);
                }
                else if(id == R.id.menu_campus)
                {
                    loadFragment(new campusLife_frag(), true, bundle);
                }
                else if(id == R.id.menu_location)
                {
                    double lat =  obj1.getUniLatitude(uniPageStudent.this, s);
                    double lng =  obj1.getUniLongitude(uniPageStudent.this, s);
                  /*  Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+lat+","+lng+"&mode=d"));
                    in.setPackage("com.google.android.apps.maps");
                    if(in.resolveActivity(getPackageManager()) != null) {
                        startActivity(in);
                    }*/

                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, lng);
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(in);

                   /* Intent in = new Intent(uniPageStudent.this, google.class);
                    in.putExtra("universityName", s);
                    startActivity(in);*/

                }
                else if(id == R.id.menu_post)
                {
                    loadFragment(new postFrag(), true, bundle);
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });


    }


}