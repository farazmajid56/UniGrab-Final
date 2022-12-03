package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.UniversityManager;
import com.example.myapplication.Classes.currentUserUni;

import java.util.ArrayList;
import java.util.Collections;

public class ManageUniProgUnderGr extends AppCompatActivity {
    private Button bt;

    private University uniobj;

    TextView tvDay;
    boolean[] selectProgram;
    ArrayList<Integer> programlist = new ArrayList<>();
    String[] programArray = {"Pre Medical", "Pre Engineering", "ICS","FA","Business"};
    ArrayList<String> userChoice = new ArrayList<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_uni_prog_undergrad_input);

        String ProgramName = getIntent().getExtras().getString("Namekey");
        String credithr = getIntent().getExtras().getString("credithrkey");
        String feepercredit = getIntent().getExtras().getString("feepercreditkey");
        String Depname = getIntent().getExtras().getString("depname");
        int Depadd = getIntent().getExtras().getInt("Adddep");
        int value = getIntent().getExtras().getInt("Program");

        int depadd = Depadd;
        //Log.d("info:", ProgramName+credithr+feepercredit);


        //Unimanager Account Creation

        UniversityManager obj = new UniversityManager();
        obj.connectToDb(ManageUniProgUnderGr.this);

        currentUserUni cu = currentUserUni.getInstance(uniobj);
        uniobj = cu.getU();

        String CurrentUniname = uniobj.getName();

        bt = findViewById(R.id.button_next_to_manage_uni_from_undergrad);
        tvDay = findViewById(R.id.undergraduate_multiple_selection);

        selectProgram = new boolean[programArray.length];




        tvDay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        ManageUniProgUnderGr.this
                );

                builder.setTitle("Select Program Requirement");

                builder.setCancelable(false);

                builder.setMultiChoiceItems(programArray, selectProgram, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        if(b){
                            programlist.add(i);


                            Collections.sort(programlist);
                        }

                        else{
                            try {
                                programlist.remove(i);
                            }

                            catch(Exception e)
                            {
                                Toast.makeText(ManageUniProgUnderGr.this, "An error has occured ",Toast.LENGTH_LONG).show();
                                Intent in = new Intent(ManageUniProgUnderGr.this, ManageUniProgInput.class);
                                in.putExtra("Program", value);
                                in.putExtra("Adddep", depadd);
                                startActivity(in);
                            }

                        }
                    }
                });

                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();

                        int k = 0;

                        for(int j = 0; j < programlist.size(); j++) {
                            stringBuilder.append(programArray[programlist.get(j)]);
                            userChoice.add(programArray[programlist.get(j)]);
                            //Log.d("info", userChoice.get(j));

                            if(j != programlist.size() - 1) {
                                stringBuilder.append(", ");
                            }

                            tvDay.setText(stringBuilder.toString());

                        }

                        //   userChoice = stringBuilder.toString();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        for(int j = 0; j < selectProgram.length; j++) {

                            selectProgram[j] = false;

                            programlist.clear();
                            userChoice.clear();

                            tvDay.setText("");

                        }


                    }
                });

                builder.show();



            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                EditText ReqMarks = findViewById(R.id.MinMarks);


                if(ReqMarks.length() != 0 && userChoice.size() != 0)
                {
                    int prog_req_marks = Integer.valueOf(String.valueOf(ReqMarks.getText().toString()));
                    int progcredithr = Integer.valueOf(credithr);
                    int progfeepercredit = Integer.valueOf(feepercredit);




                    if(depadd == 0)
                    {
                        //  int result = obj.UniqueProgram(ManageUniProgUnderGr.this,ProgramName, Depname, CurrentUniname);

                        if( obj.checkUniquenessProgram(ManageUniProgUnderGr.this, CurrentUniname,Depname,ProgramName))
                        {
                            obj.createProgramUnderGraduate(ManageUniProgUnderGr.this,Depname,ProgramName,progcredithr,progfeepercredit,prog_req_marks, userChoice, CurrentUniname);

                            Log.d("pre fields:", Depname + ProgramName + progcredithr + progfeepercredit + prog_req_marks);

                            Intent in = new Intent(ManageUniProgUnderGr.this, ManageUniMain.class);
                            startActivity(in);
                        }

                        else
                        {
                            Log.d("pre fields:", Depname + ProgramName + progcredithr + progfeepercredit + prog_req_marks);
                            Toast.makeText(ManageUniProgUnderGr.this, "Please Enter a unique program ",Toast.LENGTH_LONG).show();
                            Intent in = new Intent(ManageUniProgUnderGr.this, ManageUniProgInput.class);
                            in.putExtra("Program", value);
                            in.putExtra("Adddep", depadd);
                            startActivity(in);
                        }

                    }

                    else if(depadd == 1)
                    {
                        // int depUnique = obj.UniqueDepartment(ManageUniProgUnderGr.this, Depname, CurrentUniname);

                        //  int result = obj.UniqueProgram(ManageUniProgUnderGr.this,ProgramName, Depname);

                        if( obj.checkUniquenessDept(ManageUniProgUnderGr.this, CurrentUniname, Depname,ProgramName))
                        {
                            obj.createDepartment(ManageUniProgUnderGr.this,Depname, CurrentUniname);

                            obj.createProgramUnderGraduate(ManageUniProgUnderGr.this,Depname,ProgramName,progcredithr,progfeepercredit,prog_req_marks, userChoice, CurrentUniname);

                            Log.d("pre fields:", Depname + ProgramName + progcredithr + progfeepercredit + prog_req_marks);

                            Intent in = new Intent(ManageUniProgUnderGr.this, ManageUniMain.class);
                            startActivity(in);



                        }

                        else
                        {
                            Toast.makeText(ManageUniProgUnderGr.this, "Please Enter a unique department",Toast.LENGTH_LONG).show();
                            Intent in = new Intent(ManageUniProgUnderGr.this, ManageUniProgInput.class);
                            in.putExtra("Program", value);
                            in.putExtra("Adddep", depadd);
                            startActivity(in);
                        }

                        //  int dep = obj.createDepartmentUnderGraduate(ManageUniProgUnderGr.this,Depname,ProgramName,progcredithr,progfeepercredit,prog_req_marks, userChoice);
                    }



                }

                else
                {
                    Toast.makeText(ManageUniProgUnderGr.this, "Please Fill each field",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(ManageUniProgUnderGr.this, ManageUniProgUnderGr.class);
                    in.putExtra("Namekey", ProgramName);
                    in.putExtra("credithrkey", credithr);
                    in.putExtra("feepercreditkey", feepercredit);
                    in.putExtra("depname", Depname);
                    in.putExtra("Program", value);
                    in.putExtra("Adddep", depadd);
                    startActivity(in);
                }



            }
        });
    }

}
