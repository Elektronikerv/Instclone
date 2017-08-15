
public class User {
    private int id;
    private String password;
    private String login;
    private String avatar;
    private String email;

    public User(int id, String login, String password, String email) {
        this.id = id;
        this.password = password;
        this.login = login;
    this.email = email;
}

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getEmail() {
    return email;
}

    public void setEmail(String email) {
    this.email = email;
}

    public User() {

}

    public String getAvatar() {
    return avatar;
}

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public void setAvatar() {
        this.avatar = "images/" + login + "/avatar.jpg";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
