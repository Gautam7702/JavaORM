package ORM.Manager;

import ORM.Database.DB;
import ORM.Database.DB_postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
//    public<T> List<T> getAll(Class<T> cl){
//        String tableName = cl.getName().toLowerCase();
//        List<T> queryResult = new ArrayList<>();
//        String query = "SELECT * FROM " +tableName;
//        try{
//            PreparedStatement p = con.prepareStatement(db.update(obj));
//            p.execute();
//        }catch (Exception e){
//            System.out.println("Error in getting all objects. Message  = "+e.toString());
//        }
//
//
//        return queryResult;
//    }

    public <T> boolean doesExists(T obj){
        //TODO: update the given obj in database.
        try {
            Statement p = con.createStatement();
            String query=db.doesExist(obj);
            ResultSet rs=p.executeQuery(query);
            String val="";
            while(rs.next())
            {
                val=rs.getString(1);
            }
            if(val.equals("0")){return false;}
            return true;
        }
        catch (Exception e) {
            System.out.println("Error in updating object Message = "  + e);
            return false;
        }
    }

}
