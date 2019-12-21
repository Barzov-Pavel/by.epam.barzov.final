package controller.purchase;

import controller.Action;
import controller.Forward;
import domain.Purchase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.PurchaseService;
import service.ServiceException;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * A class displays data about purchased tours
 */

public class BoughtToursAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(BoughtToursAction.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        /*
         * Get id from request for search all purchases
         */
        try {
            id = Long.parseLong(req.getParameter("userId"));
        } catch (NumberFormatException e) {
        }

        /*
         * Test id for null
         * If id == null send redirect
         */

        if (id != null) {
            try {
                PurchaseService service = getServiceFactory().getPurchaseService();
                List<Purchase> purchases = service.getBoughtTours(id);
                req.setAttribute("purchases", purchases);
                return null;
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("Don't set of purchases " + e.getMessage());
                throw new ServletException(e);
            }
        } else {
            LOGGER.error("don't exist id redirect to index html");
            return new Forward("/index.html");
        }
    }
}
