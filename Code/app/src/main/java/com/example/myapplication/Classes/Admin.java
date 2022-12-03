package com.example.myapplication.Classes;

import android.content.Context;

import java.util.ArrayList;

public class Admin extends Visitor{

    accountManager am;

    public Admin(String name, String email, String pass,  int isadmin, int isdisabled)
    {
        super(name, email, pass, isadmin, isdisabled);
        this.am = new accountManager();
    }

    public void getfeedback(Context ptr, ArrayList<String> ar)
    {
        this.am.connectToDb(ptr);
        this.am.getFeedback(ar);
    }

    public String getType(){
        return "Admin";
    }

}
