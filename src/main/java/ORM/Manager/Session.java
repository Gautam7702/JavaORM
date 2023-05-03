package ORM.Manager;

import ORM.Database.DB;
import ORM.Database.DB_postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;

// Session class uses database apis to send queries.
public class Session {
    Connection con;
    DB db = new DB_postgres();

    Session(Connection con){
        this.con = con;
    }

    public <T> boolean insert(T obj){
        //TODO: save the given obj in database.
        try {
            PreparedStatement p = con.prepareStatement(db.insert(obj));
            p.execute();
            return true;
        }
        catch (Exception e){
            System.out.println("Error in inserting the object. Error = " + e);
            return false;
        }
    }

    public <T> boolean delete(T obj){
        //TODO: delete the given obj in database.
        try {
            PreparedStatement p = con.prepareStatement(db.delete(obj));
            p.execute();
            return true;
        }
        catch (Exception e) {
            System.out.println("Error in deleting object Message = "  + e.toString());
            return false;
        }

    }

    public <T> boolean update(T obj){
        //TODO: update the given obj in database.
        try {
            db.update(obj);
            PreparedStatement p = con.prepareStatement(db.update(obj));
            p.execute();
            return true;
        }
        catch (Exception e) {
            System.out.println("Error in updating object Message = "  + e);
            return false;
        }

    }

}
