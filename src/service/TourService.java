package service;

import domain.Tour;

import java.util.List;

public interface TourService {
    Tour findById(Long id) throws ServiceException;

    List<Tour> findAll() throws ServiceException;

    void save(Tour tour) throws ServiceException;

    boolean canDelete(Long id) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
