import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "Page", urlPatterns = "/page")
public class Page extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        if(request.getParameter("upload") != null) {
            request.getRequestDispatcher("/uploadImage.jsp").forward(request, response);
        }
        else if(request.getParameter("logOut") != null) {
            request.getSession().invalidate();
            response.sendRedirect("auth.jsp");
        }
        else if(request.getParameter("search") != null) {
            String username = request.getParameter("search");
            List<User> searchedUsers = null;
            try {
                searchedUsers = DAO.search(username);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.setAttribute("search", username);
            request.setAttribute("searchedUsers", searchedUsers);
            request.getRequestDispatcher("/searchedUsers.jsp").forward(request, response);
        }
        else {
            User user = (User) request.getSession().getAttribute("user");
            user.setAvatar("images/" + user.getLogin() + "/avatar.jpg");
            List<Image> images = new ArrayList<>();
            int followersQuentity = 0;
            int followingQuentity = 0;
            try {
                images = DAO.getImages(user);
                followersQuentity = DAO.getFollowersQuentity(user);
                followingQuentity = DAO.getFollowingQuentity(user);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.setAttribute("following", followingQuentity);
            request.setAttribute("followers", followersQuentity);
            request.setAttribute("list", images);
            request.setAttribute("posts", images.size());
            request.getRequestDispatcher("/page.jsp").forward(request, response);
        }
    }
}
