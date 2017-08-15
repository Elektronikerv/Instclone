import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "UserPage", urlPatterns = "/userPage")
public class UserPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String username = request.getParameter("visitedUser");
        String name = user.getLogin();
        User visitedUser = new User();
        int followersQuentity = 0;
        int followingQuentity = 0;
        List<Image> images = null;
        if(name.equals(username)){
            response.sendRedirect("/page");
        }
        else{
            try {
             visitedUser = DAO.getUser(username);
             visitedUser.setAvatar("images/" + username + "/avatar.jpg");
             images = DAO.getImages(visitedUser);
             boolean isFollower = DAO.isFollower(user, visitedUser);
             if(isFollower){
                 request.setAttribute("followMode", "Unfollow");
             }
             else {
                 request.setAttribute("followMode", "Follow");
             }
             if(request.getParameter("followMode")!= null) {
                 if(request.getParameter("followMode").equals("Follow")) {
                     DAO.addFollower(user, visitedUser);
                     request.setAttribute("followMode", "Unfollow");
                 }
                 else{
                     DAO.removeFollower(user, visitedUser);
                     request.setAttribute("followMode", "Follow");
                 }
             }
             followersQuentity = DAO.getFollowersQuentity(visitedUser);
             followingQuentity = DAO.getFollowingQuentity(visitedUser);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.setAttribute("following", followingQuentity);
            request.setAttribute("followers", followersQuentity);
            request.setAttribute("posts", images.size());
            request.setAttribute("visitedUser", visitedUser);
            request.setAttribute("list", images);
            request.getRequestDispatcher("/visitedUserPage.jsp").forward(request, response);
        }
    }
}
