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
import java.math.BigDecimal;

/*
 * The class saves information about tour in database
 */

public class TourSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(TourSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Tour tour = new Tour();
        try {
            tour.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
        }
        try {
            tour.setTitle(req.getParameter("title"));
            tour.setDescription(req.getParameter("description"));
            tour.setType(req.getParameter("type"));
            tour.setPrice(new BigDecimal(req.getParameter("price")));
            tour.setEnabled(Boolean.parseBoolean(req.getParameter("enabled")));
            tour.setAvgRating(Double.parseDouble(req.getParameter("rating")));
            tour.setDestination(req.getParameter("destination"));
            tour.setHot(Boolean.parseBoolean(req.getParameter("hot")));
        } catch (NumberFormatException e) {
        }
        if (tour.getTitle() != null && tour.getDescription() != null) {
            try {
                TourService service = getServiceFactory().getTourService();
                service.save(tour);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("Don't save tour in database " + e.getMessage());
                throw new ServletException(e);
            }
        }
        return new Forward("/user/tour-list.html");
    }
}
