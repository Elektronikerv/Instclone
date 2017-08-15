import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;


@WebServlet(name = "ImagePage", urlPatterns = "/imagePage")
public class ImagePage extends HttpServlet {

    private static final String IMAGE_DIRECTORY ="" ; //full path to the image directory

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        Image image = new Image(path);
        image.setDescription(request.getParameter("description"));
        User user = (User)request.getSession().getAttribute("user");
        if(request.getParameter("delete") != null) {
            deleteImage(path);
            try {
                DAO.deleteImage(user, image);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            response.sendRedirect("/page");
        }
        else {
            request.setAttribute("image", image);
            request.getRequestDispatcher("imagePage.jsp").forward(request, response);
        }
    }

    private static void deleteImage(String path) throws IOException {
        Path dir = Paths.get(IMAGE_DIRECTORY + path);
        Files.delete(dir);

    }
}
