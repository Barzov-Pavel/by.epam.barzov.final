package controller.tour;

import controller.Action;
import controller.Forward;
import domain.Tour;
import service.ServiceException;
import service.TourService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TourEditAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try {
                TourService service = getServiceFactory().getTourService();
                Tour tour = service.findById(id);
                req.setAttribute("tour", tour);
                boolean tourCanBeDeleted = service.canDelete(id);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
