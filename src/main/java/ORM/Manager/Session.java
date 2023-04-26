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

    public <T> boolean save(T obj) throws Exception{
        //TODO: save the given obj in database.
        PreparedStatement p = con.prepareStatement(db.save(obj));
        p.execute();
        return true;
    }

    public <T> boolean delete(T obj){
        //TODO: delete the given obj in database.
        return true;
    }

    public <T> boolean update(T obj){
        //TODO: update the given obj in database.
        return true;
    }

}
