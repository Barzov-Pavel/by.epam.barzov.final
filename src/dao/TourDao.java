package dao;

import domain.Tour;

public interface TourDao extends Dao<Tour> {
    boolean isTourWasBought(Long id) throws DaoException;
}
