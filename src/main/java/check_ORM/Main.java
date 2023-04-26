package check_ORM;
import ORM.Manager.Configuration;
import ORM.Manager.Session;
import ORM.Manager.SessionFactory;
public class Main {

    public static void main(String args[]) throws Exception{
        Configuration config = new Configuration("/home/gautam/Desktop/SoftieORM/src/main/java/check_ORM/configFile.txt","check_ORM");
        SessionFactory sF = config.getFactory();
        User u = new User(10,"Gautam");
        Session s = sF.getSession();
        s.save(u);
        s.update(u);
        s.delete(u);

    }

}
