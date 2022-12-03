package com.example.myapplication.Classes;

import static com.example.myapplication.MainActivity.Classes;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UniversityManager extends dbConnection{

    public boolean checkUsername(Context ptr, String name)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] u where u.userName = '"+name+"'");

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to check username. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public void addUniDepartment(Context ptr,String uname, String dname)
    {
        int uniId = getUniID(ptr, uname);

        if(connection!=null)
        {
            Statement s1=null;
            try
            {
                s1 = connection.createStatement();
                String query = "insert into Department values("+uniId+", '"+dname+"')";
                s1.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //   Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void editUserDetails(Context ptr,String uname,String email,String pass, String prev)
    {
        if(connection!=null)
        {
            Statement s1=null;
            try
            {
                s1 = connection.createStatement();
                String query = "update [User] set userName = '"+uname+"', email = '"+email+"', password = '"+pass+"' where userName = '"+prev+"'";
                s1.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
               // Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void editUniDetails(Context ptr,String contact,String life,int ranking,int fee,double lati,double longi,String loc, String uname)
    {
        int uniId = getUniID(ptr, uname);

        if(connection!=null)
        {
            Statement s1=null;
            try
            {
                s1 = connection.createStatement();
                String query = "update University set phone = '"+contact+"', ranking = "+ranking+", campusLife = '"+life+"', location = '"+loc+"', latitude = "+lati+", longitude = "+longi+", admissionFee = "+fee+" where idUniversity = "+uniId+"";
                s1.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
             //   Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void getDepts(Context ptr, String universityname, List<String> depts)
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

            }
            catch(SQLException e)
            {
                e.printStackTrace();
              //  Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean checkDepartment(Context ptr, String name, String dname)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity where a.userName = '"+name+"' and d.name = '"+dname+"'");

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to check department. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public void editUniDepartment(Context ptr,String uname, String dname, String prev)
    {
        int uniId = getUniID(ptr, uname);

        if(connection!=null)
        {
            Statement s1=null;
            try
            {
                s1 = connection.createStatement();
                String query = "update Department set name = '"+dname+"' where name = '"+prev+"' and idUniversity = "+uniId+"";
                s1.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
             //   Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


    public List<String> getProgramsOfDept(Context ptr, String universityname, String deptname)
    {
        List<String> arr = new ArrayList<>();
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
                Toast.makeText(ptr, "Unable to get program of dept. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return arr;
    }

    public int getProgramID(Context ptr, String name, String dname, String pname)
    {
        int pid = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select p.idProgram from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment where a.userName = '"+name+"' and d.name = '"+dname+"' and p.name = '"+pname+"'");

                while(resultSet.next())
                {
                    pid = resultSet.getInt(1);
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to get program id. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return pid;
    }



    public boolean checkUGProgram(Context ptr, String name, String dname, String pname)
    {
        int pid = getProgramID(ptr, name, dname, pname);
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from UndergraduateProgram ug where ug.idUGProgram = "+pid);

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1"))
                    {
                        return true;
                    }
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to check ug program. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return false;
    }


    public boolean checkGProgram(Context ptr, String name, String dname, String pname)
    {
        int pid = getProgramID(ptr, name, dname, pname);
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from GraduateProgram ug where ug.idGProgram = "+pid+"");

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to check g program. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return false;
    }


    public feeinfo getProgramFee(Context ptr, String name, String dname, String pname)
    {
        int pid = getProgramID(ptr, name, dname, pname);
        feeinfo obj = null;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select p.name, p.feePerCreditHour, p.creditHours from Program p where p.idProgram = "+pid+"");

                while(resultSet.next())
                {
                    obj = new feeinfo(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to get fee info. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return obj;
    }

    public int getUGMarks(Context ptr, String name, String dname, String pname)
    {
        int pid = getProgramID(ptr, name, dname, pname);
        int marks = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select ug.minMarks from UndergraduateProgram ug where ug.idUGProgram = "+pid+"");

                while(resultSet.next())
                {
                    marks = resultSet.getInt(1);
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to get ugmarks. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return marks;
    }

    public float getGCgpa(Context ptr, String name, String dname, String pname)
    {
        int pid = getProgramID(ptr, name, dname, pname);
        float marks = 0.0f;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select ug.minCGPA from GraduateProgram ug where ug.idGProgram = "+pid+"");

                while(resultSet.next())
                {
                    marks = resultSet.getInt(1);
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to get cgpa. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return marks;
    }


    public boolean checkUniquenessProgram(Context ptr, String name, String dname, String pname)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on p.idDepartment = d.idDepartment where a.userName = '"+name+"' and p.name = '"+pname+"'");

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to check program uniqueness. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public void editUniUGprogram(Context ptr,String uname, String dname, String pname, int cr, int fpc, int marks, String prev)
    {
        int pid = getProgramID(ptr, uname, dname, prev);
        if(connection!=null)
        {
            Statement s1 = null;
            Statement s2 = null;

            try
            {
                s1 = connection.createStatement();
                String query = "update Program set name = '"+pname+"', creditHours = "+cr+", feePerCreditHour = "+fpc+"  where idProgram = "+pid+"";
                s1.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

            try
            {
                s2 = connection.createStatement();
                String query = "update UndergraduateProgram set minMarks = "+marks+" where idUGProgram = "+pid+"";
                s2.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            //    Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


    public void editUniGprogram(Context ptr,String uname, String dname, String pname, int cr, int fpc, float marks, String prev)
    {
        int pid = getProgramID(ptr, uname, dname, prev);
        if(connection!=null)
        {
            Statement s1 = null;
            Statement s2 = null;

            try
            {
                s1 = connection.createStatement();
                String query = "update Program set name = '"+pname+"', creditHours = "+cr+", feePerCreditHour = "+fpc+"  where idProgram = "+pid;
                s1.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to edit. Kindly try again", Toast.LENGTH_SHORT).show();
            }

            try
            {
                s2 = connection.createStatement();
                String query = "update GraduateProgram set minCGPA = "+marks+" where idGProgram = "+pid;
                s2.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
             //   Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public int getUniID(Context ptr, String name)
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


    public boolean checkUniquenessAlumni(Context ptr, String name, String pname)
    {
        int uid = getUniID(ptr, name);

        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] a join University u on a.idUser = u.idUniversity join Alumni p on u.idUniversity = p.idUniversity where a.userName = '"+name+"' and p.name = '"+pname+"'");

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return false;
                    } else {
                        return true;
                    }
                }
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

        return true;
    }


    public void editUniAlumni(Context ptr,String uname, String pname, String c, int b, String prev)
    {
        int uid = getUniID(ptr, uname);
        if(connection!=null)
        {
            Statement s1 = null;

            try
            {
                s1 = connection.createStatement();
                String query = "update Alumni set name = '"+pname+"', placementCompany = '"+c+"', batch = "+b+" where idUniversity = "+uid+" and name = '"+prev+"'";
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

    public void getFacultyOfDept(Context ptr, String universityname, String deptname, List<profinfo> arr )
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


    public boolean checkUniquenessFaculty(Context ptr, String name, String email)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Faculty f on f.idDepartment = d.idDepartment where f.email = '"+email+"' and a.userName = '"+name+"'");

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public int getDeptId(Context ptr, String uname, String dname)
    {
        int pid = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select d.idDepartment from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity where a.userName = '"+uname+"' and d.name = '"+dname+"'");

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


    public void editUniFaculty(Context ptr, String uname, String dname, String fname, String lname, String email, String desig, String prev)
    {
        int uid = getUniID(ptr, uname);
        int did = getDeptId(ptr, uname, dname);
        if(connection!=null)
        {
            Statement s1 = null;

            try
            {
                s1 = connection.createStatement();
                String query ="update Faculty set firstName = '"+fname+"', lastName = '"+lname+"', email = '"+email+"', designantion = '"+desig+"' where email = '"+prev+"' and idDepartment = "+did+" and idUniversity = "+uid;
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


    public boolean checkUniquenessAid(Context ptr, String uname, String name)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] a join University u on a.idUser = u.idUniversity join FinancialAid f on f.idUniversity = u.idUniversity where a.userName = '"+uname+"' and f.name = '"+name+"'");

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return true;
    }


    public void editUniAid(Context ptr,String uname, String name, String detail, String prev)
    {
        int uid = getUniID(ptr, uname);
        if(connection!=null)
        {
            Statement s1 = null;

            try
            {
                s1 = connection.createStatement();
                String query = "update FinancialAid set name = '"+name+"', detail = '"+detail+"' where idUniversity = "+uid+" and name = '"+prev+"'";
                s1.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
          //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


    public void addUniFaculty(Context ptr, String uname, String dname, String fname, String lname, String email, String desig)
    {
        int uid = getUniID(ptr, uname);
        int did = getDeptId(ptr, uname, dname);
        if(connection!=null)
        {
            Statement s1 = null;

            try
            {
                s1 = connection.createStatement();
                String query ="insert into Faculty values( "+uid+", "+did+", '"+fname+"', '"+lname+"', '"+email+"', '"+desig+"')";
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


    public void addUniAlumni(Context ptr,String uname, String pname, String c, int b)
    {
        int uid = getUniID(ptr, uname);
        if(connection!=null)
        {
            Statement s1 = null;

            try
            {
                s1 = connection.createStatement();
                String query = "insert into Alumni values("+uid+", '"+pname+"', '"+c+"', "+b+")";
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


    public void addUniAid(Context ptr,String uname, String name, String detail)
    {
        int uid = getUniID(ptr, uname);
        if(connection!=null)
        {
            Statement s1 = null;

            try
            {
                s1 = connection.createStatement();
                String query = "insert into FinancialAid values("+uid+", '"+name+"', '"+detail+"')";
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
            //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void createProgramUnderGraduate(Context ptr, String Depname,String name, int credithr, int feepercredithr, int min_marks, ArrayList<String> preReq , String uniname)
    {

        if(connection != null)
        {
            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;
            Statement statement3 = null;
            Statement statement4 = null;
            Statement statement5 = null;
//            int UserId = 3;
            int Departmentid = 0;
            int Programid = 0;
            int UserId = 0;


            try
            {
                statement4 = connection.createStatement();
                String query3 = "select u.idUser from [User] u where u.[userName] = '"+uniname+"'";
                ResultSet resultSet3 = statement4.executeQuery(query3);

                while(resultSet3.next())
                {
                    UserId = resultSet3.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            try
            {
                statement1 = connection.createStatement();
                String query1 = "select u.idDepartment from [Department] u where u.idUniversity = "+UserId+" AND u.name = '"+Depname+"'";
                ResultSet resultSet1 = statement1.executeQuery(query1);

                while(resultSet1.next())
                {
                    Departmentid = resultSet1.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement2 = connection.createStatement();
                String query2 = "insert into Program values("+Departmentid+", '"+name+"', "+credithr+", "+feepercredithr+")";
                statement2.executeQuery(query2);

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement3 = connection.createStatement();
                String query3 = "select u.idProgram from [Program] u where u.idDepartment = "+Departmentid+"  AND u.name = '"+name+"'";
                ResultSet resultSet2 = statement3.executeQuery(query3);

                while(resultSet2.next())
                {
                    Programid = resultSet2.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            try {
                statement = connection.createStatement();
                String query = "insert into [UndergraduateProgram] values ("+Programid+", "+min_marks+")";
                statement.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            for(int i = 0; i < preReq.size(); i++)
            {
                try {
                    statement5 = connection.createStatement();
                    String query5 = "insert into [ugReqBG] values ('"+preReq.get(i)+"', "+Programid+")";
                    statement5.executeQuery(query5);
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                    //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

    }

    public void createProgramGraduate(Context ptr, String Depname,String name, int credithr, int feepercredithr, float Cgpa, ArrayList<String> preReq, String uniname)
    {

        if(connection != null)
        {
            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;
            Statement statement3 = null;
            Statement statement4 = null;
            Statement statement5 = null;
            Statement statement6 = null;
            //int UserId = 3;
            int Departmentid = 0;
            int Programid = 0;
            int UserId = 0;

            try
            {
                statement6 = connection.createStatement();
                String query3 = "select u.idUser from [User] u where u.[userName] = '"+uniname+"'";
                ResultSet resultSet3 = statement6.executeQuery(query3);

                while(resultSet3.next())
                {
                    UserId = resultSet3.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }



            try
            {
                statement1 = connection.createStatement();
                String query1 = "select u.idDepartment from [Department] u where u.idUniversity = "+UserId+" AND u.name = '"+Depname+"'";
                ResultSet resultSet1 = statement1.executeQuery(query1);

                while(resultSet1.next())
                {
                    Departmentid = resultSet1.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement2 = connection.createStatement();
                String query2 = "insert into Program values("+Departmentid+", '"+name+"', "+credithr+", "+feepercredithr+")";
                statement2.executeQuery(query2);

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement3 = connection.createStatement();
                String query3 = "select u.idProgram from [Program] u where u.idDepartment = "+Departmentid+"  AND u.name = '"+name+"'";
                ResultSet resultSet2 = statement3.executeQuery(query3);

                while(resultSet2.next())
                {
                    Programid = resultSet2.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            try {
                statement = connection.createStatement();
                String query = "insert into [GraduateProgram] values ("+Programid+", "+Cgpa+")";
                statement.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            for(int i = 0; i < preReq.size(); i++)
            {
                try {
                    statement5 = connection.createStatement();
                    String query5 = "insert into [gReqBG] values ('"+preReq.get(i)+"', "+Programid+")";
                    statement5.executeQuery(query5);
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                    //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

    }



    public int createDepartment(Context ptr, String Depname, String uniname)
    {

        if(connection != null)
        {
            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;
            int UniID = 0;

            try
            {
                statement1 = connection.createStatement();
                String query3 = "select u.idUser from [User] u where u.[userName] = '"+uniname+"'";
                ResultSet resultSet3 = statement1.executeQuery(query3);

                while(resultSet3.next())
                {
                    UniID = resultSet3.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            try
            {
                statement = connection.createStatement();
                String query4 = "insert into [Department] values("+UniID+", '"+Depname+"')";
                statement.executeQuery(query4);

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return 1;
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
            return 0;
        }


    }

    public int UniqueProgram(Context ptr, String Programname, String deptname, String uniname)
    {
        if(connection != null) {
            Statement statement = null;
            Statement statement1 = null;
            Statement statement3 = null;


            int sql_ret_id = 0;
            int uniid = 0;

            try
            {
                statement3 = connection.createStatement();
                String query3 = "select u.idUser from [User] u where u.[userName] = '"+uniname+"'";
                ResultSet resultSet3 = statement3.executeQuery(query3);

                while(resultSet3.next())
                {
                    uniid = resultSet3.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            try
            {
                statement = connection.createStatement();
                String query1 = "select u.idDepartment from [Department] u where u.name = '"+deptname+"' AND u.idUniversity = "+uniid+"";
                ResultSet resultSet1 = statement.executeQuery(query1);

                while(resultSet1.next())
                {
                    sql_ret_id = resultSet1.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(sql_ret_id == 0)
            {
                return 0;
            }


            try
            {
                String sql_ret_name = " ";
                statement1 = connection.createStatement();
                String query1 = "select u.name from [Program] u where u.idDepartment = "+sql_ret_id+" AND u.name = '"+Programname+"'";
                ResultSet resultSet1 = statement1.executeQuery(query1);

                while(resultSet1.next())
                {
                    sql_ret_name = resultSet1.getString(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }

                Log.d("sql return uniqueprog", Programname +sql_ret_name + sql_ret_id);

                if(!Programname.equals(sql_ret_name.toString()))
                {
                    return 1;
                }

                else {
                    return 2;
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
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return 2;
    }

    public int UniqueDepartment(Context ptr, String depname, String uniname)
    {
//        Log.d("sql unique depstart",  depname + Uniid);

        if(connection != null) {
            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;

            String sql_ret_id = " ";
            int sql_ret_id2 = 0;
            int Uniid = 0;


            try
            {
                statement2 = connection.createStatement();
                String query3 = "select u.idUser from [User] u where u.[userName] = '"+uniname+"'";
                ResultSet resultSet3 = statement2.executeQuery(query3);

                while(resultSet3.next())
                {
                    Uniid = resultSet3.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            try
            {

                statement1 = connection.createStatement();
                String query1 = "select u.ranking from [University] u where u.idUniversity = "+Uniid+"";
                ResultSet resultSet2 = statement1.executeQuery(query1);

                while(resultSet2.next())
                {
                    sql_ret_id2 = resultSet2.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(sql_ret_id2 == 0)
            {
                return 2;
            }


            try
            {

                statement = connection.createStatement();
                String query1 = "select u.name from [Department] u where u.name = '"+depname+"' AND u.idUniversity = "+Uniid+"";
                ResultSet resultSet1 = statement.executeQuery(query1);

                while(resultSet1.next())
                {
                    sql_ret_id = resultSet1.getString(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(!depname.equals(sql_ret_id.toString()))
            {
                //        Log.d("sql return unique dep", sql_ret_id.toString() + depname + Uniid);
                return 1;
            }

            else
                return 0;
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return 0;
    }

    public int DeleteDepartment(Context ptr, String DeptName, String uniname)
    {
        if(connection != null) {

            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;

            int sql_ret_id = 0;
            int uniid = 0;

            try
            {
                statement2 = connection.createStatement();
                String query3 = "select u.idUser from [User] u where u.[userName] = '"+uniname+"'";
                ResultSet resultSet3 = statement2.executeQuery(query3);

                while(resultSet3.next())
                {
                    uniid = resultSet3.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            try
            {

                statement = connection.createStatement();
                String query1 = "select u.idDepartment from [Department] u where u.name = '"+DeptName+"' AND u.idUniversity = "+uniid+"";
                ResultSet resultSet1 = statement.executeQuery(query1);

                while(resultSet1.next())
                {
                    sql_ret_id = resultSet1.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(sql_ret_id == 0)
            {
                return 0;
            }

            try
            {

                statement1 = connection.createStatement();
                String query1 = "Delete from [Department] where idDepartment = '"+sql_ret_id+"'";
                statement1.executeQuery(query1);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return 1;


        }

        return 0;

    }

    public int DeleteProgram(Context ptr, String DeptName, String ProgName,String uniname)
    {

        if(connection != null) {

            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;
            Statement statement3 = null;

            int sql_ret_id = 0, sql_ret_id2 = 0;
            int uniid = 0;


            try
            {
                statement2 = connection.createStatement();
                String query3 = "select u.idUser from [User] u where u.[userName] = '"+uniname+"'";
                ResultSet resultSet3 = statement2.executeQuery(query3);

                while(resultSet3.next())
                {
                    uniid = resultSet3.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            try
            {

                statement = connection.createStatement();
                String query1 = "select u.idDepartment from [Department] u where u.name = '"+DeptName+"' AND u.idUniversity = "+uniid+"";
                ResultSet resultSet1 = statement.executeQuery(query1);

                while(resultSet1.next())
                {
                    sql_ret_id = resultSet1.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(sql_ret_id == 0)
            {
                return 0;       //Dep not found
            }

            try
            {

                statement2 = connection.createStatement();
                String query2 = "select u.idProgram from [Program] u where u.name = '"+ProgName+"' AND u.idDepartment = "+sql_ret_id+"";
                ResultSet resultSet2 = statement2.executeQuery(query2);

                while(resultSet2.next())
                {
                    sql_ret_id2 = resultSet2.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(sql_ret_id2 == 0)
            {
                return 2;       //Program not found
            }


            try
            {

                statement1 = connection.createStatement();
                String query1 = "Delete from [Program] where idDepartment = '"+sql_ret_id+"' AND name = '"+ProgName+"'";
                statement1.executeQuery(query1);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return 1;           //deleted


        }

        return 0;           //error
    }

    public boolean checkUniquenessDept(Context ptr, String name, String dname, String pname)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select 1 from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity where a.userName = '"+name+"' and d.name = '"+dname+"'");


                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Unable to check program uniqueness. Kindly try again", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public int DeleteAlumini(Context ptr, String AlumName, String uniname)
    {
        if(connection != null) {

            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;

            int sql_ret_id = 0;
            int uniid = 0;

            try
            {
                statement2 = connection.createStatement();
                String query3 = "select u.idUser from [User] u where u.[userName] = '"+uniname+"'";
                ResultSet resultSet3 = statement2.executeQuery(query3);

                while(resultSet3.next())
                {
                    uniid = resultSet3.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(uniid == 0)
                return 0;

            try
            {

                statement1 = connection.createStatement();
                Log.d("alum info", uniid + AlumName);
                String query1 = "Delete from [Alumni] where idUniversity = "+uniid+" AND [name] = '"+AlumName+"'";
                statement1.executeQuery(query1);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return 1;


        }

        return 0;

    }

    public int DeleteFaculty(Context ptr, String Email,String uniname)
    {
        if(connection != null) {

            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;

            int sql_ret_id = 0;
            int uniid = 0;

            try
            {
                statement2 = connection.createStatement();
                String query3 = "select u.idUser from [User] u where u.[userName] = '"+uniname+"'";
                ResultSet resultSet3 = statement2.executeQuery(query3);

                while(resultSet3.next())
                {
                    uniid = resultSet3.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(uniid == 0)
                return 0;

            try
            {

                statement1 = connection.createStatement();
                String query1 = "Delete from [Faculty] where idUniversity = "+uniid+" AND email = '"+Email+"'";
                statement1.executeQuery(query1);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return 1;


        }

        return 0;

    }

    public int DeleteFAid(Context ptr, String name,String uniname)
    {
        if(connection != null) {

            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;

            int sql_ret_id = 0;
            int uniid = 0;

            try
            {
                statement2 = connection.createStatement();
                String query3 = "select u.idUser from [User] u where u.[userName] = '"+uniname+"'";
                ResultSet resultSet3 = statement2.executeQuery(query3);

                while(resultSet3.next())
                {
                    uniid = resultSet3.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }


            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(uniid == 0)
                return 0;

            try
            {

                statement1 = connection.createStatement();
                String query1 = "Delete from [FinancialAid] where idUniversity = "+uniid+" AND name = '"+name+"'";
                statement1.executeQuery(query1);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return 1;


        }

        return 0;

    }



}
