package ORM.Database;

import ORM.ClassTable.Column;

import java.lang.reflect.Field;
import java.sql.*;

public class DB_postgres implements DB{
    public <T> String insert(T obj){
        return "";
//        Class<?> cl = obj.getClass();
//        String tableName = cl.getSimpleName().toLowerCase();
//        Field[] fields = cl.getDeclaredFields();
//        // Insert the data into the table
//        StringBuilder insertSql = new StringBuilder("INSERT INTO " + tableName + " (");
//        StringBuilder valuesSql = new StringBuilder("VALUES (");
//        for (int i = 0; i < fields.length; i++) {
//            Field field = fields[i];
//            if (field.isAnnotationPresent(Column.class)) {
//                String fieldName = field.getName().toLowerCase();
//                field.setAccessible(true);
//                Object value = field.get(obj);
//                if (value != null) {
//                    String fieldType = "";
//                    if (field.isAnnotationPresent(Column.class)) {
//                        Column anno = field.getAnnotation(Column.class);
//                        if (anno.TYPE() == TYPES.INTEGER)
//                            valuesSql.append(field.getInt(obj));
//                        else if (anno.TYPE() == TYPES.STRING)
//                            valuesSql.append("'" + (String) field.get(obj) + "'");
//                        else if (anno.TYPE() == TYPES.FLOAT)
//                            valuesSql.append((Float)field.get(obj));
//                        else if (anno.TYPE() == TYPES.BOOLEAN)
//                            valuesSql.append((Boolean)field.get(obj));
//                        else if (anno.TYPE() == TYPES.DATE)
//                            valuesSql.append("'"+(Date) field.get(obj)+"'");
//                        else if (anno.TYPE() == TYPES.TIME)
//                            valuesSql.append("'"+(Timestamp) field.get(obj)+"'");
//                        insertSql.append(fieldName);
//                        if (i < fields.length - 1) {
//                            insertSql.append(", ");
//                            valuesSql.append(", ");
//                        }
//                    }
//                }
//            }
//        }
//        insertSql.append(") ");
//        valuesSql.append(");");
//        String finalQuery = insertSql.toString() + valuesSql.toString();
//        System.out.println(finalQuery);
//        return finalQuery;
    }
    public <T> String delete(T obj){
        //TODO: delete the given obj in database.
//        try {
//            Class<?> cl = obj.getClass();
//            String tableName = cl.getSimpleName().toLowerCase();
//            StringBuilder delete_query = new StringBuilder("DELETE FROM "+tableName+" WHERE ");
//            Field[] fields = cl.getDeclaredFields();
//            int len = 0;
//            for(int i=0;i< fields.length;i++){
//                Field field = fields[i];
//                if(field.isAnnotationPresent(Column.class)){
//                    if(field.getAnnotation(Column.class).PRIMARY_KEY()!=0)
//                        len= len+1;
//                }
//            }
//            for (int i = 0; i < fields.length; i++) {
//                Field field = fields[i];
//                String fieldName = field.getName().toLowerCase();
//                field.setAccessible(true);
//                Object value = field.get(obj);
//                if (field.isAnnotationPresent(Column.class)) {
//                    Column anno = field.getAnnotation(Column.class);
//                    if(anno.PRIMARY_KEY()==0)
//                        continue;
//                    delete_query.append(fieldName);
//                    if (anno.TYPE() == TYPES.INTEGER)
//                        delete_query.append("=" + field.getInt(obj));
//                    else if (anno.TYPE() == TYPES.STRING)
//                        delete_query.append( "=" + "'" + (String) field.get(obj) + "'");
//                    else if (anno.TYPE() == TYPES.FLOAT)
//                        delete_query.append("=" + (Float)field.get(obj));
//                    else if (anno.TYPE() == TYPES.BOOLEAN)
//                        delete_query.append("=" + (Boolean)field.get(obj));
//                    else if (anno.TYPE() == TYPES.DATE)
//                        delete_query.append("=" + "'"+(Date) field.get(obj)+"'");
//                    else if (anno.TYPE() == TYPES.TIME)
//                        delete_query.append("=" + "'"+(Timestamp) field.get(obj)+"'");
//                }
//                if (i < len- 1) {
//                    delete_query.append(", ");
//                }
//            }
//            System.out.println(delete_query.toString());
//            return delete_query.toString();
//
//        }
//        catch(Exception e){
//            System.out.println("Error creating query.");
//        }
        return "";
    }

