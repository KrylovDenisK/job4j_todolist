package ru.job4j.todolist.servlets;

import org.json.JSONObject;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.service.Service;
import ru.job4j.todolist.service.ServiceBaseImp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "UpdateServlet", urlPatterns = "/upd")
public class UpdateServlet extends HttpServlet {
    private final Service<Item> service = ServiceBaseImp.getInstance(Item.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject reqParam = new JSONObject(req.getReader().lines().collect(Collectors.joining()));
        service.update(
                reqParam.getInt("id"),
                (item -> {
                    boolean value = item.getDone();
                    item.setDone(!value);
                }));
    }
}
