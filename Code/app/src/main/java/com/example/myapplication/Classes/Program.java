package com.example.myapplication.Classes;

public abstract class  Program {
    protected String name;
    protected int creditHours;
    protected int feepch;

    public Program(String name, int ch, int fpch) {
        this.name = name;
        this.creditHours = ch;
        this.feepch = fpch;
    }
}
