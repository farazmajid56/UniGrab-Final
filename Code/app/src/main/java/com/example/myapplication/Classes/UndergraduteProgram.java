package com.example.myapplication.Classes;


import java.util.ArrayList;

public class UndergraduteProgram extends Program{

    private int minMarks;
    private ArrayList<String> prereqs;

    public UndergraduteProgram(String name, int ch, int fpch, int m, ArrayList<String> pr)
    {
        super(name, ch, fpch);
        this.minMarks = m;
        this.prereqs = pr;
    }


}
