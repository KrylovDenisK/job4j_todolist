package ru.job4j.todolist.store;

import ru.job4j.todolist.model.Item;

import java.util.List;

public interface Store {
    void create(Item item);
    List<Item> getAll();
    void delete(Integer id);
    void update(Integer id);
}
