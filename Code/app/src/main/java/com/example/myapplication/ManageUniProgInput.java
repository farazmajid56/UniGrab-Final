package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ManageUniProgInput extends AppCompatActivity {

    private Button bt;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_uni_prog_input);

        // String Prog = getIntent().getExtras().getString("Program");
        int value = getIntent().getExtras().getInt("Program");
        int Depadd = getIntent().getExtras().getInt("Adddep");
        // int value =1;
        bt = findViewById(R.id.button_next_to_manage_uni);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int depadd = Depadd;

                EditText name = findViewById(R.id.programmeName);
                EditText credithr = findViewById(R.id.creditHr);
                EditText feepercredit = findViewById(R.id.feePerCreditHr);
                EditText DepName = findViewById(R.id.Department_name);

                Log.d("info:", "info" + name + credithr + feepercredit);

//                String progname = name.getText().toString();
//                String progcredit = String.valueOf(credithr.getText().toString());
//                String progfee = String.valueOf(feepercredit.getText().toString());
//                Toast.makeText(ManageUniProgInput.this, ((char) value),
//                        Toast.LENGTH_LONG).show();

                if(name.length() != 0 && credithr.length() != 0 && feepercredit.length() != 0)
                {
                    String progname = name.getText().toString();
                    String progcredit = String.valueOf(credithr.getText().toString());
                    String progfee = String.valueOf(feepercredit.getText().toString());
                    String depname = DepName.getText().toString();

                    if(value == 1)
                    {

                        Intent in = new Intent(ManageUniProgInput.this, ManageUniProgGr.class);
                        in.putExtra("Namekey", progname);
                        in.putExtra("credithrkey", progcredit);
                        in.putExtra("feepercreditkey", progfee);
                        in.putExtra("depname", depname);
                        in.putExtra("Adddep", depadd);
                        in.putExtra("Program", value);
                        startActivity(in);
                    }

                    else if(value == 0)
                    {

                        System.out.println(progname);

                        Intent in = new Intent(ManageUniProgInput.this, ManageUniProgUnderGr.class);
                        in.putExtra("Namekey", progname);
                        in.putExtra("credithrkey", progcredit);
                        in.putExtra("feepercreditkey", progfee);
                        in.putExtra("depname", depname);
                        in.putExtra("Adddep", depadd);
                        in.putExtra("Program", value);
                        startActivity(in);
                    }
                }

                else
                {
                    Toast.makeText(ManageUniProgInput.this, "Please Fill each",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(ManageUniProgInput.this, ManageUniProgInput.class);
                    in.putExtra("Program", value);
                    in.putExtra("Adddep", depadd);
                    startActivity(in);
                }




            }
        });
    }
}
