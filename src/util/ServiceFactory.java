package util;

import java.sql.Connection;

import dao.TourDao;
import dao.UserDao;
import domain.Tour;
import service.TourService;
import service.UserService;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService() throws FactoryException;

    UserDao getUserDao() throws FactoryException;

    Connection getConnection() throws FactoryException;

    TourService getTourService() throws FactoryException;

    TourDao getTourDao() throws FactoryException;
}
