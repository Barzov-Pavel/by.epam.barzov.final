import controller.UserSaveController;
import dao.Dao;
import dao.DaoException;
import dao.mysql.PurchaseDaoImpl;
import dao.mysql.TourDaoImpl;
import dao.mysql.UserDaoImpl;
import domain.*;
import service.ServiceException;
import service.logic.UserServiceImpl;
import util.Connector;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Connector connector = new Connector();

        TestInitializator.init();
        User user = new User();
        user.setUserName("ded");
        user.setFirstName("Lox");
        user.setLastName("Barzov");
        user.setTelephone("123456780");
        user.setDiscount(0);
        user.setPassword("root");
        user.setRole(Role.TOUR_AGENT.toString());
        user.setId(Long.parseLong("1"));
        UserDaoImpl userDao = new UserDaoImpl();

        Tour tour = new Tour();
        tour.setTitle("Russian tour");
        tour.setDescription("Best tour");
        tour.setType(TourType.SHOPPING.toString());
        tour.setHot(true);
        tour.setPrice(new BigDecimal(1500));
        tour.setEnabled(true);
        tour.setAvgRating(4.5);
        tour.setVotesCount(0);
        tour.setDiscount(5);
        tour.setDestination("Turkey");
        tour.setId(Long.parseLong("3"));

        Purchase purchase = new Purchase();
        purchase.setTour(tour);
        purchase.setUser(user);
        purchase.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        purchase.setPrice(tour.getPrice());
        purchase.setStatus(PurchaseStatus.CANCELED.toString());

        TourDaoImpl tourDao = new TourDaoImpl();
        PurchaseDaoImpl purchaseDao = new PurchaseDaoImpl();
        Connection connection = null;


        try {
            connection = Connector.getConnection();
            purchaseDao.setConnection(connection);

            purchase.setId(purchaseDao.create(purchase));
            System.out.println(purchaseDao.readAll());

        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        } /*catch (DaoException e) {
            e.printStackTrace();
        }*/
    }

}
