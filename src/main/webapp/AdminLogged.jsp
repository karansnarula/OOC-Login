<%@ page import="java.sql.*" %>

<html>

      <head>
         <meta http-equiv="Content-Type"
            content="text/html; charset=windows-1256">
         <title>   Admin Logged Successfully   </title>
      </head>

      <body>

         <form method="post" action="LogoutServlet">
            <input type="submit" value="Logout" style="float: right;">
         </form>

         <center>
                <h2>Hello Admin!</h2>
         </center>

         <form method="post" action="AddUserServlet">
               <font size="5" color="black">Create a new user</font><br><br>
               FirstName:
               <input type="text" name="FirstName"/><br><br>
               LastName:
               <input type="text" name="LastName"/><br><br>
               username:
               <input type="text" name="username"/><br><br>
               password:
               <input type="text" name="password"/><br><br>
               <input type="submit" value="Create">

         </form>

         <br />

                 <H1>The User Database Table </H1>

                 <%
                     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ooclogin","root","1234");

                     Statement statement = connection.createStatement() ;
                     ResultSet resultset =
                         statement.executeQuery("select * from users") ;
                 %>

                 <TABLE BORDER="1">
                     <TR>
                         <TH>FirstName</TH>
                         <TH>LastName</TH>
                         <TH>username</TH>
                         <TH>password</TH>
                         <TH>Remove</TH>
                     </TR>
                     <% while(resultset.next()){ %>
                     <TR>
                         <TD> <%= resultset.getString(1) %></td>
                         <TD> <%= resultset.getString(2) %></TD>
                         <TD> <%= resultset.getString(3) %></TD>
                         <TD> <%= resultset.getString(4) %></TD>
                         <TD> <form method="post" action="DeleteUserServlet">
                                 <input type="hidden" name="remove" value=<%= resultset.getString(3) %>>
                                 <input type="submit" name="remove" value="Remove" onclick="return confirm('Are you sure you want to remove this user?')">
                              </form></TD>
                     </TR>
                     <% } %>
                 </TABLE>
      </body>

   </html>