package ORM.Database;

import ORM.ClassTable.Column;
import ORM.ClassTable.PrimaryKey;

import java.lang.reflect.Field;
import java.sql.*;

public class DB_postgres implements DB{
    public <T> String insert(T obj) throws IllegalAccessException {
        Class<?> cl = obj.getClass();
        String tableName = cl.getSimpleName().toLowerCase();
        Field[] fields = cl.getDeclaredFields();
        // Insert the data into the table
        StringBuilder insertSql = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder valuesSql = new StringBuilder("VALUES (");
        int len = 0;

        for(int i=0;i<fields.length;i++){
            if(fields[i].isAnnotationPresent(Column.class))
                len = len+1;
        }
        int count=0;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.isAnnotationPresent(Column.class)) {
                String fieldName = field.getName().toLowerCase();
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null) {
                    String fieldType = "";
                    if (field.isAnnotationPresent(Column.class)) {
                        Column anno = field.getAnnotation(Column.class);
                        if (field.getType() == int.class)
                            valuesSql.append(field.getInt(obj));
                        else if (field.getType() == String.class)
                            valuesSql.append("'" + (String) field.get(obj) + "'");
                        else if (field.getType() == Float.class)
                            valuesSql.append((Float)field.get(obj));
                        else if (field.getType() == Boolean.class || field.getType() == boolean.class)
                            valuesSql.append((Boolean)field.get(obj));
                        else if (field.getType() == Date.class)
                            valuesSql.append("'"+(Date) field.get(obj)+"'");
                        else if (field.getType() == Time.class || field.getType() == Timestamp.class)
                            valuesSql.append("'"+(Timestamp) field.get(obj)+"'");
                        else{
                            valuesSql.append("'"+field.get(obj).toString()+"'");
                        }
                        insertSql.append(fieldName);
                        if (count < len - 1) {
                            insertSql.append(", ");
                            valuesSql.append(", ");
                        }
                    }
                }
                count=count+1;
            }
        }
        insertSql.append(") ");
        valuesSql.append(");");
        String finalQuery = insertSql.toString() + valuesSql.toString();
        System.out.println(finalQuery);
        return finalQuery;
    }
    public <T> String delete(T obj) throws Exception {
        //TODO: delete the given obj in database.
        try {
            Class<?> cl = obj.getClass();
            String tableName = cl.getSimpleName().toLowerCase();
            StringBuilder delete_query = new StringBuilder("DELETE FROM "+tableName+" WHERE ");
            Field[] fields = cl.getDeclaredFields();
            int primLen = 0;
            for(int i=0;i< fields.length;i++){
                Field field = fields[i];
                if(field.isAnnotationPresent(Column.class)){
                    if(field.isAnnotationPresent(PrimaryKey.class))
                        primLen= primLen+1;
                }
            }
            int count=0;
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName().toLowerCase();
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class)) {
                    Column anno = field.getAnnotation(Column.class);
                    if(!field.isAnnotationPresent(PrimaryKey.class))
                        continue;
                    delete_query.append(fieldName);
                    if (field.getType() == int.class)
                        delete_query.append("=" + field.getInt(obj));
                    else if (field.getType() == String.class)
                        delete_query.append( "=" + "'" + (String) field.get(obj) + "'");
                    else if (field.getType() == Float.class)
                        delete_query.append("=" + (Float)field.get(obj));
                    else if (field.getType() == Boolean.class || field.getType() == boolean.class)
                        delete_query.append("=" + (Boolean)field.get(obj));
                    else if (field.getType() == Date.class)
                        delete_query.append("=" + "'"+(Date) field.get(obj)+"'");
                    else if (field.getType() == Time.class || field.getType() == Timestamp.class)
                        delete_query.append("=" + "'"+(Timestamp) field.get(obj)+"'");
                    else{
                        delete_query.append("=" + "'"+(String) field.get(obj).toString()+"'");
                    }
                }
                if (count < primLen- 1) {
                    delete_query.append(" AND ");
                }
                count = count+1;
            }
            System.out.println(delete_query.toString());
            return delete_query.toString();
        }
        catch(Exception e){
            throw new Exception("Error creating delete query. Error = " +e);
        }
    }

    public <T> String update(T obj) throws Exception {
        //TODO: update the given obj in database
        try {
            Class<?> cl = obj.getClass();
            String tableName = cl.getSimpleName().toLowerCase();
            StringBuilder update_query = new StringBuilder("UPDATE "+tableName+ " SET ");
            Field[] fields = cl.getDeclaredFields();
            int primLen = 0;
            int len =0;
            for(int i=0;i< fields.length;i++){
                Field field = fields[i];
                if(field.isAnnotationPresent(Column.class)){
                    if(field.isAnnotationPresent(PrimaryKey.class))
                        primLen= primLen+1;
                    len = len+1;
                }
            }
            int count=0;
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.isAnnotationPresent(Column.class)) {
                    String fieldName = field.getName().toLowerCase();
                    field.setAccessible(true);
                    update_query.append(fieldName+ "=");
                    if (field.getType() == int.class)
                        update_query.append(field.getInt(obj));
                    else if (field.getType() == String.class)
                        update_query.append("'" + (String) field.get(obj) + "'");
                    else if (field.getType() == Float.class)
                        update_query.append((Float)field.get(obj));
                    else if (field.getType() == Boolean.class || field.getType() == boolean.class)
                        update_query.append((Boolean)field.get(obj));
                    else if (field.getType() == Date.class)
                        update_query.append("'"+(Date) field.get(obj)+"'");
                    else if (field.getType() == Time.class || field.getType() == Timestamp.class)
                        update_query.append("'"+(Timestamp) field.get(obj)+"'");
                    else{
                        update_query.append("'" + (String)field.get(obj).toString()+"'");
                    }
                    if (count < len - 1) {
                        update_query.append(", ");
                    }
                    count=count+1;
                }
            }
            update_query.append(" WHERE ");
            count=0;
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName().toLowerCase();
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class)) {
                    if (!field.isAnnotationPresent(PrimaryKey.class))
                        continue;
                    update_query.append(fieldName);
                    if (field.getType() == int.class)
                        update_query.append("=" + field.getInt(obj));
                    else if (field.getType() == String.class)
                        update_query.append("=" + "'" + (String) field.get(obj) + "'");
                    else if (field.getType() == Float.class)
                        update_query.append("=" + (Float) field.get(obj));
                    else if (field.getType() == Boolean.class || field.getType() == boolean.class)
                        update_query.append("=" + (Boolean) field.get(obj));
                    else if (field.getType() == Date.class)
                        update_query.append("=" + "'" + (Date) field.get(obj) + "'");
                    else if (field.getType() == Time.class || field.getType() == Timestamp.class)
                        update_query.append("=" + "'" + (Timestamp) field.get(obj) + "'");
                    else
                        update_query.append("=" + "'" + (String) field.get(obj).toString() + "'");
                    if (count < primLen - 1) {
                        update_query.append(" AND ");
                    }
                    count = count+1;
                }
            }
            System.out.println(update_query.toString());
            return update_query.toString();
        }
        catch(Exception e){
           throw new Exception("Error creating update query. Message = "+e);
        }
    }
    public <T> String doesExist(T obj) {
        //TODO: T obj's primaryKEY exists.
        return "";
    }
}
