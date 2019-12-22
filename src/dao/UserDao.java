package dao;

import domain.User;

public interface UserDao extends Dao<User> {
    User readByLogin(String login) throws DaoException;

    User readByLoginAndPassword(String login, String password) throws DaoException;

    boolean isUserBoughtTour(Long id) throws DaoException;
}
