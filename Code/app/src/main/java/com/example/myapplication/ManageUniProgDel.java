package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.UniversityManager;
import com.example.myapplication.Classes.currentUserUni;

public class ManageUniProgDel extends AppCompatActivity {

    private Button bt;
    private University uniobj;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_uni_prog_del);

        bt = findViewById(R.id.button_progdel);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText progname = findViewById(R.id.prog_name_del);
                EditText depname = findViewById(R.id.dep_name_del);

                String Progname = progname.getText().toString();
                String Depname = depname.getText().toString();

                UniversityManager obj = new UniversityManager();
                obj.connectToDb(ManageUniProgDel.this);

                currentUserUni cu = currentUserUni.getInstance(uniobj);
                uniobj = cu.getU();

                String CurrentUniname = uniobj.getName();

                // int uniid = 1;

                int depUnique = obj.UniqueDepartment(ManageUniProgDel.this,Depname,CurrentUniname);
                int progUnique = obj.UniqueProgram(ManageUniProgDel.this, Progname, Depname, CurrentUniname);

                if(depUnique == 0)
                {
                    if(progUnique == 2)
                    {
                        int Delret = obj.DeleteProgram(ManageUniProgDel.this,Depname,Progname,CurrentUniname);

                        if(Delret == 1)
                        {

                            Toast.makeText(ManageUniProgDel.this, "Department Successfully Deleted",Toast.LENGTH_LONG).show();
                            Intent in = new Intent(ManageUniProgDel.this, ManageUniMain.class);
                            startActivity(in);

                        }

                        else if(Delret == 2)
                        {
                            Toast.makeText(ManageUniProgDel.this, "Program Not Found",Toast.LENGTH_LONG).show();
                            Intent in = new Intent(ManageUniProgDel.this, ManageUniProgDel.class);
                            startActivity(in);
                        }

                        else
                        {
                            Toast.makeText(ManageUniProgDel.this, "Please try again",Toast.LENGTH_LONG).show();
                            Intent in = new Intent(ManageUniProgDel.this, ManageUniProgDel.class);
                            startActivity(in);
                        }
                    }

                    else if(progUnique == 1)
                    {
                        Toast.makeText(ManageUniProgDel.this, "Invalid Program name",Toast.LENGTH_LONG).show();
                        Intent in = new Intent(ManageUniProgDel.this, ManageUniProgDel.class);
                        startActivity(in);
                    }

                    else if(progUnique == 0)
                    {
                        Toast.makeText(ManageUniProgDel.this, "Please try again",Toast.LENGTH_LONG).show();
                        Intent in = new Intent(ManageUniProgDel.this, ManageUniProgDel.class);
                        startActivity(in);
                    }


                }

                else if( depUnique == 2)
                {
                    Toast.makeText(ManageUniProgDel.this, "Invalid University id",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(ManageUniProgDel.this, ManageUniProgDel.class);
                    startActivity(in);
                }

                else if(depUnique == 1)
                {
                    Toast.makeText(ManageUniProgDel.this, "Invalid Department name",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(ManageUniProgDel.this, ManageUniProgDel.class);
                    startActivity(in);
                }

                else
                {
                    Toast.makeText(ManageUniProgDel.this, "Error",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(ManageUniProgDel.this, ManageUniMain.class);
                    startActivity(in);
                }

            }
        });


    }
}
