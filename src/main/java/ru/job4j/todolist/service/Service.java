package ru.job4j.todolist.service;

import java.util.List;
import java.util.function.Consumer;

public interface Service<T> {
    List<T> getAll();
    void create(T entity);
    void delete(Integer id);
    void update(Integer id, Consumer<T> command);
    T findByName(String name);
    T findById(Integer id);
}
