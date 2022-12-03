package com.example.myapplication.Classes;

public class faqInfo {
    private String question;
    private String ans;

    public faqInfo(String q, String a)
    {
        this.question = q;
        this.ans = a;
    }

    public String getAns() {
        return ans;
    }

    public String getQuestion() {
        return question;
    }
}
