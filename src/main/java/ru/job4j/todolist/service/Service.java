package ru.job4j.todolist.service;

import ru.job4j.todolist.model.Item;

import java.util.List;

public interface Service {
    List<Item> getAll();
    void create(Item item);
    void delete(Integer id);
    void update(Integer id);
}
