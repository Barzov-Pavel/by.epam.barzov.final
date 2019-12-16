package service.logic;

import dao.DaoException;
import dao.TourDao;
import domain.Tour;
import service.ServiceException;
import service.TourService;

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
}
