package com.example.myapplication.Classes;

import java.io.Serializable;

public class profinfo implements Serializable {
    private String fname;
    private String lname;
    private String designation;
    private String email;

    public profinfo(String f, String l, String Designation, String Email)
    {
        this.fname = f;
        this.lname = l;
        this.designation = Designation;
        this.email = Email;
    }

    public String getDesignation() {
        return designation;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
}
