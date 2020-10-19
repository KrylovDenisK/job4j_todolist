package ru.job4j.todolist.servlets;

import org.json.JSONObject;
import ru.job4j.todolist.model.Role;
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
import java.util.stream.Collectors;


@WebServlet(name = "RegServlet", urlPatterns = "/reg")
public class RegServlet extends HttpServlet {
    private Service<User> serUser = ServiceBaseImp.getInstance(User.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject regParam = new JSONObject(
                        req.getReader()
                        .lines()
                        .collect(Collectors.joining()));
        String login = regParam.getString("login");
        String email = regParam.getString("email");
        String password = regParam.getString("password");
        Role role = ServiceBaseImp.getInstance(Role.class).findByName("user");
        User user = User.of(login, email, password, role);
        serUser.create(user);
        HttpSession sc = req.getSession();
        sc.setAttribute("user", user);
        resp.getWriter().write(new JSONObject().put("user", user.getName()).toString());
    }
}
