package controller.tour;

import controller.Action;
import controller.Forward;
import domain.Tour;
import service.ServiceException;
import service.TourService;
import service.UserService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TourListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TourService service = getServiceFactory().getTourService();
            List<Tour> tours = service.findAll();
            req.setAttribute("tours", tours);
            return null;
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
