package check_ORM;


import ORM.ClassTable.Column;
import ORM.ClassTable.TYPES;
import ORM.ClassTable.Table;

@Table
public class User {
    Column ID;
    Column name;

    User(int ID,String name){
        this.ID = new Column(TYPES.INTEGER,ID);
        this.name = new Column(TYPES.STRING,name);
    }

}
