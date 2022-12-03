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

public class uniComparer extends dbConnection{

    private String uniname;

    public void fillUniList(List<String> arr)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select a.userName from [User] a join University u on u.idUniversity = a.idUser\n");
                while(resultSet.next())
                {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
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
          //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


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

    public void getDepts(String universityname, List<String> depts)
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
                //   Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public List<String> getProgramsOfDept(String universityname, String deptname)
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
               // Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
          //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return arr;
    }

    public String getCampusLife(String uniname)
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
              //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
           // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return str;
    }

    public int getUniRank(String uniname)
    {
        int str = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.ranking from [User] us join University u on u.idUniversity = us.idUser where us.userName = '"+uniname+"'");
                while(resultSet.next())
                {
                    str = resultSet.getInt(1);
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
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
            // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return str;
    }

    public String getUniMail( String uniname)
    {
        String str = null;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select us.email from [User] us join University u on u.idUniversity = us.idUser where us.userName = '"+uniname+"'");
                while(resultSet.next())
                {
                    str = resultSet.getString(1);
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
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
            // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return str;
    }

    public String getUniPh( String uniname)
    {
        String str = null;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.phone from [User] us join University u on u.idUniversity = us.idUser where us.userName = '"+uniname+"'");
                while(resultSet.next())
                {
                    str = resultSet.getString(1);
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
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
            // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return str;
    }

    public String getUniAddr(String uniname)
    {
        String str = null;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.location from [User] us join University u on u.idUniversity = us.idUser where us.userName = '"+uniname+"'");
                while(resultSet.next())
                {
                    str = resultSet.getString(1);
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
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
            // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return str;
    }

}
