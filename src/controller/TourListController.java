package controller;

import java.sql.Connection;

import dao.mysql.TourDaoImpl;
import dao.mysql.UserDaoImpl;
import domain.Tour;
import domain.User;
import service.ServiceException;
import service.logic.TourServiceImpl;
import service.logic.UserServiceImpl;
import util.Connector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TourListController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            Connector.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/new_schema",
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
            TourDaoImpl dao = new TourDaoImpl();
            dao.setConnection(connection);
            TourServiceImpl service = new TourServiceImpl();
            service.setTourDao(dao);
            List<Tour> tours = service.findAll();
            req.setAttribute("tours", tours);
            req.getRequestDispatcher("/WEB-INF/jsp/user/tour-list.jsp").forward(req, resp);
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
