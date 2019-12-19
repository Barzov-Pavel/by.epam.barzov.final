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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class UserSaveController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user = new User();
        try {
            user.setId(Long.parseLong(request.getParameter("id")));
        } catch (NumberFormatException e) {
            // TO LOGGER
        }
        try {
            user.setUserName(request.getParameter("login"));
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setPassword(request.getParameter("password"));
            user.setDiscount(Integer.parseInt(request.getParameter("discount")));
            user.setTelephone(request.getParameter("phone"));
            user.setRole(request.getParameter("role"));
            PrintWriter writer = response.getWriter();
//            writer.println("<!DOCTYPE html>");
//            writer.println("<html>");
//            writer.println("<head>");
//            writer.println("<meta charset=\"UTF-8\">");
//            writer.println("</head>");
//            writer.println("<body>");
//            writer.println(request.getParameter("lastName"));
//            writer.println("/<body>");
//            writer.println("</html>");

        } catch (NumberFormatException e) {

        }
        if (user.getUserName() != null && user.getRole() != null) {
            Connection connection = null;
            try {
                connection = Connector.getConnection();
                connection.setAutoCommit(false);
                UserDaoImpl dao = new UserDaoImpl();
                dao.setConnection(connection);
                UserServiceImpl service = new UserServiceImpl();
                service.setUserDao(dao);
                service.setDefaultPassword("12345");
                service.save(user);
                connection.commit();
            } catch (SQLException | ServiceException e) {
                throw new ServletException(e);
            } finally {
                try {
                    connection.rollback();
                    connection.close();
                } catch (Exception e) {

                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/user/list.html");
    }
}
