package check_ORM;
import ORM.Manager.Configuration;
import ORM.Manager.Session;
import ORM.Manager.SessionFactory;

import java.sql.Date;
import java.sql.Timestamp;

public class Main {

    public static void main(String args[]) throws Exception{
        Configuration config = new Configuration("/home/gautam/Desktop/SoftieORM/src/main/java/check_ORM/configFile.txt","check_ORM");
        SessionFactory sF = config.getFactory();
        Users u = new Users(10,"Gautam",false,new Date(622790105000L),Timestamp.valueOf("2018-09-01 09:01:15"),50.5f);
        Session s = sF.getSession();
//        s.save(u);
//        u.name = "HElo";
//        s.update(u);
//        s.delete(u);
    }

}
