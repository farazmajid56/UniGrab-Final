package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.Classes.GraduateStudent;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.UndergradStudent;
import com.example.myapplication.Classes.currentUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class homePage extends AppCompatActivity {

    private BottomNavigationView bnView;
    private Student obj;

    // loads the fragment on tha basis of given 'fragment'
    private void loadFrag(Fragment fragment, boolean flag){
      /*  fragment.setArguments();*/
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!flag){
            ft.add(R.id.container , fragment);
        }else{
            ft.replace(R.id.container , fragment);
        }
        ft.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


   //     String str = getIntent().getExtras().getString("edutype");

      //  Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        currentUser cu  = currentUser.getInstance(obj, null, null);
        obj = cu.getStu();

     //   obj = (Student) getIntent().getExtras().getSerializable("stu");

    //    Toast.makeText(this, Integer.toString(((UndergradStudent)obj).getMarks()), Toast.LENGTH_SHORT).show();

        bnView = findViewById(R.id.nav_view);

        bnView.setSelectedItemId(R.id.navigation_home);
      /*  Bundle b = new Bundle();
        b.putString("edutype", str);
        b.putSerializable("stu", obj);*/
        loadFrag(new homeFrag(),false);

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId() ;
                if (id == R.id.navigation_home){
                    loadFrag(new homeFrag(),true);
                }else if (id == R.id.navigation_profile){
                    loadFrag(new profileFrag(),true);
                }else{
                    loadFrag(new settingsFrag(),true);
                }
                return true;
            }
        });
    }
}