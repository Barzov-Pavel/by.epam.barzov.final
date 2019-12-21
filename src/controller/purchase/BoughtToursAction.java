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

public class BoughtToursAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(BoughtToursAction.class);
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            LOGGER.error("try to set id");
            id = Long.parseLong(req.getParameter("userId"));
            LOGGER.error("id set");
        } catch (NumberFormatException e) {
            LOGGER.error("id didn't set");
        }

        if (id != null) {
            try {
                LOGGER.error("try to get purchase service");
                PurchaseService service = getServiceFactory().getPurchaseService();
                List<Purchase> purchases = service.getBoughtTours(id);
                LOGGER.error("try to set purchases");
                req.setAttribute("purchases", purchases);
                LOGGER.error("purchases set");
                return null;
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        } else {
            LOGGER.error("don't exist id redirect to index html");
            return new Forward("/index.html");
        }
    }
}
