package controller.UserDao;

import model.User;

import java.util.List;

public interface UserInterface {
    public List<User> getAllUsers();
    public void deleteUserById(long id);
    public void addUser(String name, String login, String password);
    public boolean userExists(String login, String password);
}
