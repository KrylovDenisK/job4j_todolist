package ru.job4j.todolist.servlets;

import org.json.JSONObject;
import ru.job4j.todolist.service.Service;
import ru.job4j.todolist.service.ServiceItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "GetItemsServlet", urlPatterns = "/get")
public class GetItemsServlet extends HttpServlet {
    private final Service service = ServiceItem.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        JSONObject rpItems = new JSONObject();
        rpItems.put("items", service.getAll());
        resp.getWriter().write(rpItems.toString());
    }
}
