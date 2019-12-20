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
        user.setUserName("bob");
        user.setFirstName("Боб");
        user.setLastName("Палмер");
        user.setTelephone("32146987");
        user.setDiscount(0);
        user.setPassword("root");
        user.setRole(Role.CUSTOMER.toString());
        UserDaoImpl userDao = new UserDaoImpl();
//
//        Tour tour = new Tour();
//        tour.setTitle("Russian tour");
//        tour.setDescription("Best tour");
//        tour.setType(TourType.SHOPPING.toString());
//        tour.setHot(true);
//        tour.setPrice(new BigDecimal(1500));
//        tour.setEnabled(true);
//        tour.setAvgRating(4.5);
//        tour.setVotesCount(0);
//        tour.setDiscount(5);
//        tour.setDestination("Turkey");
//        tour.setId(Long.parseLong("3"));
//
//        Purchase purchase = new Purchase();
//        purchase.setTour(tour);
//        purchase.setUser(user);
//        purchase.setDate(new Date(Calendar.getInstance().getTime().getTime()));
//        purchase.setPrice(tour.getPrice());
//        purchase.setStatus(PurchaseStatus.CANCELED.toString());
//
//        TourDaoImpl tourDao = new TourDaoImpl();
//        PurchaseDaoImpl purchaseDao = new PurchaseDaoImpl();
        Connection connection = null;


        try {
            connection = Connector.getConnection();
            userDao.setConnection(connection);
            UserServiceImpl service = new UserServiceImpl();
            service.setUserDao(userDao);
            user = userDao.readByLoginAndPassword("ded", "root");

            System.out.println(user);


        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        } /*catch (DaoException e) {
            e.printStackTrace();
        }*/
    }

}
