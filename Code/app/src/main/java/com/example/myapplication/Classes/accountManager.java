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
import java.util.Objects;

public class accountManager extends dbConnection{

    public void getFeedback(ArrayList<String> arr)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select feedback from Feedback");

                while(resultSet.next())
                {
                    arr.add(resultSet.getString(1));
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


    public void connectToDb(Context ptr) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
            Toast.makeText(ptr,"Successfully Connected to Database", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(ptr, "Unable to Connect to Database. (Check ip , username & password).", Toast.LENGTH_SHORT).show();
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
        connection = null;
    }

    public boolean isConnectionOpen() {
        if (connection == null)
            return false;
        return true;
    }

    public boolean viewAccountList(Context ptr, ArrayList<User> uList) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // Classes is string with value "net.sourceforge.jtds.jdbc.Driver"
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
            //Toast.makeText(ptr,"Successful Connection", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (connection != null) {
            // Statement statement = null;
            try {
                CallableStatement callableStatement = connection.prepareCall("{call getALLUsers()}");
                ResultSet result = callableStatement.executeQuery();

                while (result.next()) {

                    if (Objects.equals(result.getString("type"), "Student")) {
                        Student st = new Student(
                                result.getInt("idUser"),// uid
                                result.getString("userName"), // name
                                result.getString("email"), // email
//                                "17/02/2001", //DOB
//                                result.getString("firstName"),// fname
//                                result.getString("latName"), // lname
                                result.getInt("isDisabled") //Disabled
                        );
                        uList.add(st);
                    } else if (Objects.equals(result.getString("type"), "University")) {
                        University un = new University(
                                result.getInt("idUser"),// uid
                                result.getString("userName"), // name
                                result.getString("email"), // email
//                                    result.getString("phone"),// phone
//                                    result.getString("location"),
                                result.getInt("isDisabled")
                        );
                        uList.add(un);
                    }

                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                //Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public boolean viewAccount(Context ptr, int uid, TextView _uid, TextView _uname, TextView _fname, TextView _lname, TextView _email, TextView _disabled, String type){
        connectToDb(ptr);

        if (connection != null) {
            //Statement statement = null;

            try {
                // For Procedure with input parameters
                CallableStatement callableStatement = connection.prepareCall("{call getUser(?)}");
                //passing parameters
                callableStatement.setInt("uid",uid);

                //try {
                if (/*type.equals("Student")*/true) {
                    ResultSet result = callableStatement.executeQuery();
                    while (result.next()) {
                        _email.setText(result.getString("email"));
                        //_fname.setText(result.getString("firstName"));
                        //_lname.setText(result.getString("lastName"));
                        _uid.setText(result.getString("idUser"));
                        _uname.setText(result.getString("userName"));

                        if (result.getInt("isDisabled") == 1) {
                            _disabled.setText("DISABLED");
                        } else {
                            _disabled.setText("ENABLED");
                        }
                    }
                }
//                    } else {
//                        ResultSet result = callableStatement.executeQuery();
//                        //_email.setText(result.getString("email"));
//                        //_fname.setText(result.getString("location"));
//                        //_lname.setText(result.getString("phone"));
//
//                        _uid.setText(result.getString("idUser"));
//                        _uname.setText(result.getString("userName"));
//                        //Log.d("UniGrab", result.getString("userName"));
//                        if (result.getInt("isDisabled") == 1) {
//                            _disabled.setText("DISABLED");
//                        } else {
//                            _disabled.setText("ENABLED");
//                        }
//                    }
//                }
//                catch (SQLException e) {
//
//                }
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
                //Log.d("UniGrab",e.getMessage());
            }

            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return true;
        }
        return false;
    }

    public boolean disableAccount(Context ptr, int uid) {
        connectToDb(ptr);
        if(connection != null){
            //Statement statement = null;
            try {
                CallableStatement callableStatement = connection.prepareCall("{call disable_Account(?)}");
                callableStatement.setInt("uid",uid);
                ResultSet result = callableStatement.executeQuery();

                while (result.next()) {
                    if(result.getInt("result") == 1){
                        Toast.makeText(ptr,"Account Disabled Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ptr,"Account Already Disabled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
                return false;
            }

            try {
                connection.close();
                connection = null;
            }
            catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        Toast.makeText(ptr, "Unable to Connect to Database. (Check ip , username & password).", Toast.LENGTH_SHORT).show();
        return false;
    }

    public boolean enableAccount(Context ptr,int uid) {
        connectToDb(ptr);
        if(connection != null){
            //Statement statement = null;
            try {
                CallableStatement callableStatement = connection.prepareCall("{call enable_Account(?)}");
                callableStatement.setInt("uid",uid);
                ResultSet result = callableStatement.executeQuery();

                while (result.next()) {
                    if(result.getInt("result") == 1){
                        Toast.makeText(ptr,"Account Enabled Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ptr,"Account Already Enabled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
                return false;
            }

            try {
                connection.close();
                connection = null;
            }
            catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        Toast.makeText(ptr, "Unable to Connect to Database. (Check ip , username & password).", Toast.LENGTH_SHORT).show();
        return false;
    }

    public boolean deleteAccount(Context ptr, int uid) {
        connectToDb(ptr);
        if(connection != null){
            //Statement statement = null;
            try {
                CallableStatement callableStatement = connection.prepareCall("{call delete_Account(?)}");
                callableStatement.setInt("uid",uid);
                ResultSet result = callableStatement.executeQuery();

                while (result.next()) {
                    if(result.getInt("result") == 1){
                        Toast.makeText(ptr,"Account Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ptr,"Unable to Delete Account", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
                return false;
            }

            try {
                connection.close();
                connection = null;
            }
            catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        Toast.makeText(ptr, "Unable to Connect to Database. (Check ip , username & password).", Toast.LENGTH_SHORT).show();
        return false;
    }
}
