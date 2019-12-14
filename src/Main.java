import dao.Dao;
import dao.DaoException;
import dao.mysql.UserDaoImpl;
import domain.Role;
import domain.User;
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
            System.out.println(userDao.read(Long.valueOf(2)));
            user.setId(Long.valueOf(2));
            userDao.update(user);
            System.out.println(userDao.read(Long.valueOf(2)));

        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        } /*catch (DaoException e) {
            e.printStackTrace();
        }*/
    }

}
