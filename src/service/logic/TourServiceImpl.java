package service.logic;

import org.apache.logging.log4j.*;
import service.*;
import dao.*;
import domain.Tour;
import service.exceptions.ServiceException;
import service.exceptions.TourNotExistsException;

import java.util.List;

public class TourServiceImpl extends BaseService implements TourService {
    private static final Logger LOGGER = LogManager.getLogger(TourServiceImpl.class);
    private TourDao tourDao;

    public void setTourDao(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    @Override
    public Tour findById(Long id) throws ServiceException {
        try {
            return tourDao.read(id);
        } catch (DaoException e) {
            LOGGER.error("Don't find tour by id. Service Exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tour> findAll() throws ServiceException {
        try {
            return tourDao.readAll();
        } catch (DaoException e) {
            LOGGER.error("Don't find all tours. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Tour tour) throws ServiceException {
        try {
            if (tour.getId() != null) {
                Tour storedTour = tourDao.read(tour.getId());
                if (storedTour != null) {
                    tourDao.update(tour);
                } else {
                    throw new TourNotExistsException(tour.getId());
                }
            } else {
                Long id = tourDao.create(tour);
                tour.setId(id);

            }
        } catch (DaoException | ServiceException e) {
            LOGGER.error("Don't save tour. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean canDelete(Long id) throws ServiceException {
        try {
            return !tourDao.isTourWasBought(id);
        } catch (DaoException e) {
            LOGGER.error("Don't  check the tour for the possibility of deletion. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            tourDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error("Don't delete tour. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }
}
