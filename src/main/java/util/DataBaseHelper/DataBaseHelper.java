package util.DataBaseHelper;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHelper {

    static Connection connection = null;

    public static Connection getConnect() {

        try{
            if (connection == null){
                Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();

                DriverManager.registerDriver(driver);

                StringBuilder url = new StringBuilder();

                url.
                        append("jdbc:mysql://").
                        append("localhost:").
                        append("3306/").
                        append("task1_db?").
                        append("user=root&").
                        append("password=root");
                connection = (Connection) DriverManager.getConnection(url.toString());
            }
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
