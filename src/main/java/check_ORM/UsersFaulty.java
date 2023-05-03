package check_ORM;

import ORM.ClassTable.Column;
import ORM.ClassTable.PrimaryKey;
import ORM.ClassTable.Table;

import java.sql.Date;
import java.sql.Timestamp;
@Table
public class UsersFaulty {
    @PrimaryKey
    @Column
    Float ID;
    @Column
    int name;
    @Column
    Date isSpcl;
    @Column
    Timestamp dob;
    @Column
    String joining_time;
    @Column
    boolean current_balance;
}
