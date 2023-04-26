package ORM.Manager;

//Configuration configures the database connection based configPath xml, create SessionFactory and creates Table
public class Configuration {
    private String configPath;
    private SessionFactory sessionFactory;

    private Config configObj;

    private String packageName;
    Configuration(String configPath,String packageName){
        this.configPath = configPath;
        config();
    }
    private void config(){
        //TODO: configures the database connection based configPath and xml create session factory
    }



    public SessionFactory getFactory(){
        return new SessionFactory(configObj);
    }
}
