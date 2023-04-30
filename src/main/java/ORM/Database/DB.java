package ORM.Database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DB {
    <T> String save(Class<T> clazz, T object, Connection conn) throws SQLException, IllegalAccessException;
    <T> String  delete(Class<T> clazz, T object, Connection conn) throws IllegalAccessException, SQLException;
    <T> String update(Class<T> clazz, T object, Connection conn);
}
