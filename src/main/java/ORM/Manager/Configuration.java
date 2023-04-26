package ORM.Manager;
import java.io.*;
import java.sql.*;
import java.lang.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




//Configuration configures the database connection based configPath xml, create SessionFactory and creates Table
public class Configuration {
    private static final Logger logger = LogManager.getLogger(Configuration.class);
    private String configPath;
    private SessionFactory sessionFactory;

    private Config configObj;

    private String packageName;
    public Configuration(String configPath, String packageName){
        this.configPath = configPath;
        this.packageName = packageName;
        config();
    }
    private void config(){
        //TODO: configures the database connection based configPath and xml create session factory
        try {
            FileInputStream fis=new FileInputStream(this.configPath);
            Properties p=new Properties ();
            p.load(fis);
            String url= (String)p.get("URL");
            String username= (String)p.get("User");
            String password= (String)p.get("Password");
            configObj = new Config(url,username,password); //create config OBJ
            fis.close();
        }catch (Exception e){
            logger.error("Error in reading configuration file! ERROR: "+e.toString());
        }
    }



    public SessionFactory getFactory() throws Exception{
        return new SessionFactory(configObj,this.packageName);
    }
}
