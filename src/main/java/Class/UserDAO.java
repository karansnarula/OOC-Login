package Class;

import java.text.*;
import java.util.*;
import java.sql.*;

public class UserDAO
{
    static Connection currentCon = null;
    static ResultSet rs = null;



    public static UserBean login(UserBean bean) {

        //preparing some objects for connection
        Statement stmt = null;

        String username = bean.getUsername();
        String password = bean.getPassword();

        String searchQuery =
                "select * from users where username='"
                        + username
                        + "' AND password='"
                        + password
                        + "'";

        // "System.out.println" prints in the console; Normally used to trace the process
        System.out.println("Your user name is " + username);
        System.out.println("Your password is " + password);
        System.out.println("Query: "+searchQuery);

        try
        {
            //connect to DB
            currentCon = ConnectionManager.getConnection();
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user does not exist set the isValid variable to false
            if (!more)
            {
                System.out.println("Sorry, you are not a registered user! Please sign up first");
                bean.setValid(false);
            }

            //if user exists set the isValid variable to true
            else if (more)
            {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                System.out.println("Welcome " + firstName);
                bean.setFirstName(firstName);
                bean.setLastName(lastName);
                bean.setValid(true);
            }
        }

        catch (Exception ex)
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }

        //some exception handling
        finally
        {
            if (rs != null)	{
                try {
                    rs.close();
                } catch (Exception e) {}
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {}
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }

        return bean;

    }

    public static UserBean addUser(UserBean bean) {

        String firstName = bean.getFirstName();
        String lastName = bean.getLastName();
        String username = bean.getUsername();
        String password = bean.getPassword();

        try
        {
            //connect to DB
            currentCon = ConnectionManager.getConnection();

            // the mysql insert statement
            String query = " insert into users (FirstName, LastName, username, password)" + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = currentCon.prepareStatement(query);
            preparedStmt.setString (1, firstName);
            preparedStmt.setString (2, lastName);
            preparedStmt.setString   (3, username);
            preparedStmt.setString(4, password);
            // execute the preparedstatement
            preparedStmt.execute();
        }

        catch (Exception ex)
        {
            System.out.println("Add user failed: An Exception has occurred! " + ex);
        }

        //some exception handling
        finally
        {
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }
        return bean;
    }

    public static UserBean deleteUser(UserBean bean) {

        String username = bean.getUsername();

        try
        {
            //connect to DB
            currentCon = ConnectionManager.getConnection();

            // the mysql insert statement
            if(username.equals("admin") == false){
                String query = "delete from users where username = ?";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = currentCon.prepareStatement(query);
                preparedStmt.setString   (1, username);
                // execute the preparedstatement
                preparedStmt.execute();
            }
        }

        catch (Exception ex)
        {
            System.out.println("Delete user failed: An Exception has occurred! " + ex);
        }

        //some exception handling
        finally
        {
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }
        return bean;
    }

    public static UserBean editUser(UserBean bean){

        String username = bean.getUsername();
        String firstName = bean.getFirstName();
        try
        {
            //connect to DB
            currentCon = ConnectionManager.getConnection();

            String query = "update users set FirstName = ? where username = ?";
            PreparedStatement preparedStmt = currentCon.prepareStatement(query);
            preparedStmt.setString   (1, firstName);
            preparedStmt.setString(2, username);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        }

        catch (Exception ex)
        {
            System.out.println("Edit user failed: An Exception has occurred! " + ex);
        }

        //some exception handling
        finally
        {
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }
        return bean;

    }
}

