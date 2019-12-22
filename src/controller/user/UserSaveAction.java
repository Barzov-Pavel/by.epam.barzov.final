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

/*
 * The class saves information about user in database
 */

public class UserSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(UserSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            user.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
        }
        try {
            user.setUserName(req.getParameter("login"));
            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            user.setPassword(req.getParameter("password"));
            user.setDiscount(Integer.parseInt(req.getParameter("discount")));
            user.setTelephone(req.getParameter("phone"));
            user.setRole(req.getParameter("role"));
        } catch (NumberFormatException e) {
        }
        if (user.getUserName() != null && user.getRole() != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                service.save(user);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("Don't save user " + e.getMessage());
                throw new ServletException(e);
            }
        }
        return new Forward("/user/list.html");
    }
}
