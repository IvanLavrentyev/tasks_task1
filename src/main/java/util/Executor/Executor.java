package util.Executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public int execUpdate(String update){
        int numOfUpdatedLines = 0;
        try{
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            numOfUpdatedLines = statement.executeUpdate(update);
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            }catch (SQLException ignored) {}
            finally {
                try{
                    connection.setAutoCommit(true);
                } catch (SQLException ignored) {}
            }
        }
        return numOfUpdatedLines;
    }

    public <T> T execQuery(String query, ExecHelper <T> helper){
        T result = null;

        try{
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            result = helper.help(resultSet);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {}
            finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ignored) {}
            }
        }
        return result;
    }

}
