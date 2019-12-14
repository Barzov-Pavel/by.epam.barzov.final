package service.logic;

import java.util.List;

import dao.DaoException;
import dao.UserDao;
import domain.User;
import service.ServiceException;
import service.UserLoginNotUniqueException;
import service.UserNotExistsException;
import service.UserPasswordIncorrectException;
import service.UserService;

public class UserServiceImpl extends BaseService implements UserService {
    private UserDao userDao;
    private String defaultPassword;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return (User) userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            getTransaction().start();
            if (user.getId() != null) {
                User storedUser = (User) userDao.read(user.getId());
                if (storedUser != null) {
                    user.setPassword(storedUser.getPassword());
                    if (storedUser.getUserName().equals(user.getUserName()) || userDao.readByLogin(user.getUserName()) == null) {
                        userDao.update(user);
                    } else {
                        throw new UserLoginNotUniqueException(user.getUserName());
                    }
                } else {
                    throw new UserNotExistsException(user.getId());
                }
            } else {
                user.setPassword(defaultPassword);
                if (userDao.readByLogin(user.getUserName()) == null) {
                    Long id = userDao.create(user);
                    user.setId(id);
                } else {
                    throw new UserLoginNotUniqueException(user.getUserName());
                }
            }
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException {
        try {
            getTransaction().start();
            User user = (User) userDao.read(userId);
            if (user != null) {
                if (user.getPassword().equals(oldPassword)) {
                    if (newPassword == null) {
                        newPassword = defaultPassword;
                    }
                    user.setPassword(newPassword);
                    userDao.update(user);
                } else {
                    throw new UserPasswordIncorrectException(user.getId());
                }
            } else {
                throw new UserNotExistsException(userId);
            }
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public boolean canDelete(Long id) throws ServiceException {
        try {
            return !userDao.isUserInitiatesTransfers(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
