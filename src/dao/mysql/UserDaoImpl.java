package dao.mysql;

import dao.*;
import domain.User;
import org.apache.logging.log4j.*;

import java.sql.*;
import java.util.*;

/*
 * The class works with user table in database.
 * Can create, update, read by id, read all, delete tours.
 * Find tours by login, login and password.
 * Check the users who bought the tour
 */

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public Long create(User user) throws DaoException {
        String sql = "INSERT INTO `user` (`username`, `firstName`, " +
                "`lastName`, `password`, `discount`, `telephone`, `role`)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getDiscount());
            statement.setString(6, user.getTelephone());
            statement.setString(7, user.getRole());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            LOGGER.error("Don't create user in database " + e.getMessage() + e.getSQLState());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public User read(Long id) throws DaoException {
        String sql = "SELECT * FROM `user` WHERE id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setUserName(resultSet.getString("username"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setDiscount(resultSet.getInt("discount"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRole(resultSet.getString("role"));
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error("Don't read user by id from database " + e.getMessage() + e.getSQLState());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<User> readAll() throws DaoException {
        String sql = "SELECT * FROM `user`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("username"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setDiscount(resultSet.getInt("discount"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOGGER.error("Don't read all tuples from table user " + e.getMessage() + e.getSQLState());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }


    @Override
    public void update(User user) throws DaoException {
        String sql = "UPDATE `user` SET `username`=?, `firstName`=?," +
                " `lastName`=?, `password`=?, `discount`=?, `telephone`=?, `role`=? WHERE `id`=?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getDiscount());
            statement.setString(6, user.getTelephone());
            statement.setString(7, user.getRole());
            statement.setLong(8, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Don't update user tuple in database " + e.getMessage() + e.getSQLState());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `user` WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Don't delete user from database " + e.getMessage() + e.getSQLState());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public User readByLogin(String login) throws DaoException {
        String sql = "SELECT * FROM `user` WHERE username=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(login);
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setDiscount(resultSet.getInt("discount"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRole(resultSet.getString("role"));
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error("Don't read user by login from database " + e.getMessage() + e.getSQLState());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws DaoException {
        String sql = "SELECT * FROM `user` WHERE username=? and password=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(login);
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(password);
                user.setDiscount(resultSet.getInt("discount"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRole(resultSet.getString("role"));
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error("Don't read user by login and password from database " + e.getMessage() + e.getSQLState());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean isUserBoughtTour(Long id) throws DaoException {
        String sql = "SELECT * FROM `purchase` WHERE user_id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Don't check user who bought tour " + e.getMessage() + e.getSQLState());
            throw new DaoException(e);
        }
        return false;
    }
}
