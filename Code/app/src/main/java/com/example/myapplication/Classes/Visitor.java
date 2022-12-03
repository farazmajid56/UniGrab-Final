package com.example.myapplication.Classes;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.contextaware.ContextAwareHelper;

public class Visitor extends User{

    public Visitor()
    {
        this.ac = new accountCreator();
        this.au = new authenticateUser();
        this.name = null;
        this.email = null;
        this.password = null;
    }

    public Visitor(String name, String email, String pass, int admin, int disabled)
    {
        this.name = name;
        this.email = email;
        this.password = pass;
        this.isAdmin = admin;
        this.isDisabled = disabled;
    }

    public boolean signInStu(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
        if(this.au.verifyInfoStu(ptr, uname.getText().toString(), pass.getText().toString()) == true)
        {
            return true;
        }
        return false;
    }

    public boolean signInUni(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
        if(this.au.verifyInfoUni(ptr, uname.getText().toString(), pass.getText().toString()) == true)
        {
            return true;
        }
        return false;
    }

    public boolean signInAdmin(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
        if(this.au.verifyInfoAdmin(ptr, uname.getText().toString(), pass.getText().toString()) == true)
        {
            return true;
        }
        return false;
    }

    public String getEducationType(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
        return this.au.getEduType(ptr, uname.getText().toString(), pass.getText().toString());
    }

    public UndergradStudent makeUndergradObj(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
       return this.au.getUGStu(ptr, uname.getText().toString(), pass.getText().toString());
    }

    public GraduateStudent makeGradObj(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
        return this.au.getGStu(ptr, uname.getText().toString(), pass.getText().toString());
    }

    public University makeUni(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
        return this.au.getUni(ptr, uname.getText().toString(), pass.getText().toString());
    }

    public Admin makeAdmin(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
        return this.au.getAdmin(uname.getText().toString(), pass.getText().toString());
    }

    public void insertUGStu(Context ptr, String name, String email, String pass, String eduType, String d, String fname, String lname, int m, String subjects, int isadmin, int isdisabled)
    {
        this.ac.connectToDb(ptr);
        this.ac.createUndergradAcc(ptr, name, email, pass, eduType, d, fname, lname, m, subjects, isadmin, isdisabled);
    }

    public void insertGStu(Context ptr, String name, String email, String pass, String eduType, String d, String fname, String lname, float m, String subjects, int isadmin, int isdisabled)
    {
        this.ac.connectToDb(ptr);
        this.ac.createGradAcc(ptr, name, email, pass, eduType, d, fname, lname, m, subjects, isadmin, isdisabled);
    }

    public void insertUni(Context ptr, String contact, String life, int rank, int fee, String uname, Double lati, Double longi, String loc, String email, String pass)
    {
        this.ac.connectToDb(ptr);
        this.ac.insertUniDetails(ptr, contact, life, rank, fee, uname, lati, longi, loc, email, pass);
    }

    public String getType(){
        return "Visitor";
    }
  /*  public String getUsername() {
        return this.name;
    }*/
}
