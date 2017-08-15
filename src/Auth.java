import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Auth", urlPatterns = "/auth")
public class Auth extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = DAO.isValid(login, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(user != null) {
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/page").forward(request, response);
        }
        else{
           response.sendRedirect("/auth.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
