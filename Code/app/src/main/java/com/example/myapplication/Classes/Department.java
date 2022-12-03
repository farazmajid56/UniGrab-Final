package com.example.myapplication.Classes;

import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<UndergraduteProgram> ugprgms;
    private ArrayList<GraduateProgram> gprgms;

    public Department(String n, ArrayList<UndergraduteProgram> obj, ArrayList<GraduateProgram> obj1)
    {
        this.name = n;
        this.ugprgms = obj;
        this.gprgms = obj1;
    }

    public String getName() {
        return name;
    }
}