    public <T> String update(T obj){
        //TODO: update the given obj in database
        try {
//            Class<?> cl = obj.getClass();
//            String tableName = cl.getSimpleName().toLowerCase();
//            StringBuilder update_query = new StringBuilder("UPDATE "+tableName+ " SET ");
//            Field[] fields = cl.getDeclaredFields();
//            for (int i = 0; i < fields.length; i++) {
//                Field field = fields[i];
//                if (field.isAnnotationPresent(Column.class)) {
//                    String fieldName = field.getName().toLowerCase();
//                    field.setAccessible(true);
//                    Object value = field.get(obj);
//                    if (value != null) {
//                        String fieldType = "";
//                        if (field.isAnnotationPresent(Column.class)) {
//                            update_query.append(fieldName+ "=");
//                            Column anno = field.getAnnotation(Column.class);
//                            if (anno.TYPE() == TYPES.INTEGER)
//                                update_query.append(field.getInt(obj));
//                            else if (anno.TYPE() == TYPES.STRING)
//                                update_query.append("'" + (String) field.get(obj) + "'");
//                            else if (anno.TYPE() == TYPES.FLOAT)
//                                update_query.append((Float)field.get(obj));
//                            else if (anno.TYPE() == TYPES.BOOLEAN)
//                                update_query.append((Boolean)field.get(obj));
//                            else if (anno.TYPE() == TYPES.DATE)
//                                update_query.append("'"+(Date) field.get(obj)+"'");
//                            else if (anno.TYPE() == TYPES.TIME)
//                                update_query.append("'"+(Timestamp) field.get(obj)+"'");
//                            if (i < fields.length - 1) {
//                                update_query.append(", ");
//                            }
//                        }
//                    }
//                }
//            }
//            update_query.append(" WHERE ");
//            int len  = 0;
//            for(int i=0;i< fields.length;i++){
//                Field field = fields[i];
//                if(field.isAnnotationPresent(Column.class)){
//                    if(field.getAnnotation(Column.class).PRIMARY_KEY()!=0)
//                        len= len+1;
//                }
//            }
//            for (int i = 0; i < fields.length; i++) {
//                Field field = fields[i];
//                String fieldName = field.getName().toLowerCase();
//                field.setAccessible(true);
//                if (field.isAnnotationPresent(Column.class)) {
//                    Column anno = field.getAnnotation(Column.class);
//                    if(anno.PRIMARY_KEY()==0)
//                        continue;
//                    update_query.append(fieldName);
//                    if (anno.TYPE() == TYPES.INTEGER)
//                        update_query.append("=" + field.getInt(obj));
//                    else if (anno.TYPE() == TYPES.STRING)
//                        update_query.append( "=" + "'" + (String) field.get(obj) + "'");
//                    else if (anno.TYPE() == TYPES.FLOAT)
//                        update_query.append("=" + (Float)field.get(obj));
//                    else if (anno.TYPE() == TYPES.BOOLEAN)
//                        update_query.append("=" + (Boolean)field.get(obj));
//                    else if (anno.TYPE() == TYPES.DATE)
//                        update_query.append("=" + "'"+(Date) field.get(obj)+"'");
//                    else if (anno.TYPE() == TYPES.TIME)
//                        update_query.append("=" + "'"+(Timestamp) field.get(obj)+"'");
//                }
//                if (i < len- 1) {
//                    update_query.append(", ");
//                }
//            }
//            System.out.println(update_query.toString());
//            return update_query.toString();

        }
        catch(Exception e){
            System.out.println("Error creating query.");
        }
        return "";
    }
}
