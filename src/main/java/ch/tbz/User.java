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

    /**
     * Construktor User
     * @param id ID des Users
     * @param name Name des Users
     * @param password Passwort des Users
     * @param admin Hier wird gespeichert ob der User Admin ist.
     */
    public User(Integer id, String name, String password, boolean admin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.admin = admin;
    }


    /**
     * Hier wird geschaut ob der User eingeloggt ist.
     * @return Returnt true wenn der Benutzer eingeloggt ist.
     */
    public Boolean isLoggedIn() {
        return isLoggedIn;
    }
}
