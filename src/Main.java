import dao.Dao;
import dao.DaoException;
import dao.mysql.TourDaoImpl;
import dao.mysql.UserDaoImpl;
import domain.Role;
import domain.Tour;
import domain.TourType;
import domain.User;
import service.ServiceException;
import service.logic.UserServiceImpl;
import util.Connector;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

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
        UserDaoImpl userDao = new UserDaoImpl();

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
//
        TourDaoImpl tourDao = new TourDaoImpl();
        Connection connection = null;

        try {
            connection = Connector.getConnection();
            userDao.setConnection(connection);


            System.out.println(userDao.readAll());
            tourDao.setConnection(connection);
            System.out.println(tourDao.readAll());
           // userDao.create(user);
            //System.out.println(userDao.read(Long.parseLong("2")));


            //System.out.println(userService.findById(Long.parseLong("2")));

        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        } /*catch (DaoException e) {
            e.printStackTrace();
        }*/
    }

}
