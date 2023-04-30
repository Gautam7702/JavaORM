package ORM.Database;
import java.lang.reflect.*;
import java.sql.*;
public class DB_postgres implements DB{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "1234";

//    public <T> String save(T obj){
//        //TODO: save the given obj in database.
//        return "";
//
//    }

    //-----------------------------------------------------------



    public <T> String save(Class<T> clazz, T object, Connection conn) throws SQLException, IllegalAccessException {
        String tableName = clazz.getSimpleName().toLowerCase();
        // Build the CREATE TABLE SQL statement using reflection
        Statement stmt= conn.createStatement();
        Field[] fields = clazz.getDeclaredFields();
        // Insert the data into the table
        StringBuilder insertSql = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder valuesSql = new StringBuilder("VALUES (");
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName().toLowerCase();
            field.setAccessible(true);
            Object value = field.get(object);
            if (value != null) {
                insertSql.append(fieldName);
                if(value instanceof String){
                    valuesSql.append("'"+(String)value+"'");
                }
                else {
                    valuesSql.append("'" + value + "'");
                }
                if (i < fields.length - 1) {
                    insertSql.append(", ");
                    valuesSql.append(", ");
                }
            }
        }
        insertSql.append(") ");
        valuesSql.append(");");
        System.out.println(insertSql.toString()+valuesSql.toString());
//        stmt.execute(insertSql.toString()+valuesSql.toString());
        System.out.println("Inserted");
        return "Inserted";
    }

    //------------------------------------------------------------
    public <T> String delete(Class<T> clazz, T object, Connection conn) throws IllegalAccessException, SQLException {

        String tableName = clazz.getSimpleName().toLowerCase();
        // Build the CREATE TABLE SQL statement using reflection
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder delete_query = new StringBuilder("DELETE FROM "+tableName+" WHERE ");
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName().toLowerCase();
            field.setAccessible(true);
            Object value = field.get(object);
            if (value != null) {
                delete_query.append(fieldName+"="+value.toString());
                if (i < fields.length - 1) {
                    delete_query.append(", ");
                }
            }
        }
        delete_query.append(";");
        Statement stmt = conn.createStatement();
        stmt.execute(delete_query.toString());
        System.out.println("Deleted");
        return "Deleted";
    }

    public <T> String update(Class<T> clazz,T obj,Connection conn){
        //TODO: update the given obj in database.
        return "";
    }
}
