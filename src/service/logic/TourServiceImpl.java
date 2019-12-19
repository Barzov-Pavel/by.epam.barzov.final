package service.logic;

import dao.DaoException;
import dao.TourDao;
import domain.Tour;
import domain.User;
import service.*;

import java.util.List;

public class TourServiceImpl extends BaseService implements TourService {
    private TourDao tourDao;

    public void setTourDao(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    @Override
    public Tour findById(Long id) throws ServiceException {
        try {
            return tourDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tour> findAll() throws ServiceException {
        try {
            return tourDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Tour tour) throws ServiceException {
        try {
//            getTransaction().start();
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
//            getTransaction().commit();
        } catch (DaoException e) {
//            try {
////                getTransaction().rollback();
//            } catch (ServiceException e1) {
//            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
//            try {
//                getTransaction().rollback();
//            } catch (ServiceException e1) {
//            }
            throw e;
        }
    }

    @Override
    public boolean canDelete(Long id) throws ServiceException {
        try {
            return !tourDao.isTourWasBought(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            tourDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
