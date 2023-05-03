package ORM.Manager;

import ORM.ClassTable.PrimaryKey;
import ORM.ClassTable.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Annotation;
import ORM.ClassTable.Column;

public class SessionFactory {
        private static final Logger logger = LogManager.getLogger(SessionFactory.class);
        Config config;
        private String packageName;
        boolean reset = false;

        SessionFactory(Config configObj,String packageName) throws  Exception{
            this.config = configObj;
            this.packageName = packageName;
            createTable();
        }
        SessionFactory(Config configObj,String packageName,boolean reset) throws  Exception{
            this.config = configObj;
            this.packageName = packageName;
            if(reset==true){
                reset();
            }
            createTable();
        }

    private static List<Class<?>> findAnnotatedClasses(String packageName)
            throws Exception {
            // this function is used to find all the classes which are annotated with  @Table
        List<Class<?>> annotatedClasses = new ArrayList<>();
        for (Class<?> clazz : getClasses(packageName)) {
            if (!clazz.isAnnotation() && Modifier.isPublic(clazz.getModifiers())) {
                for (Annotation annotation : clazz.getAnnotations()) {
                    if (annotation.annotationType().equals(Table.class)) {
                        annotatedClasses.add(clazz);
                        break;
                    }
                }
            }
        }
        return annotatedClasses;
    }

    private static List<Class<?>> getClasses(String packageName) throws Exception {
            // Use this function to get all the classes present in the package.
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = SessionFactory.class.getClassLoader();
        }
        for (java.net.URL resource : java.util.Collections.list(classLoader.getResources(path))) {
            for (File file : new File(resource.toURI()).listFiles()) {
                String fileName = file.getName();
                if (fileName.endsWith(".class")) {
                    String className = packageName + "." + fileName.substring(0, fileName.length() - 6);
                    classes.add(Class.forName(className));
                }
            }
        }
        return classes;
    }
        public <T> void createClassTable(Class<T> cl,Connection con) throws SQLException {
            String tableName = cl.getSimpleName().toLowerCase();
            StringBuilder createTableSql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + "(");
            //TODO: if same table name exists but format different then remove it and add this.
            //TODO: the class should have atleast one primary key.
            boolean typeError = false;
            Field[] fields = cl.getDeclaredFields();
            boolean hasPrimaryKey = false;
            int len = 0;
            int primLen  =0;
            for(int i=0;i<fields.length;i++){
                if(fields[i].isAnnotationPresent(Column.class)) {
                    len = len + 1;
                    if(fields[i].isAnnotationPresent(PrimaryKey.class))
                        primLen = primLen+1;
                }
            }
            String pk = "PRIMARY KEY (";
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName().toLowerCase();
                String fieldType = "";
                boolean isPrimaryKey = false;
                if(field.isAnnotationPresent(Column.class)) {
                    Column anno = field.getAnnotation(Column.class);
                    if (field.getType() == int.class)
                        fieldType = "INT";
                    else if (field.getType() == String.class)
                        fieldType = "VARCHAR";
                    else if (field.getType() == Float.class || field.getType() == float.class)
                        fieldType = "FLOAT";
                    else if (field.getType() == Boolean.class || field.getType() == boolean.class)
                        fieldType = "BOOLEAN";
                    else if (field.getType() == Date.class)
                        fieldType = "date";
                    else if (field.getType() == Time.class)
                        fieldType = "TIME";
                    else {
                        fieldType = "VARCHAR";
                        System.out.println("Attribute type mentioned does not match in " + tableName + " for attribute " + fieldName + ". Converting it to as String.");
                    }
                    createTableSql.append(fieldName).append(" ").append(fieldType);
                    createTableSql.append(", ");
                }
            }
            int count=0;
            for(int i=0;i< fields.length;i++){
                if(fields[i].isAnnotationPresent(Column.class) && fields[i].isAnnotationPresent(PrimaryKey.class)){
                    pk = pk  + fields[i].getName().toLowerCase();
                    if(count< primLen-1){
                        pk+= ",";
                    }
                    count = count+1;
                }
            }
            pk+= ")";
            if(primLen!=0) {
                createTableSql.append(pk+")");
                System.out.println(createTableSql);
                Statement stmt = con.createStatement();
                stmt.executeUpdate(createTableSql.toString());
            }else{
                System.out.println("There should be atleast one primary key in the table " + tableName);
                throw new SQLException("There should be atleast one primary key in the table " + tableName);
            }
        }

        public void createTable() throws Exception{
            Connection con;
            //TODO: create table
            try {
                con = DriverManager.getConnection(config.url, config.username, config.password);
                logger.info("Connection established");
                List<Class<?>> tableClass =findAnnotatedClasses(packageName);
                for(Class<?> c: tableClass){
                    createClassTable(c,con);
                }
            }catch (Exception e){
                logger.error("Error connecting to database. Message = " + e.toString());
            }

        }
        public Session getSession() throws Exception{
            //TODO: creates a new thread,create a session object on that and returns session object.
            Connection con = DriverManager.getConnection(config.url, config.username, config.password);
            Session s = new Session(con);
            return s;
        }
        public void reset() throws Exception {
            List<Class<?>> all=findAnnotatedClasses(packageName);
            Connection conn;
            try {
                conn = DriverManager.getConnection(config.url, config.username, config.password);
                for(Class<?> clazz : findAnnotatedClasses(packageName))
                {
                    String tableName = clazz.getSimpleName().toLowerCase();
                    StringBuilder delete_sql=new StringBuilder("DROP TABLE IF EXISTS "+tableName);
                    Statement stmt = conn.createStatement();
                    System.out.println(delete_sql.toString());
                    stmt.executeUpdate(delete_sql.toString());
                }
            }catch (Exception e){
                logger.error("Error connecting to database. Message = " + e.toString());
            }
        }
}
