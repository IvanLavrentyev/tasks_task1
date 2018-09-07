package controller.UserDao;

import controller.UserDao.UserDao;
import model.User;

import java.util.List;

public class UserService implements UserInterface {

    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void addUser(String name, String login, String password) {
        userDao.addUser(name,login,password);
    }

    @Override
    public boolean userExists(String login, String password) {
        return userDao.userExists(login,password);
    }
}
