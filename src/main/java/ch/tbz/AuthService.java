package ch.tbz;

import lombok.Getter;

import java.util.ArrayList;

class AuthService {
    @Getter
    private final ArrayList<User> users = new ArrayList<User>();


    /**
     * Hardcode der User
     */
    public AuthService() {
        users.add(new User(1, "alex", "ziegler", true));
        users.add(new User(2, "julian", "kuehne", false));
        users.add(new User(3, "test", "test", false));
    }

    /**
     * Diese Methode returned ob ein User eingeloggt ist
     * @param userId Das ist die Userid vom Benutzer.
     * @return Return true wenn der User eingeloggt ist.
     */
    public boolean IsUserLoggedIn(int userId) {
        for (User user : users) {
            if (user.getId() == userId && user.isLoggedIn())
                return true;
        }
        return false;
    }

    /**
     * Diese Methode überpüft das Login.
     * @param un Hier wird der User gespeichert.
     * @param pw Hier wird das Passwort gespeichert.
     * @return Return true wenn das Passwort und der User richtig sind.
     */
    public User Login(String un, String pw) {
        for (User user : users) {
            if (user.getName().equals(un) && user.getPassword().equals(pw)) {
                user.setIsLoggedIn(true);
                return user;
            }
        }
        System.out.println("Benutzername oder Passwort falsch!");
        return null;
    }

    /**
     * User Logout
     * @param id ID des Users
     * @return Returnt true wenn der User abgemeldet ist.
     */
    public boolean Logout(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setIsLoggedIn(false);
                return true;
            }
        }
        return false;
    }

    /**
     * Getter für User ID
     * @param userId ID des Users
     * @return Returnt die User ID
     */
    public User getUserById(int userId) {
        return users.get(userId - 1);
    }
}
