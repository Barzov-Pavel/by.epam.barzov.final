package controller;

import dao.mysql.TourDaoImpl;
import domain.Tour;
import service.ServiceException;
import service.logic.TourServiceImpl;
import util.Connector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TourEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            Connection connection = null;
            try {
                connection = Connector.getConnection();
                TourDaoImpl dao = new TourDaoImpl();
                dao.setConnection(connection);
                TourServiceImpl service = new TourServiceImpl();
                service.setTourDao(dao);
                Tour tour = service.findById(id);
                req.setAttribute("tour", tour);
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
        req.getRequestDispatcher("/WEB-INF/jsp/user/tour-edit.jsp").forward(req, resp);
    }
}
