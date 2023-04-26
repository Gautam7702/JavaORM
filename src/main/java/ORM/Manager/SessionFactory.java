package ORM.Manager;

import ORM.ClassTable.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Annotation;

public class SessionFactory {
        private static final Logger logger = LogManager.getLogger(SessionFactory.class);
        Config config;
        private String packageName;

        SessionFactory(Config configObj,String packageName) throws  Exception{
            this.config = configObj;
            this.packageName = packageName;
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
        private <T> void createClassTable(Class<T> cl){
            //TODO: SANYAM
        }

        private void createTable() throws Exception{
            Connection con;
            //TODO: create table
            try {
                con = DriverManager.getConnection(config.url, config.username, config.password);
                logger.info("Connection established");
                PreparedStatement p = con.prepareStatement("CREATE TABLE DEPARTMENT(\n" +
                        "   ID INT PRIMARY KEY      NOT NULL,\n" +
                        "   DEPT           CHAR(50) NOT NULL,\n" +
                        "   EMP_ID         INT      NOT NULL\n" +
                        ");\n");
                p.execute();
            }catch (Exception e){
                logger.error("Error connecting to database. Message = " + e.toString());
            }
            List<Class<?>> tableClass =findAnnotatedClasses(packageName);
            for(Class<?> c: tableClass){
                createClassTable(c);
            }
        }
        public Session getSession() throws Exception{
            //TODO: creates a new thread,create a session object on that and returns session object.
            Connection con = DriverManager.getConnection(config.url, config.username, config.password);
            Session s = new Session(con);
            return s;
        }
}
