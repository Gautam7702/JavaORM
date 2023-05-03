package check_ORM;
import ORM.Manager.Configuration;
import ORM.Manager.Session;
import ORM.Manager.SessionFactory;

import java.sql.Date;
import java.sql.Timestamp;

public class Main {

    public static void main(String args[]) throws Exception{
        Configuration config = new Configuration("/home/sanyam/ORM/JavaORM/src/main/java/check_ORM/configFile.txt","check_ORM",true);
        SessionFactory sF = config.getFactory();
        Users u = new Users(10,"Gautam",false,new Date(622790105000L),Timestamp.valueOf("2018-09-01 09:01:15"),50.5f);
        Session s = sF.getSession();
        s.insert(u);
//        u.current_balance = 10.0f;
//        u.name = "hello";
//        s.update(u);
//        System.out.println(s.getAll(Users.class));
////        //TODO: CHECK FOR NULL
//        s.delete(u);
        Users u2=new Users(11,"Shubham",false,new Date(622790105000L),Timestamp.valueOf("2018-09-01 09:01:15"),50.5f);
        if(s.doesExists(u2)){
            System.out.println("True");
        }
        else{
            System.out.println("False");
        }
    }

}
