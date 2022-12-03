package com.example.myapplication.Classes;

import java.util.ArrayList;

public class GraduateProgram extends Program{
    private float mincgpa;
    private ArrayList<String> prereqs;

    public GraduateProgram(String name, int ch, int fpch, float m, ArrayList<String> pr) {
        super(name, ch, fpch);
        this.mincgpa = m;
        this.prereqs = pr;
    }
}
