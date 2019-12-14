package controller;

import dao.mysql.UserDaoImpl;
import domain.Role;
import domain.User;
import service.ServiceException;
import service.logic.UserServiceImpl;
import util.Connector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            // TO DO LOGGER
        }
        if (id != null) {
            Connection connection = null;
            try {
                connection = Connector.getConnection();
                UserDaoImpl userDao = new UserDaoImpl();
                userDao.setConnection(connection);
                UserServiceImpl service = new UserServiceImpl();
                service.setUserDao(userDao);
                User user = service.findById(id);
                request.setAttribute("user", user);
            } catch (SQLException | ServiceException e) {
                throw new ServletException(e);
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {

                }
            }
        }
        request.setAttribute("roles", Role.values());
        request.getRequestDispatcher("WEB-INF/jsp/user/edit.jsp").forward(request, response);
    }
}
