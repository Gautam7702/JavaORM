package ORM.Database;

public interface DB {
    <T> String save(T obj);
    <T> String  delete(T obj);
    <T> String update(T obj);
}
