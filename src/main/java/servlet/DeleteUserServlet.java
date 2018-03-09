package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Class.UserBean;
import Class.UserDAO;


@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try{

            UserBean user = new UserBean();
            user.setUserName(request.getParameter("remove"));
            UserDAO.deleteUser(user);
            response.sendRedirect("AdminLogged.jsp");

        }
        catch (Throwable theException){
            System.out.println(theException);
        }

    }
}
