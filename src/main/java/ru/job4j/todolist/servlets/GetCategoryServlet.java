package ru.job4j.todolist.servlets;

import org.json.JSONObject;
import ru.job4j.todolist.model.Category;
import ru.job4j.todolist.service.Service;
import ru.job4j.todolist.service.ServiceBaseImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetCategoryServlet", urlPatterns = "/category")
public class GetCategoryServlet extends HttpServlet {
    private final Service<Category> service = ServiceBaseImp.getInstance(Category.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        JSONObject rpCategory = new JSONObject();
        rpCategory.put("categories", service.getAll());
        resp.getWriter().write(rpCategory.toString());
    }
}
