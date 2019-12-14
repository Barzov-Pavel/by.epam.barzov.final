package dao;

import domain.*;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao extends Dao<User> {
    User readByLogin(String login) throws DaoException;

    User readByLoginAndPassword(String login, String password) throws DaoException;

    boolean isUserInitiatesTransfers(Long id) throws DaoException;
//    void addRoles(Long userId, List<Role> roles);
//
//    void addRole(Long userId, Role role);
//
//    void deleteRoles(Long userId);
//
//    void updateRoles(Long userId, List<Role> roles);
//
//    List<Role> readRoles(Long userId);
//
//    User read(String username);
//
//    int countPurchases(Long userId);
//
//    BigDecimal computePurchasesTotalPrice(Long userId);
//
//    List<User> findAllOrderByRegularity(boolean byTotalPrice);
}
