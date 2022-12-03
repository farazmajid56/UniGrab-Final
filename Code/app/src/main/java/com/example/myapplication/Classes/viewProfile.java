package com.example.myapplication.Classes;

import static com.example.myapplication.MainActivity.Classes;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class viewProfile extends dbConnection{


    public void getUniveristy(String universityname, ArrayList<String> depts, List<alumniInfo> arr, List<feeinfo> f, List<aidInfo> a, List<reviewInfo> r)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select d.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet.next())
                {
                    depts.add(resultSet.getString(1));
                }

                ResultSet resultSet1 = statement.executeQuery("select al.name, al.placementCompany, al.batch from [User] a join University u on a.idUser = u.idUniversity join Alumni al on u.idUniversity = al.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet1.next())
                {
                    arr.add(new alumniInfo(resultSet1.getString(1), resultSet1.getString(2), Integer.toString(resultSet1.getInt(3))));
                }

                ResultSet resultSet2 = statement.executeQuery("select p.name, p.feePerCreditHour, p.creditHours from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment where a.userName = '"+universityname+"'");
                while(resultSet2.next())
                {
                    f.add(new feeinfo(resultSet2.getString(1), resultSet2.getInt(2), resultSet2.getInt(3)));
                }

                ResultSet resultSet3 = statement.executeQuery("select fa.name, fa.detail from [User] a join University u on a.idUser = u.idUniversity join FinancialAid fa on u.idUniversity = fa.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet3.next())
                {
                    a.add(new aidInfo(resultSet3.getString(1), resultSet3.getString(2)));
                }

                ResultSet resultSet4 = statement.executeQuery("select r.review, r.stars from [User] a join University u on a.idUser = u.idUniversity join Review r on u.idUniversity = r.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet4.next())
                {
                    r.add(new reviewInfo(resultSet4.getString(1), resultSet4.getInt(2)));
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
             //   Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
           // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


    public void getUni(Context ptr, String universityname, ArrayList<String> depts, List<alumniInfo> arr, List<feeinfo> f, List<aidInfo> a, List<reviewInfo> r)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select d.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet.next())
                {
                    depts.add(resultSet.getString(1));
                }

                ResultSet resultSet1 = statement.executeQuery("select al.name, al.placementCompany, al.batch from [User] a join University u on a.idUser = u.idUniversity join Alumni al on u.idUniversity = al.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet1.next())
                {
                    arr.add(new alumniInfo(resultSet1.getString(1), resultSet1.getString(2), Integer.toString(resultSet1.getInt(3))));
                }

                ResultSet resultSet2 = statement.executeQuery("select p.name, p.feePerCreditHour, p.creditHours from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment where a.userName = '"+universityname+"'");
                while(resultSet2.next())
                {
                    f.add(new feeinfo(resultSet2.getString(1), resultSet2.getInt(2), resultSet2.getInt(3)));
                }

                ResultSet resultSet3 = statement.executeQuery("select fa.name, fa.detail from [User] a join University u on a.idUser = u.idUniversity join FinancialAid fa on u.idUniversity = fa.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet3.next())
                {
                    a.add(new aidInfo(resultSet3.getString(1), resultSet3.getString(2)));
                }

                ResultSet resultSet4 = statement.executeQuery("select r.review, r.stars from [User] a join University u on a.idUser = u.idUniversity join Review r on u.idUniversity = r.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet4.next())
                {
                    r.add(new reviewInfo(resultSet4.getString(1), resultSet4.getInt(2)));
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //   Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


    public void getFacultyOfDept(Context ptr, String universityname, String deptname, List<profinfo> arr)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select f.firstName, f.lastName, f.designantion, f.email from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Faculty f on d.idDepartment = f.idDepartment where a.userName = '"+universityname+"' and d.name = '"+deptname+"'");
                while(resultSet.next())
                {
                    arr.add(new profinfo(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


    public void getProgramsOfDept(Context ptr, String universityname, String deptname, List<String> arr)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select p.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment where a.userName = '"+universityname+"' and d.name = '"+deptname+"'");
                while(resultSet.next())
                {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public String getCampusLife(Context ptr, String uniname)
    {
        String str = null;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.campusLife from [User] us join University u on u.idUniversity = us.idUser where us.userName = '"+uniname+"'");
                while(resultSet.next())
                {
                    str = resultSet.getString(1);
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return str;
    }


    public int getUniAdmissionFee(Context ptr, String uname)
    {
        int ans = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.admissionFee from [User] a join University u on a.idUser = u.idUniversity where a.userName = '"+uname+"'");
                while(resultSet.next())
                {
                    ans = resultSet.getInt(1);
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }


        return ans;
    }


    public void getImages(Context ptr, String uniname, ArrayList<String> arr, ArrayList<String> arr2)
    {
        if(connection != null)
        {
            Statement statement = null;
            int uid = 0;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.idUniversity from [User] a join University u on a.idUser = u.idUniversity where a.userName = '"+uniname+"'");

                while(resultSet.next())
                {
                    uid = resultSet.getInt(1);
                }

                ResultSet resultSet1 = statement.executeQuery("select i.imgBin, i.description from University u join Image i on u.idUniversity = i.idUniversity where u.idUniversity = "+uid);

                while(resultSet1.next())
                {
                    arr.add(resultSet1.getString(1));
                    arr2.add(resultSet1.getString(2));
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void getTexts(Context ptr, String uniname, ArrayList<String> arr)
    {
        if(connection != null)
        {
            Statement statement = null;
            int uid = 0;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.idUniversity from [User] a join University u on a.idUser = u.idUniversity where a.userName = '"+uniname+"'");

                while(resultSet.next())
                {
                    uid = resultSet.getInt(1);
                }

                ResultSet resultSet1 = statement.executeQuery("select i.description from University u join text i on u.idUniversity = i.idUniversity where u.idUniversity = "+uid);

                while(resultSet1.next())
                {
                    arr.add(resultSet1.getString(1));
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            //   Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


    public void getVideos(Context ptr, String uniname, ArrayList<String> arr)
    {

        if(connection != null)
        {
            Statement statement = null;
            int uid = 0;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.idUniversity from [User] a join University u on a.idUser = u.idUniversity where a.userName = '"+uniname+"'");

                while(resultSet.next())
                {
                    uid = resultSet.getInt(1);
                }

                ResultSet resultSet1 = statement.executeQuery("select i.link from University u join Video i on u.idUniversity = i.idUniversity where u.idUniversity = "+uid);

                while(resultSet1.next())
                {
                    arr.add(resultSet1.getString(1));
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            //   Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

    }

    public int getUGStuMarks(Context ptr, String name)
    {
        int str = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.marks from [User] a join Undergraduate u on u.idStudent = a.idUser where a.userName = '"+name+"'");
                while(resultSet.next())
                {
                    str = resultSet.getInt(1);
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return str;
    }


    public float getGStuCgpa(Context ptr, String name)
    {
        float str = 0.0f;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.CGPA from [User] a join Graduate u on u.idStudent = a.idUser where a.userName = '"+name+"'");
                while(resultSet.next())
                {
                    str = resultSet.getFloat(1);
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return str;
    }

    public int getStuID(Context ptr, String name)
    {
        int pid = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select a.idUser from [User] a where a.userName = '"+name+"'");

                while(resultSet.next())
                {
                    pid = resultSet.getInt(1);
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to get uid. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return pid;
    }

    public void editUGStu(Context ptr,String name, int marks, String combo)
    {
        int uid = getStuID(ptr, name);
        if(connection!=null)
        {
            Statement s1 = null;

            try
            {
                s1 = connection.createStatement();
                String query = "update Undergraduate set marks = "+marks+", subjectCombo = '"+combo+"' where idStudent = "+uid;
                s1.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void editGStu(Context ptr,String name, float marks, String combo)
    {
        int uid = getStuID(ptr, name);
        if(connection!=null)
        {
            Statement s1 = null;

            try
            {
                s1 = connection.createStatement();
                String query = "update Graduate set CGPA = "+marks+", BSDegree = '"+combo+"' where idStudent = "+uid;
                s1.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }




}
