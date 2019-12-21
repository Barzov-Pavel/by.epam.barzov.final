package service.logic;

import dao.DaoException;
import dao.PurchaseDao;
import domain.Purchase;
import service.PurchaseService;
import service.ServiceException;

import java.util.List;

public class PurchaseServiceImpl extends BaseService implements PurchaseService {
    private PurchaseDao purchaseDao;

    public void setPurchaseDao(PurchaseDao purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    @Override
    public void buyTour(Purchase purchase) throws ServiceException {
        try {
            purchaseDao.create(purchase);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Purchase> getBoughtTours(Long userId) throws ServiceException {
        try {
            return purchaseDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
