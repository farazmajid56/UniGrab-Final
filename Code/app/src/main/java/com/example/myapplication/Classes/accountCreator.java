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

public class accountCreator extends dbConnection{

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
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public void createUndergradAcc(Context ptr, String name, String email, String pass, String eduType, String d, String fname, String lname, int m, String subjects, int isadmin, int isdisabled)
    {
        if(connection != null)
        {
            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;
            Statement statement3 = null;
            int stuId = 0;
            try {
                statement = connection.createStatement();
                String query = "insert into [User] values ('"+name+"', '"+email+"', '"+pass+"',"+isadmin+","+isdisabled+", 'Student')";
                statement.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement1 = connection.createStatement();
                String query1 = "select u.idUser from [User] u where u.userName = '"+name+"'";
                ResultSet resultSet1 = statement1.executeQuery(query1);

                while(resultSet1.next())
                {
                    stuId = resultSet1.getInt(1);
                 //   Toast.makeText(ptr, "userid:"+String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                 //   Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
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
                String query2 = "insert into Student values("+stuId+", '"+eduType+"', '"+d+"', '"+fname+"', '"+lname+"')";
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
                String query3 = "insert into Undergraduate values("+stuId+", "+m+", '"+subjects+"')";
                statement3.executeQuery(query3);

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

    public void createGradAcc(Context ptr, String name, String email, String pass, String eduType, String d, String fname, String lname, float m, String subjects, int isadmin, int isdisabled)
    {
        if(connection != null)
        {
            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;
            Statement statement3 = null;
            int stuId = 0;
            try {
                statement = connection.createStatement();
                String query = "insert into [User] values ('"+name+"', '"+email+"', '"+pass+"',0,0,'Student')";
                statement.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement1 = connection.createStatement();
                String query1 = "select u.idUser from [User] u where u.userName = '"+name+"'";
                ResultSet resultSet1 = statement1.executeQuery(query1);

                while(resultSet1.next())
                {
                    stuId = resultSet1.getInt(1);
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
                String query2 = "insert into Student values("+stuId+", '"+eduType+"', '"+d+"', '"+fname+"', '"+lname+"')";
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
                String query3 = "insert into Graduate values("+stuId+", "+m+", '"+subjects+"')";
                statement3.executeQuery(query3);

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

    public void insertUniDetails(Context ptr,String contact,String life,int ranking,int fee,String uname,double lati,double longi,String loc,String email,String pass)
    {
        int uniId=0;

        if(connection!=null)
        {
            Statement s1=null;
            Statement s3=null;


            Statement s2=null;
            try
            {
                s2 = connection.createStatement();
                String query = "insert into [User] values ('"+uname+"','"+email+"','"+pass+"', 0, 0, 'University')";
                s2.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

            try
            {
                s3 = connection.createStatement();
                String query1 = "select a.idUser from [User] a where a.userName = '"+uname+"'";
                ResultSet resultSet1 = s3.executeQuery(query1);

                while(resultSet1.next())
                {
                    uniId = resultSet1.getInt(1);
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

            try
            {
                s1 = connection.createStatement();
                String query = "insert into University values ('"+contact+"',"+uniId+","+ranking+",'"+life+"','"+loc+"',"+lati+","+longi+", "+fee+")";
                s1.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkUniquenessEmail(Context ptr, String email)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] a where a.email = '"+email+"'");

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

}
