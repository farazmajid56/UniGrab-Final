package com.example.myapplication.Classes;

public class currentUserAdmin {
    private static currentUserAdmin instance = null;
    private Admin a;

    private currentUserAdmin(Admin obj)
    {
        this.a = obj;
    }

    public static currentUserAdmin getInstance(Admin obj2)
    {
        if(instance == null)
        {
            instance = new currentUserAdmin(obj2);
        }
        return instance;
    }

    public Admin getA() {
        return a;
    }
}
