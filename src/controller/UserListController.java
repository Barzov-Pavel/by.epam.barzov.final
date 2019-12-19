package controller;

import java.sql.Connection;

import dao.mysql.UserDaoImpl;
import domain.User;
import service.ServiceException;
import service.logic.UserServiceImpl;
import util.Connector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserListController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            Connector.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/new_schema?useUnicode=true&characterEncoding=utf8",
                    "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            UserDaoImpl dao = new UserDaoImpl();
            dao.setConnection(connection);
            UserServiceImpl service = new UserServiceImpl();
            service.setUserDao(dao);
            List<User> users = service.findAll();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }
}
