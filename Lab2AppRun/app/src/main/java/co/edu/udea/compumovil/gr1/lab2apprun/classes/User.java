package co.edu.udea.compumovil.gr1.lab2apprun.classes;

/**
 * Created by felipe on 21/03/16.
 */
public class User {
    private String imagePath;
    private String userName;
    private String password;
    private String email;

    public User(String userName, String email, String password, String imagePath) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.imagePath = imagePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
