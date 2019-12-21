package controller.purchase;

import controller.Action;
import controller.Forward;
import controller.user.UserDeleteAction;
import dao.DaoException;
import domain.Purchase;
import domain.PurchaseStatus;
import domain.Tour;
import domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.PurchaseService;
import service.ServiceException;
import service.TourService;
import service.UserService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

public class TourBuyAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(TourBuyAction.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long tourId = null;
        Long userId = null;
        BigDecimal price = null;
        try {
            LOGGER.error("try parse tourId");
            tourId = Long.parseLong(req.getParameter("tourId"));
            LOGGER.error("set tourId");
            userId = Long.parseLong(req.getParameter("userId"));
            LOGGER.error("set userId");
            price = new BigDecimal(req.getParameter("price"));
            LOGGER.error("set price");
        } catch (NumberFormatException e) {
            LOGGER.error("don't parse data");
        }
        if (tourId != null && userId != null && price != null) {
            try {
                PurchaseService purchaseService = getServiceFactory().getPurchaseService();
                TourService tourService = getServiceFactory().getTourService();
                UserService userService = getServiceFactory().getUserService();
                Tour tour = tourService.findById(tourId);
                User user = userService.findById(userId);
                Purchase purchase = new Purchase();
                purchase.setTour(tour);
                purchase.setUser(user);
                purchase.setPrice(price);
                purchase.setDate(new Date((new java.util.Date()).getTime()));
                purchase.setStatus(PurchaseStatus.ACTIVE.toString());
                purchaseService.buyTour(purchase);
            } catch (ServiceException | FactoryException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
