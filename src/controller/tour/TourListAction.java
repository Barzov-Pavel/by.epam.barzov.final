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
import java.util.List;

/*
 * A class find all tours in database
 */

public class TourListAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(TourListAction.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TourService service = getServiceFactory().getTourService();
            List<Tour> tours = service.findAll();
            req.setAttribute("tours", tours);
            return null;
        } catch (FactoryException | ServiceException e) {
            LOGGER.error("Don't set List of tours " + e.getMessage());
            throw new ServletException(e);
        }
    }
}
