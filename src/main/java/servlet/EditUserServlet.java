package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Class.UserBean;
import Class.UserDAO;

@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try{

            UserBean user = new UserBean();
            if (request.getParameter("FirstName").equals("") == false){
                user.setFirstName(request.getParameter("FirstName"));
                user.setUserName(request.getParameter("username"));
                if(user.getUsername().equals("admin") == false){
                    UserDAO.editUser(user);
                }
            }
            response.sendRedirect("UserLogged.jsp");
        }
        catch (Throwable theException){
            System.out.println(theException);
        }
    }
}
