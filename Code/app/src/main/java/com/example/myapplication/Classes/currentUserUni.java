package com.example.myapplication.Classes;

public class currentUserUni {
    private static currentUserUni instance = null;
    private University a;

    private currentUserUni(University obj)
    {
        this.a = obj;
    }

    public static currentUserUni getInstance(University obj2)
    {
        if(instance == null)
        {
            instance = new currentUserUni(obj2);
        }
        return instance;
    }

    public University getU() {
        return a;
    }

    public void logout()
    {
        instance = null;
        this.a = null;
    }

    public void setUniName(String name)
    {
        this.a.setName(name);
    }

    public void setUniEmail(String name)
    {
        this.a.setEmail(name);
    }

    public void setUniPass(String name)
    {
        this.a.setPassword(name);
    }

    public void setUniRank(int r)
    {
        this.a.setRanking(r);
    }

    public void setUniLoc(String name)
    {
        this.a.setLocation(name);
    }

    public void setUniFee(int f)
    {
        this.a.setAdmissionFee(f);
    }

    public void setUniLife(String name)
    {
        this.a.setCampusLife(name);
    }

    public void setUniLat(double l)
    {
        this.a.setLatitude(l);
    }

    public void setUniLon(double l)
    {
        this.a.setLongitude(l);
    }

    public void setUniPh(String name)
    {
        this.a.setPhone(name);
    }


}
