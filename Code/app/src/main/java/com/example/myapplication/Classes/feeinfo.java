package com.example.myapplication.Classes;

import java.io.Serializable;

public class feeinfo implements Serializable {
    private String degree;
    private int feePerCdthr;
    private int creditHrs;

    public feeinfo(String deg, int fpc, int ch)
    {
        this.degree = deg;
        this.feePerCdthr = fpc;
        this.creditHrs = ch;
    }

    public String getDegree() {
        return degree;
    }

    public int getCreditHrs() {
        return creditHrs;
    }

    public int getFeePerCdthr() {
        return feePerCdthr;
    }
}
