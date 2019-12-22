package service.logic;

import java.util.List;

import dao.*;
import domain.User;
import org.apache.logging.log4j.*;
import service.*;
import service.exceptions.ServiceException;
import service.exceptions.UserLoginNotUniqueException;
import service.exceptions.UserNotExistsException;
import service.exceptions.UserPasswordIncorrectException;

public class UserServiceImpl extends BaseService implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

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
            return userDao.read(id);
        } catch (DaoException e) {
            LOGGER.error("Don't find user by id. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        } catch (DaoException e) {
            LOGGER.error("Don't find user by login and password. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch (DaoException e) {
            LOGGER.error("Don't find all users. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            if (user.getId() != null) {
                User storedUser = userDao.read(user.getId());
                if (storedUser != null) {
                    user.setPassword(storedUser.getPassword());
                    if (storedUser.getUserName().equals(user.getUserName()) || userDao.readByLogin(user.getUserName()) == null) {
                        userDao.update(user);
                    } else {
                        LOGGER.warn("Login not unique");
                        throw new UserLoginNotUniqueException(user.getUserName());
                    }
                } else {
                    throw new UserNotExistsException(user.getId());
                }
            } else {
                if (userDao.readByLogin(user.getUserName()) == null) {
                    Long id = userDao.create(user);
                    user.setId(id);
                } else {
                    LOGGER.warn("Login not unique");
                    throw new UserLoginNotUniqueException(user.getUserName());
                }
            }
        } catch (DaoException | ServiceException e) {
            LOGGER.error("Don't save user. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException {
        try {
            getTransaction().start();
            User user = userDao.read(userId);
            if (user != null) {
                if (user.getPassword().equals(oldPassword)) {
                    if (newPassword == null) {
                        newPassword = defaultPassword;
                    }
                    user.setPassword(newPassword);
                    userDao.update(user);
                } else {
                    LOGGER.warn("Incorrect password");
                    throw new UserPasswordIncorrectException(user.getId());
                }
            } else {
                LOGGER.warn("User not exists");
                throw new UserNotExistsException(userId);
            }
            getTransaction().commit();
        } catch (DaoException | ServiceException e) {
            LOGGER.error("Don't change password. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean canDelete(Long id) throws ServiceException {
        try {
            return !userDao.isUserBoughtTour(id);
        } catch (DaoException e) {
            LOGGER.error("Don't check user for possibility of deletion. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error("Don't delete user. Service exception " + e.getMessage());
            throw new ServiceException(e);
        }
    }
}
