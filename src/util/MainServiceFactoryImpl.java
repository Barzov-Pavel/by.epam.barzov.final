package util;

import dao.PurchaseDao;
import dao.TourDao;
import dao.UserDao;
import dao.mysql.PurchaseDaoImpl;
import dao.mysql.TourDaoImpl;
import dao.mysql.UserDaoImpl;
import service.PurchaseService;
import service.TourService;
import service.UserService;
import service.logic.PurchaseServiceImpl;
import service.logic.TourServiceImpl;
import service.logic.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class MainServiceFactoryImpl implements ServiceFactory {
    private Connection connection;

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setDefaultPassword("12345");
        userService.setUserDao(getUserDao());
        return userService;
    }

    @Override
    public UserDao getUserDao() throws FactoryException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(getConnection());
        return userDao;
    }

    @Override
    public TourService getTourService() throws FactoryException {
        TourServiceImpl tourService = new TourServiceImpl();
        tourService.setTourDao(getTourDao());
        return tourService;
    }

    @Override
    public TourDao getTourDao() throws FactoryException {
        TourDaoImpl tourDao = new TourDaoImpl();
        tourDao.setConnection(getConnection());
        return tourDao;
    }

    @Override
    public PurchaseDao getPurchaseDao() throws FactoryException {
        PurchaseDaoImpl purchaseDao = new PurchaseDaoImpl();
        purchaseDao.setConnection(getConnection());
        return purchaseDao;
    }

    @Override
    public PurchaseService getPurchaseService() throws FactoryException {
        PurchaseServiceImpl purchaseService = new PurchaseServiceImpl();
        purchaseService.setPurchaseDao(getPurchaseDao());
        return purchaseService;
    }

    @Override
    public Connection getConnection() throws FactoryException {
        if (connection == null) {
            try {
                connection = Connector.getConnection();
            } catch (SQLException e) {
                throw new FactoryException(e);
            }
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {

        }
    }
}
