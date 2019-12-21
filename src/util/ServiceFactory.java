package util;

import java.sql.Connection;

import dao.PurchaseDao;
import dao.TourDao;
import dao.UserDao;
import service.PurchaseService;
import service.TourService;
import service.UserService;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService() throws FactoryException;

    UserDao getUserDao() throws FactoryException;

    Connection getConnection() throws FactoryException;

    TourService getTourService() throws FactoryException;

    TourDao getTourDao() throws FactoryException;

    PurchaseDao getPurchaseDao() throws FactoryException;

    PurchaseService getPurchaseService() throws FactoryException;
}
