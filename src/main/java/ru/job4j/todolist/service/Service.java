package ru.job4j.todolist.service;

import ru.job4j.todolist.model.Item;

public interface Service {
    String getAllJson();
    void create(Item item);
    void delete(Integer id);
    void update(Integer id);
}
