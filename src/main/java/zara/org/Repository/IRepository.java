package zara.org.Repository;

import java.util.List;

public interface IRepository<T> {
    List<T> GetAll();
    T GetById(int id);
    void Add(T entity);
    void Update(T entity);
    void Remove(int id);
}
