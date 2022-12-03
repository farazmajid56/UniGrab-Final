package com.example.myapplication.Classes;

public class GraduateStudent extends Student{

    private float cgpa;
    private String bsDeg;

    public GraduateStudent(String name, String email, String pass, String eduType, String d, String fname, String lname, float Cgpa, String bsdeg, int isadmin, int isdisabled)
    {
        super(name, email, pass, eduType, d, fname, lname, isadmin, isdisabled);
        this.cgpa = Cgpa;
        this.bsDeg = bsdeg;
    }

    public float getCgpa() {
        return cgpa;
    }

    public String getBsDeg() {
        return bsDeg;
    }

    public void setBsDeg(String bsDeg) {
        this.bsDeg = bsDeg;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }
}
