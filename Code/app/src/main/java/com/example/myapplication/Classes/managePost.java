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

public class managePost extends dbConnection{


    public void getImages(String uniname, ArrayList<String> arr, ArrayList<String> arr2)
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

    public void getTexts(String uniname, ArrayList<String> arr)
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


    public void getVideos(String uniname, ArrayList<String> arr)
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


    public void insertImage(Context ptr, String uniname, String imgbin, String description)
    {
        if(connection != null)
        {
            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;
            Statement statement3 = null;
            int uid = 0;
            int pid = 0;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.idUniversity from [User] a join University u on a.idUser = u.idUniversity where a.userName = '"+uniname+"'");

                while(resultSet.next())
                {
                    uid = resultSet.getInt(1);
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            try{
                statement2 = connection.createStatement();
                String query2 = "insert into Image values("+uid+", '"+imgbin+"', '"+description+"')";
                statement2.executeQuery(query2);
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

    public void insertVideo(Context ptr, String uniname, String link)
    {
        if(connection != null)
        {
            Statement statement = null;
            Statement statement1 = null;
            int uid = 0;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.idUniversity from [User] a join University u on a.idUser = u.idUniversity where a.userName = '"+uniname+"'");

                while(resultSet.next())
                {
                    uid = resultSet.getInt(1);
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            try{
                statement1 = connection.createStatement();
                String query2 = "insert into Video values("+uid+", '"+link+"')";
                statement1.executeQuery(query2);
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

    public void insertStatus(Context ptr, String uniname, String txt)
    {
        if(connection != null)
        {
            Statement statement = null;
            Statement statement1 = null;
            int uid = 0;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.idUniversity from [User] a join University u on a.idUser = u.idUniversity where a.userName = '"+uniname+"'");

                while(resultSet.next())
                {
                    uid = resultSet.getInt(1);
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            try{
                statement1 = connection.createStatement();
                String query2 = "insert into text values("+uid+", '"+txt+"')";
                statement1.executeQuery(query2);
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
}
