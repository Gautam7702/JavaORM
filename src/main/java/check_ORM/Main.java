package check_ORM;
import ORM.Manager.Configuration;
import ORM.Manager.Session;
import ORM.Manager.SessionFactory;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Main {

    public static void main(String args[]) throws Exception{
        Configuration config = new Configuration("/home/gautam/Desktop/SoftieORM/src/main/java/check_ORM/configFile.txt","check_ORM",true);
        SessionFactory sF = config.getFactory();
        Users u = new Users(10,"Gautam",false,new Date(622790105000L),  new Time(System.currentTimeMillis()),50.5f);
        Users u2 = new Users(11,"Gautam",false,new Date(622790105000L),  new Time(System.currentTimeMillis()),50.5f);
        Session s = sF.getSession();
        s.insert(u);
        s.insert(u2);
        u.current_balance = 10.0f;
        u.name = "hello";
        s.update(u);
        System.out.println(s.getAll(Users.class));
////        //TODO: CHECK FOR NULL
//        s.delete(u);
    }

}
