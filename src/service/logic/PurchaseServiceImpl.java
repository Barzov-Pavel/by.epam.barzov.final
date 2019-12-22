package service.logic;

import dao.*;
import domain.Purchase;
import org.apache.logging.log4j.*;
import service.*;
import service.exceptions.ServiceException;

import java.util.List;

public class PurchaseServiceImpl extends BaseService implements PurchaseService {
    private static final Logger LOGGER = LogManager.getLogger(PurchaseServiceImpl.class);
    private PurchaseDao purchaseDao;

    public void setPurchaseDao(PurchaseDao purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    @Override
    public void buyTour(Purchase purchase) throws ServiceException {
        try {
            purchaseDao.create(purchase);
        } catch (DaoException e) {
            LOGGER.error("Don't create purchase. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Purchase> getBoughtTours(Long userId) throws ServiceException {
        try {
            return purchaseDao.readAll();
        } catch (DaoException e) {
            LOGGER.error("Don't read all purchases. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }
}
