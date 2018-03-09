package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Class.UserBean;
import Class.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try
        {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if(username.equals("admin") && password.equals("1234")){
                response.sendRedirect("AdminLogged.jsp"); //logged-in page
            }else{

                UserBean user = new UserBean();
                user.setUserName(request.getParameter("username"));
                user.setPassword(request.getParameter("password"));

                user = UserDAO.login(user);

                if (user.isValid())
                {

                    HttpSession session = request.getSession(true);
                    session.setAttribute("currentSessionUser",user);
                    response.sendRedirect("UserLogged.jsp"); //logged-in page
                }

                else
                    response.sendRedirect("InvalidLogin.jsp"); //error page
            }
        }


        catch (Throwable theException)
        {
            System.out.println(theException);
        }
    }
}
