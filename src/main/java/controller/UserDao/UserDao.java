package controller.UserDao;

import model.User;
import org.apache.log4j.Logger;
import util.DataBaseHelper.DataBaseHelper;
import util.Executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserInterface {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class.getSimpleName());

    private final Executor executor;

    public UserDao() {
        this.executor = new Executor(DataBaseHelper.getConnect());
    }

    @Override
    public List <User> getAllUsers(){
        LOGGER.info("getting all users from database");
        List<User> users = executor.execQuery(String.format("select * from task1_db.user"), result ->{
            List <User> userList = new ArrayList<>();
            try{
                while (result.next()){
                    userList.add( new User(result.getLong("id"),
                                                result.getString("name"),
                                                result.getString("login"),
                                                result.getString("password")));
                    LOGGER.info("adding user " + userList.get(userList.size()-1).getName());
                }
            }catch (SQLException e){
                LOGGER.error("error while adding user to list" + e);
            }
            return userList;
        });
        return users;
    }

    public void deleteUserById(long id) {
        executor.execUpdate(String.format("delete from task1_db.user where id='%s'",id));
    }

    @Override
    public void addUser(String name, String login, String password) {
        executor.execUpdate(String.format("insert into task1_db.user (name, login, password) values ('%s', '%s', '%s')",
                                                                                                name,login,password));
    }

    @Override
    public boolean userExists(String login, String password) {
        int flag = executor.execQuery(String.format("select count(*) from task1_db.user where login='%s' && password='%s'",
                                                                                                login,password), result ->{
            int i = 0;
            try{
                result.next();
                i= result.getInt(1);
            }catch (SQLException e){}
            return i;
        });
        return flag!=0;
    }

    @Override
    public User getUserById(long id) {
        User user = executor.execQuery(String.format("select * from task1_db.user where id='%s'",id), result ->{
            User u = null;
            try {
                result.next();
                long userId = result.getLong("id");
                String userName = result.getString("name");
                String userLogin = result.getString("login");
                String userPass = result.getString("password");
                u = new User(userId, userName, userLogin, userPass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return u;
        });
        return user;
    }

    @Override
    public void updateUser(long id, String name, String login, String password) {
        executor.execUpdate(String.format("update task1_db.user set name='%s', login='%s', password='%s' where id='%s'",
                name, login, password, id));
    }
}
