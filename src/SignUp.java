import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "SignUp", urlPatterns = "/signUp")
public class SignUp extends HttpServlet {

    private static final String IMAGE_DIRECTORY = "/home/elektroniker/JavaPractice/Web/InstClone/web/images/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = parseRequest(request, response);
        try {
            if (DAO.isFreeLogin(user.getLogin())) {
                DAO.addUser(user);
                response.sendRedirect("auth.jsp");
            } else {
                request.getRequestDispatcher("signUp.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static User parseRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User();
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multiparts) {
                    if (item.isFormField()) {
                        if(item.getFieldName().equals("login")) {
                            user.setLogin(item.getString());
                        }
                        else if(item.getFieldName().equals("password")) {
                            user.setPassword(item.getString());
                        }
                        else if(item.getFieldName().equals("email")){
                            user.setEmail(item.getString());
                        }
                    }
                }
                loadAvatar(multiparts, user);
            } catch (Exception ex) {
                response.getWriter().print("File UploadImage Failed due to " + ex);
            }
        }
        return user;
    }

    private static void loadAvatar(List<FileItem> multiparts, User user) throws Exception {
        String login = user.getLogin();
        Path dir = Paths.get(IMAGE_DIRECTORY + login);
        Files.createDirectories(dir);
        for (FileItem item : multiparts) {
            if (!item.isFormField()) {

                item.write(new File(IMAGE_DIRECTORY + login + "/avatar.jpg"));
            }
        }
    }
}