package com.example.myapplication.Classes;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class University extends Visitor{
    private String phone;
    private String campusLife;
    private int ranking;
    protected String location;
    protected double longitude;
    protected double latitude;
    private int admissionFee;
    private ArrayList<Department> departments;
    private ArrayList<aidInfo> aids;
    private ArrayList<alumniInfo> alums;
    private ArrayList<reviewInfo> reviews;
    private ArrayList<profinfo> faculty;
    private ArrayList<feeinfo> fees;
    private ArrayList<imageClass> imgs;
    private ArrayList<String> vids;
    private ArrayList<String> txts;
    private managePost mp;
    private viewProfile vp;
    private UniversityManager um;

    University(String name, String email, String pass, String ph, String campus, int rank, String location, double longitude, double latitude, int isadmin, int isdisabled, ArrayList<Department> depts, int af, ArrayList<aidInfo> a, ArrayList<alumniInfo> alumnis, ArrayList<reviewInfo> r, ArrayList<profinfo> faculti, ArrayList<feeinfo> fee, ArrayList<imageClass> im, ArrayList<String> vid, ArrayList<String> txt) {
        super(name, email, pass, isadmin, isdisabled);
        this.phone = ph;
        this.campusLife = campus;
        this.ranking = rank;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.departments = depts;
        this.admissionFee = af;
        this.aids = a;
        this.alums = alumnis;
        this.reviews = r;
        this.faculty = faculti;
        this.fees = fee;
        this.imgs = im;
        this.vids = vid;
        this.txts = txt;
        this.mp = new managePost();
        this.vp = new viewProfile();
        this.um = new UniversityManager();
    }

    public University(int uid,String name, String email,int isdisabled)
    {
        super(name, email, "NULL", 0, isdisabled);
        this.uid = uid;
        this.mp = null;
        this.vp = null;
    }
    public void addDept(Context ptr, String uname, String dname)
    {
        this.um.connectToDb(ptr);
        this.um.addUniDepartment(ptr, uname, dname);
    }

    public String getType(){ return "University"; }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public ArrayList<aidInfo> getAids() {
        return aids;
    }

    public ArrayList<alumniInfo> getAlums() {
        return alums;
    }

    public ArrayList<feeinfo> getFees() {
        return fees;
    }

    public ArrayList<profinfo> getFaculty() {
        return faculty;
    }

    public ArrayList<reviewInfo> getReviews() {
        return reviews;
    }

    public ArrayList<imageClass> getImgs() {
        return imgs;
    }

    public ArrayList<String> getTxts() {
        return txts;
    }

    public ArrayList<String> getVids() {
        return vids;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getRanking() {
        return ranking;
    }

    public String getCampusLife() {
        return campusLife;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public int getAdmissionFee() {
        return admissionFee;
    }

    public void getUni(Context ptr, String universityname, ArrayList<String> depts, List<alumniInfo> arr, List<feeinfo> f, List<aidInfo> a, List<reviewInfo> r)
    {
        this.vp.connectToDb(ptr);
        this.vp.getUniveristy(universityname, depts, arr, f, a, r);
    }

    @Override
    public void setEmail(String str) {
        super.setEmail(str);
    }

    @Override
    public void setPassword(String str) {
        super.setPassword(str);
    }

    @Override
    public void setUsername(String str) {
        super.setUsername(str);
    }

    @Override
    public void setIsDisabled(int a) {
        super.setIsDisabled(a);
    }

    public void setAdmissionFee(int admissionFee) {
        this.admissionFee = admissionFee;
    }

    public void setCampusLife(String campusLife) {
        this.campusLife = campusLife;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setFees(ArrayList<feeinfo> fees) {
        this.fees = fees;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }


    public boolean checkName(Context ptr, String s)
    {
        this.um.connectToDb(ptr);
        return this.um.checkUsername(ptr, s);
    }

    public void editAcc(Context ptr, String n, String e, String p, String prevname)
    {
        this.um.connectToDb(ptr);
        this.um.editUserDetails(ptr, n, e, p, prevname);
    }

    public void editPersonal(Context ptr, String contact, String life, int rank, int fee, Double lati, Double longi, String loc, String uname)
    {
        this.um.connectToDb(ptr);
        this.um.editUniDetails(ptr, contact, life, rank, fee, lati, longi, loc, uname);
    }

    public void getDeptsUni(Context ptr, String uniname, List<String> arr)
    {
        this.um.connectToDb(ptr);
        this.um.getDepts(ptr, uniname, arr);
    }

    public boolean checkUniqueDept(Context ptr, String uname, String dname)
    {
        this.um.connectToDb(ptr);
        return this.um.checkDepartment(ptr, uname, dname);
    }

    public void editDept(Context ptr, String uname, String dname, String prev)
    {
        this.um.connectToDb(ptr);
        this.um.editUniDepartment(ptr, uname, dname, prev);
    }

    public List<String> getDeptPrgms(Context ptr, String deptname)
    {
        this.um.connectToDb(ptr);

        return this.um.getProgramsOfDept(ptr, this.getName(), deptname);
    }

    public boolean checkProgramUG(Context ptr, String uname, String dname, String pname)
    {
        this.um.connectToDb(ptr);
        return this.um.checkUGProgram(ptr, uname, dname, pname);
    }

    public feeinfo getProgFeeInfo(Context ptr, String uname, String dname, String pname)
    {
        this.um.connectToDb(ptr);
        return this.um.getProgramFee(ptr, uname, dname, pname);
    }

    public int getProgUGMinMarks(Context ptr, String uname, String dname, String pname)
    {
        this.um.connectToDb(ptr);
        return this.um.getUGMarks(ptr, uname, dname, pname);
    }

    public float getProgGMinCGPA(Context ptr, String uname, String dname, String pname)
    {
        this.um.connectToDb(ptr);
        return this.um.getGCgpa(ptr, uname, dname, pname);
    }

    public boolean checkProgramUniqueness(Context ptr, String uname, String dname, String pname)
    {
        this.um.connectToDb(ptr);
        return this.um.checkUniquenessProgram(ptr, uname, dname, pname);
    }

    public void editUGProgram(Context ptr, String uname, String dname, String pname, int c, int fpc, int m, String prev)
    {
        this.um.connectToDb(ptr);
        this.um.editUniUGprogram(ptr, uname, dname, pname, c, fpc, m, prev);
    }

    public void editGProgram(Context ptr, String uname, String dname, String pname, int c, int fpc, float m, String prev)
    {
        this.um.connectToDb(ptr);
        this.um.editUniGprogram(ptr, uname, dname, pname, c, fpc, m, prev);
    }

    public boolean checkAlumniUniqueness(Context ptr, String uname, String pname)
    {
        this.um.connectToDb(ptr);
        return this.um.checkUniquenessAlumni(ptr, uname, pname);
    }

    public void editAlumni(Context ptr, String uname, String pname, String c, int b, String prev)
    {
        this.um.connectToDb(ptr);
        this.um.editUniAlumni(ptr, uname, pname, c, b, prev);
    }

    public void getFacultyContent(Context ptr, String universityname, String deptname, List<profinfo> arr )
    {
        this.um.connectToDb(ptr);
        this.um.getFacultyOfDept(ptr, universityname, deptname, arr);
    }

    public boolean checkFacultyUniqueness(Context ptr, String uname, String email)
    {
        this.um.connectToDb(ptr);
        return this.um.checkUniquenessFaculty(ptr, uname, email);
    }

    public void editFaculty(Context ptr, String uname, String dname, String fname, String lname, String email, String desig, String prev)
    {
        this.um.connectToDb(ptr);
        this.um.editUniFaculty(ptr, uname, dname, fname, lname, email, desig, prev);
    }

    public boolean checkAidUniqueness(Context ptr, String uname, String name)
    {
        this.um.connectToDb(ptr);
        return this.um.checkUniquenessAid(ptr, uname, name);
    }

    public void editAid(Context ptr, String uname, String name, String detail, String prev)
    {
        this.um.connectToDb(ptr);
        this.um.editUniAid(ptr, uname, name, detail, prev);
    }

    public boolean checkEmailUnique(Context ptr, String email)
    {
        this.ac.connectToDb(ptr);
        return this.ac.checkUniquenessEmail(ptr, email);
    }

    public void addFaculty(Context ptr, String uname, String dname, String fname, String lname, String email, String desig)
    {
        this.um.connectToDb(ptr);
        this.um.addUniFaculty(ptr, uname, dname, fname, lname, email, desig);
    }

    public void addAlumni(Context ptr, String uname, String pname, String c, int b)
    {
        this.um.connectToDb(ptr);
        this.um.addUniAlumni(ptr, uname, pname, c, b);
    }

    public void addAid(Context ptr, String uname, String name, String detail)
    {
        this.um.connectToDb(ptr);
        this.um.addUniAid(ptr, uname, name, detail);
    }

}
