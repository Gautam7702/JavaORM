package ORM.Manager;

public class SessionFactory {
        Config config;
        SessionFactory(Config configObj){
            this.config = configObj;
            createTable();


        }
        private void createTable(){
            //TODO: create table
        }
        Session getSession(){
            //TODO: creates a new thread,create a session object on that and returns session object.
            Session s = new Session(null);
            return s;
        }
}
