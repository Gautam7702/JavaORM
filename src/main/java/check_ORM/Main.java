package check_ORM;
import ORM.Database.DB_postgres;
import ORM.Manager.Configuration;
import ORM.Manager.Session;
import ORM.Manager.SessionFactory;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String args[]) throws Exception{
//        Configuration config = new Configuration("/home/sanyam/ORM/JavaORM/src/main/java/check_ORM/configFile.txt","check_ORM");
//        SessionFactory sF = config.getFactory();
        Tester u = new Tester(10,"Gautam");
//        Session s = sF.getSession();
//        s.save(User.class,u);
//
////        s.update(User.class,u);
//        s.delete(User.class,u);
        Connection conn=null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "1234");
            System.out.println("Connected!");
        }catch (Exception e){
            System.out.println("Unable to Connect");
        }
        DB_postgres test=new DB_postgres();
        test.save(Tester.class,u,conn);
//        test.delete(User.class,u,conn);
    }

}
