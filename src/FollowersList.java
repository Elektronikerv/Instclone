import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "FollowersList", urlPatterns = "/followersList")
public class FollowersList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String following = request.getParameter("followers");
        String follower = request.getParameter("following");
        ArrayList<User> followers = null;
        try {
            User user = null;
            if(following != null) {
                user = DAO.getUser(following);
                request.setAttribute("followType", "followers:");
                followers = DAO.getFollowers(user);
            }else{
                user = DAO.getUser(follower);
                request.setAttribute("followType", "following:");
                followers = DAO.getFollowing(user);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("followers", followers);
        request.getRequestDispatcher("/followersList.jsp").forward(request, response);
    }
}
