package pl.edu.wszib.store.paints.database;

import pl.edu.wszib.store.paints.model.User;

public class UserDB {
    private User[] users = new User[2];
    private static final UserDB instance = new UserDB();

    private UserDB() {
        this.users[0] = new User("admin",
                "0133b400150530550fa65944da6edcd3", User.Role.ADMIN);
        this.users[1] = new User("user",
                "f6ce65abb67736636d7a63cd22e10dcf", User.Role.USER);
    }

    public User findByLogin(String login) {
        for(User user : this.users) {
            if(user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        User[] newUsers = new User[this.users.length + 1];
        for(int i = 0; i < this.users.length; i++) {
            newUsers[i] = this.users[i];
        }
        newUsers[newUsers.length - 1] = user;
        this.users = newUsers;
    }

    public String checkRoleToAdmin(String login) {
        if (this.findByLogin(login) != null) {
            if (this.findByLogin(login).getRole() == User.Role.ADMIN) {
                return "1";
            }
            this.findByLogin(login).setRole(User.Role.ADMIN);
            return "0";
        }
        return "2";
    }



    public static UserDB getInstance() {
        return instance;
    }
}
