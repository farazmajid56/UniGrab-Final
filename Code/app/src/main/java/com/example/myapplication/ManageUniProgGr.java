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
import java.util.Collection;
import java.util.Collections;

public class ManageUniProgGr extends AppCompatActivity {

    private Button bt;

    private University uniobj;

    TextView tvDay;
    boolean[] selectProgram;
    ArrayList<Integer> programlist = new ArrayList<>();
    private String []programArray = {"BSCS", "BSSE", "BSDS","BBA","BSCV", "BSEE", "BSME"};
    ArrayList<String> userChoice = new ArrayList<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_uni_program_grad_input);

        String ProgramName = getIntent().getExtras().getString("Namekey");
        String credithr = getIntent().getExtras().getString("credithrkey");
        String feepercredit = getIntent().getExtras().getString("feepercreditkey");
        String Depname = getIntent().getExtras().getString("depname");
        int Depadd = getIntent().getExtras().getInt("Adddep");
        int value = getIntent().getExtras().getInt("Program");

        int depadd = Depadd;

        UniversityManager obj = new UniversityManager();
        obj.connectToDb(ManageUniProgGr.this);

        currentUserUni cu = currentUserUni.getInstance(uniobj);
        uniobj = cu.getU();

        String CurrentUniname = uniobj.getName();


        bt = findViewById(R.id.button_next_to_manage_uni_from_grad);

        tvDay = findViewById(R.id.graduate_multiple_selection);

        selectProgram = new boolean[programArray.length];

        tvDay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        ManageUniProgGr.this
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
                                Toast.makeText(ManageUniProgGr.this, "An error has occured ",Toast.LENGTH_LONG).show();
                                Intent in = new Intent(ManageUniProgGr.this, ManageUniProgInput.class);
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

                        for(int j = 0; j < programlist.size(); j++) {
                            stringBuilder.append(programArray[programlist.get(j)]);
                            userChoice.add(programArray[programlist.get(j)]);
                            if(j != programlist.size() - 1) {
                                stringBuilder.append(", ");
                            }

                            tvDay.setText(stringBuilder.toString());

                        }

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




                EditText ReqCGPA = findViewById(R.id.CGPA);


                Log.d("depadd:", " "+depadd);

                if(ReqCGPA.length() != 0 && userChoice.size() != 0)
                {
                    //     Log.d("prefields",credithr);
                    float prog_cgpa = Float.valueOf(String.valueOf(ReqCGPA.getText().toString()));
                    int progcredithr = Integer.valueOf(credithr);
                    int progfeepercredit = Integer.valueOf(feepercredit);


                    if(depadd == 0)
                    {
                        // int result = obj.UniqueProgram(ManageUniProgGr.this, ProgramName, Depname, CurrentUniname);

                        if(obj.checkUniquenessProgram(ManageUniProgGr.this, CurrentUniname, Depname, ProgramName))
                        {
                            obj.createProgramGraduate(ManageUniProgGr.this,Depname,ProgramName,progcredithr,progfeepercredit,prog_cgpa, userChoice, CurrentUniname);

                            Log.d("pre fields:", Depname + ProgramName + progcredithr + progfeepercredit + prog_cgpa);

                            Intent in = new Intent(ManageUniProgGr.this, ManageUniMain.class);
                            startActivity(in);
                        }

                        else
                        {
                            Toast.makeText(ManageUniProgGr.this, "Please Enter a unique program ",Toast.LENGTH_LONG).show();
                            Intent in = new Intent(ManageUniProgGr.this, ManageUniProgInput.class);
                            in.putExtra("Program", value);
                            in.putExtra("Adddep", depadd);
                            startActivity(in);
                        }



                    }

                    else if(depadd == 1)
                    {
                        Log.d("Adddep", "deap value "+depadd);

                        int depUnique = obj.UniqueDepartment(ManageUniProgGr.this, Depname, CurrentUniname);

                        if( depUnique == 1)
                        {
                            obj.createDepartment(ManageUniProgGr.this,Depname, CurrentUniname);

                            obj.createProgramGraduate(ManageUniProgGr.this,Depname,ProgramName,progcredithr,progfeepercredit,prog_cgpa, userChoice, CurrentUniname);

                            Intent in = new Intent(ManageUniProgGr.this, ManageUniMain.class);
                            startActivity(in);

                        }

                        else if( depUnique == 2)
                        {
                            Toast.makeText(ManageUniProgGr.this, "Invalid University id",Toast.LENGTH_LONG).show();
                            Intent in = new Intent(ManageUniProgGr.this, ManageUniProgInput.class);
                            in.putExtra("Program", value);
                            in.putExtra("Adddep", depadd);
                            startActivity(in);
                        }

                        else if(depUnique == 0)
                        {
                            Toast.makeText(ManageUniProgGr.this, "Please Enter a unique department",Toast.LENGTH_LONG).show();
                            Intent in = new Intent(ManageUniProgGr.this, ManageUniProgInput.class);
                            in.putExtra("Program", value);
                            in.putExtra("Adddep", depadd);
                            startActivity(in);
                        }

                    }

                }

                else
                {
                    Toast.makeText(ManageUniProgGr.this, "Please Fill each field",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(ManageUniProgGr.this, ManageUniProgGr.class);
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
