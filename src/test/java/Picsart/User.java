package Picsart;

import java.util.Map;

public class User {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public User(Map<String, String> user) {
        this.userName = user.get("userName");
        this.password = user.get("password");
    }

}
