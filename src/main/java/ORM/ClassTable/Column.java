package ORM.ClassTable;
//Column class is used to simulate the Columns of table in database.
public class Column {

    boolean primaryKey;
    String value;

    Column(String datatype,String value){

    }

    public void set(String value){
        this.value = value;
    }

}
