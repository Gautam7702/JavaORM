package ORM.Database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DB {
    <T> String insert(T obj);
    <T> String  delete(T obj);
    <T> String update(T obj);
}
