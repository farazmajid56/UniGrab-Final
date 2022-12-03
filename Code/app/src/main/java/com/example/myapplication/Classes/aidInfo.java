package com.example.myapplication.Classes;

import java.io.Serializable;

public class aidInfo implements Serializable {
    String name;
    String detail;
    aidInfo(String n, String d)
    {
        this.name = n;
        this.detail = d;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

}
