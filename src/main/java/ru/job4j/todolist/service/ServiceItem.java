package ru.job4j.todolist.service;

import org.json.JSONObject;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.store.Store;
import ru.job4j.todolist.store.StoreItem;

import java.util.List;

public class ServiceItem implements Service {
    private static final Service INSTANCE = new ServiceItem();
    private final Store store = StoreItem.getInstance();

    public static Service getInstance() {
        return INSTANCE;
    }

    public String getAllJson() {
        JSONObject items = new JSONObject();
        items.put("items", getAll());
        return items.toString();
    }

    private List<Item> getAll() {
        return store.getAll();
    }

    public void create(Item item) {
        store.create(item);
    }

    public void delete(Integer id) {
        store.delete(id);
    }

    public void update(Integer id) {
        store.update(id);
    }
}