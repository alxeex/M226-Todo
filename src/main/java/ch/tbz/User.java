package ch.tbz;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private Boolean isLoggedIn = false;
    @Getter
    @Setter
    private Boolean admin = false;

    public User(Integer id, String name, String password, boolean admin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.admin = admin;
    }



    public Boolean isLoggedIn() {
        return isLoggedIn;
    }
    public Boolean isAdmin() {
        return admin;
    }
}
