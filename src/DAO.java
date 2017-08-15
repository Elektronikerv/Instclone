import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private static String USER = "root";
    private static String URL = "jdbc:mysql://localhost:3306/Inst";
    private static String PASSWORD = "root";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static List<User> getAll() throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Users");
        ResultSet rs = ps.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"));
            users.add(user);
        }
        return users;
    }

    public static boolean isFreeLogin(String login) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Users WHERE login=?");
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        return !rs.next();
    }

    public static void addUser(User user) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("INSERT INTO Users VALUES (id, ?, ?,?)");
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getEmail());
        ps.executeUpdate();
    }

    public static User isValid(String login, String password) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Users WHERE login=? AND password=?");
        ps.setString(1, login);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        User user = null;
        while (rs.next()) {
            user = new User(rs.getInt("id"), rs.getString("login"),
                    rs.getString("password"), rs.getString("email"));
        }
        return user;
    }

    public static void addImage(User user ,Image image) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("INSERT INTO Images VALUES(?,?,?)");
        ps.setInt(1, user.getId());
        ps.setString(2, image.getPath());
        ps.setString(3,image.getDescription());
        ps.executeUpdate();
    }

    public static void deleteImage(User user, Image image) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("DELETE FROM Images WHERE usersId=? AND path=?");
        ps.setInt(1, user.getId());
        ps.setString(2, image.getPath());
        ps.executeUpdate();
    }

    public static List<Image> getImages(User user) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT path, description FROM Images WHERE usersId=?");
        ps.setInt(1, user.getId());
        ResultSet rs = ps.executeQuery();
        List<Image> images = new ArrayList<>();
        while(rs.next()) {
            Image image = new Image(rs.getString("path"));
            image.setDescription(rs.getString("description"));
            images.add(image);
        }
        return images;
    }

    public static User getUser(String login) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT  id ,login FROM Users WHERE login=?");
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while(rs.next()){
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
        }
        return user;
    }

    public static User getUser(int id) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT  * FROM Users WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while(rs.next()){
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setEmail(rs.getString("email"));
        }
        return user;
    }

    public static List<User> search(String login) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Users WHERE login LIKE '%" + login + "%'");

        ResultSet rs = ps.executeQuery();
        List<User> list = new ArrayList<>();
        while(rs.next()) {
            User user = new User(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString("email"));
            user.setAvatar();
            list.add(user);
        }
        return list;
    }

    public static void addFollower(User follower, User following) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("INSERT INTO Followers VALUES(?, ?)");
        ps.setInt(1, follower.getId());
        ps.setInt(2, following.getId());
        ps.executeUpdate();
    }

    public  static void removeFollower(User follower, User following) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("DELETE FROM Followers WHERE followerId=? AND followingId=? ");
        ps.setInt(1, follower.getId());
        ps.setInt(2, following.getId());
        ps.executeUpdate();
    }

    public static boolean isFollower(User follower, User following) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Followers WHERE followerId=? AND followingId=? ");
        ps.setInt(1, follower.getId());
        ps.setInt(2, following.getId());
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static ArrayList<User> getFollowers(User user) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT followerId FROM Followers WHERE followingId=?");
        ps.setInt(1, user.getId());
        ResultSet rs = ps.executeQuery();
        ArrayList<User> followers= new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("followerId");
            if(id != 0) {
                User follower = getUser(id);
                follower.setAvatar();
                followers.add(follower);
            }
        }
        return followers;
    }

    public static ArrayList<User> getFollowing(User user) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT followingId FROM Followers WHERE followerId=?");
        ps.setInt(1, user.getId());
        ResultSet rs = ps.executeQuery();
        ArrayList<User> followingList= new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("followingId");
            if(id != 0) {
                User following= getUser(id);
                following.setAvatar();
                followingList.add(following);
            }
        }

        return followingList;
    }

    public static int getFollowersQuentity(User user) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT COUNT(followerId) AS count FROM Followers WHERE followingId=?");
        ps.setInt(1, user.getId());
        ResultSet rs = ps.executeQuery();
        int count=0;
        while(rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }

    public static int getFollowingQuentity(User user) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT COUNT(followingId) AS count FROM Followers WHERE followerId=?");
        ps.setInt(1, user.getId());
        ResultSet rs = ps.executeQuery();
        int count=0;
        while(rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }
}

