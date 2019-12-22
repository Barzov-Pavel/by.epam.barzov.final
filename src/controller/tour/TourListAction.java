package controller.tour;

import controller.*;
import domain.Tour;
import org.apache.logging.log4j.*;
import service.*;
import service.exceptions.ServiceException;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/*
 * The class finds all tours in database
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
