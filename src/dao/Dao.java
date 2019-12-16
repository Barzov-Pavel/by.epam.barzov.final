package dao;

import java.util.List;

/**
 * An interface that represents a generic data access object
 *
 * @param <T> type of the target entity
 */
public interface Dao<T> {
    Long create(T entity) throws DaoException;

    T read(Long id) throws DaoException;

    List<T> readAll() throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Long id) throws DaoException;
}
