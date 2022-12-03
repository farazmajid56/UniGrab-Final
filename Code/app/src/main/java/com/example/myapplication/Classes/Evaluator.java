package com.example.myapplication.Classes;

import static com.example.myapplication.MainActivity.Classes;

import android.content.Context;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Evaluator extends dbConnection{
    /*
    private static String ip = "10.0.2.2";       //ALWAYS CHANGE IP TO CURRENT WIFI
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "UniGrab";

    private Connection connection = null;*/

    public ArrayList<String> getFilteredUnisUG(String degree, Context ptr, String subjectCombo, int marks) {
        ArrayList<String> arr = new ArrayList<String>();
        if (connection != null) {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select us.userName from [User] us join University u on u.idUniversity = us.idUser join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment join UndergraduateProgram up on p.idProgram = up.idUGProgram join ugReqBG b on up.idUGProgram = b.bgid where b.name = '" + subjectCombo + "' and p.name = '" + degree + "' and " + marks + " >= up.minMarks");
                while (resultSet.next()) {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(ptr, String.valueOf(arr.size()), Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ptr, "Connection is null", Toast.LENGTH_SHORT).show();
        }


        return arr;
    }


    public ArrayList<String> getFilteredUnisG(String degree, Context ptr, String subjectCombo, Float marks) {
        ArrayList<String> arr = new ArrayList<String>();
        if (connection != null) {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select us.userName from [User] us join University u on u.idUniversity = us.idUser join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment join GraduateProgram gp on p.idProgram = gp.idGProgram join gReqBG b on gp.idGProgram = b.bgid where b.name = '" + subjectCombo + "' and p.name = '" + degree + "' and " + marks + " >= gp.minCGPA");
                while (resultSet.next()) {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ptr, "Connection is null", Toast.LENGTH_SHORT).show();
        }


        return arr;
    }


    public void fillBSlist(List<String> arr, String uname) {
        if (connection != null) {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select p.name from [User] a join University u on u.idUniversity = a.idUser join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment join UndergraduateProgram up on p.idProgram = up.idUGProgram where a.userName = '" + uname + "'");
                while (resultSet.next()) {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //    Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void fillMSlist(List<String> arr, String uname) {
        if (connection != null) {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select p.name from [User] a join University u on u.idUniversity = a.idUser join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment join GraduateProgram gp on p.idProgram = gp.idGProgram where a.userName = '" + uname + "'");
                while (resultSet.next()) {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


    public void fillAllBSlist(List<String> arr) {
        if (connection != null) {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select distinct p.name from Program p join UndergraduateProgram up on p.idProgram = up.idUGProgram");
                while (resultSet.next()) {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //    Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void fillAllMSlist(List<String> arr) {
        if (connection != null) {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select distinct p.name from Program p join GraduateProgram gp on p.idProgram = gp.idGProgram");
                while (resultSet.next()) {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean getStatusBS(String degree, Context ptr, String subjectCombo, int marks, String uniname) {
        if (connection != null) {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] us join University u on u.idUniversity = us.idUser join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment join UndergraduateProgram up on p.idProgram = up.idUGProgram join ugReqBG b on up.idUGProgram = b.bgid where b.name = '" + subjectCombo + "' and p.name = '" + degree + "' and " + marks + " >= up.minMarks and  us.userName = '" + uniname + "'");
                while (resultSet.next()) {
                    if (resultSet.getString(1).equals("1")) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ptr, "Connection is null", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean getStatusMS(String degree, Context ptr, String subjectCombo, float marks, String uniname) {
        if (connection != null) {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] us join University u on u.idUniversity = us.idUser join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment join GraduateProgram up on p.idProgram = up.idGProgram join gReqBG b on up.idGProgram = b.bgid where b.name = '" + subjectCombo + "' and p.name = '" + degree + "' and " + marks + " >= up.minCGPA and us.userName = '" + uniname + "'");
                while (resultSet.next()) {
                    if (resultSet.getString(1).equals("1")) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ptr, "Connection is null", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void fillAllPrograms(List<String> arr, Context ptr) {
        if (connection != null) {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select distinct p.name from Program p");
                while (resultSet.next()) {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ptr, "Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


}
