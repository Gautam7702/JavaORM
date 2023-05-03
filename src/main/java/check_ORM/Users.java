package check_ORM;


import ORM.ClassTable.Column;
import ORM.ClassTable.PrimaryKey;
import ORM.ClassTable.Table;

import java.sql.Date;
import java.sql.Timestamp;

@Table
public class Users{
    @Column
    @PrimaryKey
    int ID;

    @Column
    String name;
    @Column
    Boolean isSpcl;
    @Column
    @PrimaryKey
    Date dob;
    @Column
    Timestamp joining_time;
    @Column
    Float current_balance;

    int x;


    public Users(int ID, String name, Boolean isSpcl, Date dob, Timestamp joining_time, Float current_balance) {
        this.ID = ID;
        this.name = name;
        this.isSpcl = isSpcl;
        this.dob = dob;
        this.joining_time = joining_time;
        this.current_balance = current_balance;
    }
}
