package ru.job4j.todolist.servlets;

import org.json.JSONObject;
import ru.job4j.todolist.model.User;
import ru.job4j.todolist.service.Service;
import ru.job4j.todolist.service.ServiceBaseImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.stream.Collectors;

@WebServlet(name = "AuthServlet", urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {
    private final Service<User> service = ServiceBaseImp.getInstance(User.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sc = req.getSession();
        sc.removeAttribute("user");
        resp.sendRedirect("auth/auth.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject auth = new JSONObject(
                req.getReader()
                        .lines()
                        .collect(Collectors.joining()));
        String login = auth.getString("login");
        String password = auth.getString("password");
        User user = service.findByName(login);
        if (user != null) {
            Writer wr = resp.getWriter();
            if (user.getPassword().equals(password)) {
                HttpSession sc = req.getSession();
                sc.setAttribute("user", user);
                wr.write(new JSONObject().put("user", user.getName()).toString());
            }
        }

    }
}
