package Class;

import java.sql.*;
import java.util.*;


public class ConnectionManager {

    static Connection con;
//    static String url;

    public static Connection getConnection()
    {

//        try
//        {
//            String url = "jdbc:odbc:" + "login";
//            // assuming "DataSource" is your DataSource name
//
//            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            try
            {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ooclogin","root","1234");

                // assuming your SQL Server's	username is "username"
                // and password is "password"

            }

            catch (SQLException ex)
            {
                System.out.println("SQL Exception");
                ex.printStackTrace();
            }
//        }

//        catch(ClassNotFoundException e)
//        {
//            System.out.println("ClassNotFound Exception");
//            System.out.println(e);
//        }

        return con;
    }
}
