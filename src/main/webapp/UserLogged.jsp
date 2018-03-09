   <%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="java.sql.*"
   %>

<html>

      <head>
         <meta http-equiv="Content-Type"
            content="text/html; charset=windows-1256">
         <title>   User Logged Successfully   </title>
      </head>

      <body>

         <form method="post" action="LogoutServlet">
            <input type="submit" value="Logout" style="float: right;">
         </form>

         <center>
            <h2>Hello User!</h2>
         </center>

      		<form method="post" action="EditUserServlet">

      		    <font size="5" color="black">Edit User</font><br><br>

      			Enter the username
      			<input type="text" name="username"/><br><br>

      			Enter the new FirstName
      			<input type="text" name="FirstName"/><br><br>

      			<input type="submit" value="Edit">

      		</form>


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
                              </TR>
                              <% while(resultset.next()){ %>
                              <TR>
                                  <TD> <%= resultset.getString(1) %></td>
                                  <TD> <%= resultset.getString(2) %></TD>
                                  <TD> <%= resultset.getString(3) %></TD>
                              </TR>
                              <% } %>
                          </TABLE>

      </body>

   </html>