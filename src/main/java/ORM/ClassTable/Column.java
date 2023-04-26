package ORM.ClassTable;
//Column class is used to simulate the Columns of table in database.
public class Column<T> {

    boolean primaryKey=false;
    T value;

    TYPES TYPE;


    public Column(TYPES TYPE, T value){
        this.TYPE = TYPE;
        this.value = value;
    }
    Column(TYPES TYPE,T value,boolean primaryKey){
        this.TYPE = TYPE;
        this.value = value;
        this.primaryKey = primaryKey;
    }

    public void set(T value){
        this.value = value;
    }

}
