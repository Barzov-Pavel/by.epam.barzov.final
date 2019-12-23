package controller.tour;

import controller.*;
import org.apache.logging.log4j.*;
import service.*;
import service.exceptions.ServiceException;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/*
 * The class implements functionality for delete tour
 * We can delete a tour that has never been bought
 */

public class TourDeleteAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(TourDeleteAction.class);

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
                service.delete(id);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("Don't delete a tour " + e.getMessage());
                throw new ServletException(e);
            }
        }
        return new Forward("/tour/tour-list.html");
    }
}
