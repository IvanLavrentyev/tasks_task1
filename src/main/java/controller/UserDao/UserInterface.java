package controller.UserDao;

import model.User;

import java.util.List;

public interface UserInterface {
    List<User> getAllUsers();
    void deleteUserById(long id);
    void addUser(String name, String login, String password);
    boolean userExists(String login, String password);
    User getUserById(long id);
    void updateUser(long id, String name, String login, String password);
}
