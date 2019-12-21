package controller.purchase;

import controller.*;
import domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.*;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

/*
 * A class implements tour purchase functionality
 * A class Purchase contains objects of classes Tour and User
 */

public class TourBuyAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(TourBuyAction.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tourId = null;
        Long userId = null;
        BigDecimal price = null;
        try {
            tourId = Long.parseLong(req.getParameter("tourId"));
            userId = Long.parseLong(req.getParameter("userId"));
            price = new BigDecimal(req.getParameter("price"));
        } catch (NumberFormatException e) {
        }

        if (tourId != null && userId != null && price != null) {
            try {
                PurchaseService purchaseService = getServiceFactory().getPurchaseService();
                TourService tourService = getServiceFactory().getTourService();
                UserService userService = getServiceFactory().getUserService();
                // create Tour and User objects for creating Purchase object
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
                LOGGER.error("Don't create Purchase " + e.getMessage());
                throw new ServletException(e);
            }
        }
        return null;
    }
}
