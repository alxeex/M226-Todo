package ch.tbz;

import lombok.Getter;

import java.util.ArrayList;

class AuthService {
    @Getter
    private final ArrayList<User> users = new ArrayList<User>();


    public AuthService() {
        users.add(new User(1, "alex", "ziegler", true));
        users.add(new User(2, "julian", "kuehne", false));
        users.add(new User(3, "test", "test", false));
    }

    public boolean IsUserLoggedIn(int userId) {
        for (User user : users) {
            if (user.getId() == userId && user.isLoggedIn())
                return true;
        }
        return false;
    }

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

    public boolean Logout(String un) {
        for (User user : users) {
            if (user.getName() == un) {
                user.setIsLoggedIn(false);
                return true;
            }
        }
        return false;
    }

    public boolean Logout(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setIsLoggedIn(false);
                return true;
            }
        }
        return false;
    }

    public User getUserById(int userId) {
        return users.get(userId - 1);
    }
}
