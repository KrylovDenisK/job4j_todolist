package ru.job4j.todolist.service;

import ru.job4j.todolist.store.Store;
import ru.job4j.todolist.store.StoreBase;
import java.util.List;
import java.util.function.Consumer;

public class ServiceBaseImp<T> implements Service<T> {
    private final Store<T> store;

    private ServiceBaseImp(Class<T> tClass) {
        store = StoreBase.getInstance(tClass);
    }

    public static <T> Service<T> getInstance(Class<T> tClass) {
        return new ServiceBaseImp<>(tClass);
    }

    @Override
    public List<T> getAll() {
        return store.getAll();
    }

    @Override
    public void create(T entity) {
        store.create(entity);
    }

    @Override
    public void delete(Integer id) {
        T entity = store.findById(id);
        if (entity != null) {
            store.delete(entity);
        }
    }

    @Override
    public void update(Integer id, Consumer<T> command) {
        T entity = store.findById(id);
        command.accept(entity);
        store.update(entity);
    }

    @Override
    public T findByName(String name) {
        return store.findByName(name).get(0);
    }

    @Override
    public T findById(Integer id) {
        return store.findById(id);
    }
}
