package ru.job4j.todolist.store;

import java.util.List;

public interface Store<T> {
    void create(T item);
    List<T> getAll();
    void delete(T entity);
    void update(T entity);
    T findById(Integer id);
    List<T> findByName(String name);
}
