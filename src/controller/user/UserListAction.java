package controller.user;

import controller.*;
import domain.User;
import org.apache.logging.log4j.*;
import service.*;
import service.exceptions.ServiceException;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/*
 * The class finds all users in database
 */

public class UserListAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(UserListAction.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService service = getServiceFactory().getUserService();
            List<User> users = service.findAll();
            req.setAttribute("users", users);
            return null;
        } catch (FactoryException | ServiceException e) {
            LOGGER.error("Don't set List users " + e.getMessage());
            throw new ServletException(e);
        }
    }
}
