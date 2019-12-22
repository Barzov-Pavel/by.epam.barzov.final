package dao.mysql;

import dao.*;
import domain.*;
import org.apache.logging.log4j.*;

import java.sql.*;
import java.util.*;

/*
 * The class works with Purchase table.
 * Create new purchase.
 * Read by id.
 * Read all tuples from table.
 * Update tuple.
 * Delete tuple by id.
 */

public class PurchaseDaoImpl extends BaseDaoImpl implements PurchaseDao {
    private static final Logger LOGGER = LogManager.getLogger(PurchaseDaoImpl.class);

    @Override
    public Long create(Purchase purchase) throws DaoException {
        String sql = "INSERT INTO `purchase` (`user_id`, `tour_id`, " +
                "`date`, `price`, `status`)" +
                " VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, purchase.getUser().getId());
            statement.setLong(2, purchase.getTour().getId());
            statement.setDate(3, purchase.getDate());
            statement.setBigDecimal(4, purchase.getPrice());
            statement.setString(5, purchase.getStatus());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            LOGGER.error("Don't create purchase in database " + e.getMessage() + e.getSQLState());
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
    public Purchase read(Long id) throws DaoException {
        String sql = "SELECT * FROM `purchase` WHERE id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Purchase purchase = null;
            if (resultSet.next()) {
                purchase = new Purchase();
                purchase.setId(id);
                purchase.setUser(new User(resultSet.getLong("user_id")));
                purchase.setTour(new Tour(resultSet.getLong("tour_id")));
                purchase.setDate(resultSet.getDate("date"));
                purchase.setPrice(resultSet.getBigDecimal("price"));
                purchase.setStatus(resultSet.getString("status"));
            }
            return purchase;
        } catch (SQLException e) {
            LOGGER.error("Don't read purchase by id " + e.getMessage() + e.getSQLState());
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
    public List<Purchase> readAll() throws DaoException {
        UserDaoImpl userDao = new UserDaoImpl();
        TourDaoImpl tourDao = new TourDaoImpl();
        userDao.setConnection(getConnection());
        tourDao.setConnection(getConnection());

        String sql = "SELECT * FROM `purchase`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Purchase> purchases = new ArrayList<>();
            while (resultSet.next()) {
                Purchase purchase = new Purchase();
                purchase.setId(resultSet.getLong("id"));
                purchase.setUser(userDao.read(resultSet.getLong("user_id")));
                purchase.setTour(tourDao.read(resultSet.getLong("tour_id")));
                purchase.setDate(resultSet.getDate("date"));
                purchase.setPrice(resultSet.getBigDecimal("price"));
                purchase.setStatus(resultSet.getString("status"));
                purchases.add(purchase);
            }
            return purchases;
        } catch (SQLException e) {
            LOGGER.error("Don't read all tuples from purchase table " + e.getMessage() + e.getSQLState());
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
    public void update(Purchase purchase) throws DaoException {
        String sql = "UPDATE `purchase` SET `user_id`=?, `tour_id`=?," +
                " `date`=?, `price`=?, `status`=? WHERE `id`=?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, purchase.getUser().getId());
            statement.setLong(2, purchase.getTour().getId());
            statement.setDate(3, purchase.getDate());
            statement.setBigDecimal(4, purchase.getPrice());
            statement.setString(5, purchase.getStatus());
            statement.setLong(6, purchase.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Don't update tuple in purchase table " + e.getMessage() + e.getSQLState());
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
        String sql = "DELETE FROM `purchase` WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Don't delete purchase " + e.getMessage() + e.getSQLState());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }
}
