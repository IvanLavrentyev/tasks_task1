package util.Executor;

import java.sql.ResultSet;

@FunctionalInterface
public interface ExecHelper <T> {
     T help(ResultSet resultSet);
}
