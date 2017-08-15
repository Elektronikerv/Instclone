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
import java.util.List;


@WebServlet(name = "UploadImage", urlPatterns = "/uploadImage")
public class UploadImage extends HttpServlet {

    private static final String IMAGE_DIRECTORY =""; //full path to the image directory

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        String login = user.getLogin();
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        item.write(new File(IMAGE_DIRECTORY + login + "/" + name));
                        Image image = new Image("images/" + login + "/" + name);
                        image.setDescription(getDescription(multiparts));
                        DAO.addImage(user, image);
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

    public static String getDescription(List<FileItem> multiparts) {
        for (FileItem item : multiparts) {
            if (item.isFormField()) {
                if (item.getFieldName().equals("description")) {
                    return item.getString();
                }
            }
        }
        return null;
    }
}
