package controller;

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
import java.sql.Connection;
import java.sql.SQLException;

public class UserSaveController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        try {
            user.setId(Long.parseLong(request.getParameter("id")));
            user.setRole(request.getParameter("role"));
        } catch (NumberFormatException e) {
            // TO LOGGER
        }
        if (user.getUserName() != null && user.getRole() != null) {
            Connection connection = null;
            try {
                connection = Connector.getConnection();
                UserDaoImpl dao = new UserDaoImpl();
                dao.setConnection(connection);
                UserServiceImpl service = new UserServiceImpl();
                service.setUserDao(dao);
                service.setDefaultPassword("12345");
                service.save(user);
            } catch (SQLException | ServiceException e) {
                throw new ServletException(e);
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {

                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/user/list.html");
    }
}
