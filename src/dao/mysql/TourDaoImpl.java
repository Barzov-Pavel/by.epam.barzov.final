package dao.mysql;

import dao.*;
import domain.Tour;
import org.apache.logging.log4j.*;

import java.sql.*;
import java.util.*;

/*
 * The class works with tour table in database.
 * Can create, update, read by id, read all, delete and check bought tours.
 */

public class TourDaoImpl extends BaseDaoImpl implements TourDao {
    private static final Logger LOGGER = LogManager.getLogger(TourDaoImpl.class);

    @Override
    public Long create(Tour tour) throws DaoException {
        String sql = "INSERT INTO `tour` (`title`, `description`, " +
                "`type`, `hot`, `price`, `enabled`, `avg_rating`," +
                "`votes_count`, `discount`, `destination`)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tour.getTitle());
            statement.setString(2, tour.getDescription());
            statement.setString(3, tour.getType());
            statement.setBoolean(4, tour.isHot());
            statement.setBigDecimal(5, tour.getPrice());
            statement.setBoolean(6, tour.isEnabled());
            statement.setDouble(7, tour.getAvgRating());
            statement.setInt(8, tour.getVotesCount());
            statement.setInt(9, tour.getDiscount());
            statement.setString(10, tour.getDestination());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            LOGGER.error("Don't create tour tuple in database " + e.getMessage() + e.getSQLState());
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
    public Tour read(Long id) throws DaoException {
        String sql = "SELECT * FROM `tour` WHERE id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Tour tour = null;
            if (resultSet.next()) {
                tour = new Tour();
                tour.setId(id);
                tour.setTitle(resultSet.getString("title"));
                tour.setDescription(resultSet.getString("description"));
                tour.setType(resultSet.getString("type"));
                tour.setHot(resultSet.getBoolean("hot"));
                tour.setPrice(resultSet.getBigDecimal("price"));
                tour.setEnabled(resultSet.getBoolean("enabled"));
                tour.setAvgRating(resultSet.getDouble("avg_rating"));
                tour.setVotesCount(resultSet.getInt("votes_count"));
                tour.setDiscount(resultSet.getInt("discount"));
                tour.setDestination(resultSet.getString("destination"));
            }
            return tour;
        } catch (SQLException e) {
            LOGGER.error("Don't read tour in database " + e.getMessage() + e.getSQLState());
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
    public List<Tour> readAll() throws DaoException {
        String sql = "SELECT * FROM `tour`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Tour> tours = new ArrayList<>();
            while (resultSet.next()) {
                Tour tour = new Tour();
                tour.setId(resultSet.getLong("id"));
                tour.setTitle(resultSet.getString("title"));
                tour.setDescription(resultSet.getString("description"));
                tour.setType(resultSet.getString("type"));
                tour.setHot(resultSet.getBoolean("hot"));
                tour.setPrice(resultSet.getBigDecimal("price"));
                tour.setEnabled(resultSet.getBoolean("enabled"));
                tour.setAvgRating(resultSet.getDouble("avg_rating"));
                tour.setVotesCount(resultSet.getInt("votes_count"));
                tour.setDiscount(resultSet.getInt("discount"));
                tour.setDestination(resultSet.getString("destination"));
                tours.add(tour);
            }
            return tours;
        } catch (SQLException e) {
            LOGGER.error("Don't read all tuples from tour table " + e.getMessage() + e.getSQLState());
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
    public void update(Tour tour) throws DaoException {
        String sql = "UPDATE `tour` SET `title`=?, `description`=?," +
                " `type`=?, `hot`=?, `price`=?, `enabled`=?, `avg_rating`=?," +
                "`votes_count`=?, `discount`=?, `destination`=? WHERE `id`=?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, tour.getTitle());
            statement.setString(2, tour.getDescription());
            statement.setString(3, tour.getType());
            statement.setBoolean(4, tour.isHot());
            statement.setBigDecimal(5, tour.getPrice());
            statement.setBoolean(6, tour.isEnabled());
            statement.setDouble(7, tour.getAvgRating());
            statement.setInt(8, tour.getVotesCount());
            statement.setInt(9, tour.getDiscount());
            statement.setString(10, tour.getDestination());
            statement.setLong(11, tour.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Don't update tour tuple " + e.getMessage() + e.getSQLState());
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
        String sql = "DELETE FROM `tour` WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Don't delete tour tuple from database " + e.getMessage() + e.getSQLState());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean isTourWasBought(Long id) throws DaoException {
        String sql = "SELECT * FROM `purchase` WHERE tour_id=?";
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
            LOGGER.error("Don't check bought tour in database " + e.getMessage() + e.getSQLState());
            throw new DaoException(e);
        }
        return false;
    }
}
