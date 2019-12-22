package dao;

import domain.Purchase;

import java.util.List;

public interface PurchaseDao extends Dao<Purchase> {
    List<Purchase> getBoughtTours(Long userId) throws DaoException;
}
