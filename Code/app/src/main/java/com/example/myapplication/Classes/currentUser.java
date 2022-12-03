package com.example.myapplication.Classes;

public class currentUser{
    private static currentUser instance = null;
    Student s;
    University u;
    Admin a;

    private currentUser(Student obj, University obj1, Admin obj2)
    {
        this.s = obj;
        this.u = obj1;
        this.a = obj2;
    }

    public static currentUser getInstance(Student obj, University obj1, Admin obj2)
    {
        if(instance == null)
        {
            instance = new currentUser(obj, obj1, obj2);
        }
        return instance;
    }

    public Student getStu()
    {
        return this.s;
    }

    public University getU()
    {
        return this.u;
    }

    public Admin getA() {
        return a;
    }

    public void logout()
    {
        instance = null;
        this.s = null;
    }
}
