package ORM.Manager;

import ORM.Database.DB;
import ORM.Database.DB_postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Session class uses database apis to send queries.
public class Session {
    Connection con;
    DB db = new DB_postgres();

    Session(Connection con){
        this.con = con;
    }

    public <T> boolean save(Class<T> clazz, T obj) throws Exception{
        //TODO: save the given obj in database.
        PreparedStatement p = con.prepareStatement(db.save(clazz,obj,con));
        p.execute();
        return true;
    }

    public <T> boolean delete(Class<T> clazz, T obj) throws Exception {
        //TODO: delete the given obj in database.
        PreparedStatement p = con.prepareStatement(db.delete(clazz,obj,con));
        p.execute();
        return true;
    }

    public <T> boolean update(Class<T> clazz, T obj){
        //TODO: update the given obj in database.
        return true;
    }

}
