package ORM.Manager;

import ORM.Database.DB;
import ORM.Database.DB_postgres;

import java.sql.Connection;
// Session class uses database apis to send queries.
public class Session {
    Connection con;
    DB db = new DB_postgres();

    Session(Connection con){
        this.con = con;
    }

    <T> boolean save(T obj){
        //TODO: save the given obj in database.
        return true;
    }

    <T> boolean delete(T obj){
        //TODO: delete the given obj in database.
        return true;
    }

    <T> boolean update(T obj){
        //TODO: update the given obj in database.
        return true;
    }

}
