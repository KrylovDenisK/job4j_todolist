package ru.job4j.todolist.servlets;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.todolist.model.Category;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.model.User;
import ru.job4j.todolist.service.Service;
import ru.job4j.todolist.service.ServiceBaseImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet(name = "AddItemServlet", urlPatterns = "/add")
public class AddItemServlet extends HttpServlet {
    private final Service<Item> service = ServiceBaseImp.getInstance(Item.class);
    private final Service<Category> serviceCty = ServiceBaseImp.getInstance(Category.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        JSONObject reqParam = new JSONObject(
                        req.getReader()
                        .lines()
                        .collect(Collectors.joining()));
        JSONArray categories = reqParam.getJSONArray("categories");
        Item item = new Item(
                reqParam.getString("desc"),
                (User) req.getSession().getAttribute("user"));
        IntStream.range(0, categories.length())
                .forEach(i -> item.addCategory(
                        serviceCty.findById(
                                categories.getInt(i))));
        service.create(item);
        JSONObject rpItems = new JSONObject();
        rpItems.put("items", service.getAll());
        resp.getWriter().write(rpItems.toString());
    }
}
