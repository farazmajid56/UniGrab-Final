package com.example.myapplication.Classes;

import java.util.Date;

public class UndergradStudent extends Student{
    private int marks;
    private String subjectCombo;


    public UndergradStudent(String name, String email, String pass, String eduType, String d, String fname, String lname, int m, String subjects, int isadmin, int isdisabled)
    {
        super(name, email, pass, eduType, d, fname, lname, isadmin, isdisabled);
        this.marks = m;
        this.subjectCombo = subjects;
    }

    public int getMarks() {
        return marks;
    }

    public String getSubjectCombo() {
        return subjectCombo;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public void setSubjectCombo(String subjectCombo) {
        this.subjectCombo = subjectCombo;
    }
}
