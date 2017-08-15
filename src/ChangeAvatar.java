import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ChangeAvatar", urlPatterns = "/changeAvatar")
public class ChangeAvatar extends HttpServlet {
    private static final String IMAGE_DIRECTORY ="/home/elektroniker/JavaPractice/Web/InstClone/web/images/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String login = user.getLogin();

        if(ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts) {
                    if(!item.isFormField()) {
                        item.write(new File(IMAGE_DIRECTORY + login + "/" + "avatar.jpg"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/page");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
