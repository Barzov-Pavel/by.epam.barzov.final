package controller.tour;

import controller.*;
import domain.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.*;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/*
 * A class find tour by id and check the tour for the ability to remove
 */

public class TourEditAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(TourEditAction.class);

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
                LOGGER.error("Don't set tour " + e.getMessage());
                throw new ServletException(e);
            }
        }
        return null;
    }
}
