import dao.Dao;
import dao.DaoException;
import dao.mysql.UserDaoImpl;
import domain.Role;
import domain.User;
import service.ServiceException;
import service.logic.UserServiceImpl;
import util.Connector;

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
        user.setRole(Role.CUSTOMER.toString());
        UserDaoImpl userDao = new UserDaoImpl();
        Connection connection = null;


        try {
            connection = Connector.getConnection();
            userDao.setConnection(connection);
            UserServiceImpl userService = new UserServiceImpl();
            userService.setUserDao(userDao);
            System.out.println(userService.findAll());
            user.setId(Long.valueOf(2));

            System.out.println(userService.findById(Long.parseLong("2")));

        } catch (SQLException | ServiceException e) {
            e.printStackTrace();
        } /*catch (DaoException e) {
            e.printStackTrace();
        }*/
    }

}
