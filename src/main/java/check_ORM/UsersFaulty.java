package check_ORM;

import ORM.ClassTable.Column;
import ORM.ClassTable.PrimaryKey;
import ORM.ClassTable.Table;
import ORM.Manager.Config;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
@Table
public class UsersFaulty {
    int u =0;
    @Column
    Float ID;
    @Column
    @PrimaryKey
    int name;
    @Column
    Date isSpcl;
    @Column
    Timestamp dob;
    @Column
    String joining_time;
    @Column

    boolean current_balance;
    @Column
    Config conf;
    int x=19;
}
