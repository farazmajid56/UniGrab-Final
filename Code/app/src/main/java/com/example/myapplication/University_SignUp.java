package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Classes.accountCreator;

import java.util.Objects;

public class University_SignUp extends AppCompatActivity {

    private Button bt;
    private EditText uname;
    private String usname;
    private String emai;
    private String pa;
    private String re;
    private EditText email;
    private EditText pass;
    private EditText repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_sign_up);

        accountCreator obj = new accountCreator();
        obj.connectToDb(University_SignUp.this);


        bt = findViewById(R.id.NextUniSignUp);
        uname = findViewById(R.id.UsernameUniSignUp);

        email=findViewById(R.id.EmailUniSignUp);
        pass=findViewById(R.id.PasswordUniSignUp);

        repass=findViewById(R.id.PasswordReEnterUniSign);



        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(email.length()!=0 && pass.length()!=0 && repass.length()!=0 && uname.length()!=0)
                {
                    if(pass.length() > 6)
                    {
                        if (Objects.equals(pass.getText().toString(), repass.getText().toString()))
                        {
                            if(obj.checkUsername(University_SignUp.this, uname.getText().toString()) == true && obj.checkUniquenessEmail(University_SignUp.this, String.valueOf(email.getText())) == true)
                            {

                                /*Toast.makeText(University_SignUp.this, uname.getText().toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(University_SignUp.this, email.getText().toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(University_SignUp.this, pass.getText().toString(), Toast.LENGTH_SHORT).show();
*/
                                Intent in = new Intent(University_SignUp.this, UniPersonalSignUp.class);
                                in.putExtra("email", email.getText().toString());
                                in.putExtra("pass", pass.getText().toString());
                                in.putExtra("name", uname.getText().toString());
                                startActivity(in);
                            }
                            else
                            {
                                Toast.makeText(University_SignUp.this, "Account with entered username/email already exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(University_SignUp.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else
                    {
                        Toast.makeText(University_SignUp.this, "Passwords Must Be > 6 Characters", Toast.LENGTH_SHORT).show();

                    }

                }
                else
                {
                    Toast.makeText(University_SignUp.this, "All Fields Must Be Filled Correctly", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}