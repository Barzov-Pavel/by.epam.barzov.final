package service;

import domain.Purchase;

import java.util.List;

public interface PurchaseService {
    void buyTour(Purchase purchase) throws ServiceException;

    List<Purchase> getBoughtTours(Long userId) throws ServiceException;
}
