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
 * The class finds user by id and checks the user for the ability to remove
 */

public class UserEditAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(UserEditAction.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.findById(id);
                req.setAttribute("user", user);
                req.setAttribute("role", user.getRole());
                boolean userCanBeDeleted = service.canDelete(id);
                req.setAttribute("userCanBeDeleted", userCanBeDeleted);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("Don't set user " + e.getMessage());
                throw new ServletException(e);
            }
        }
        return null;
    }
}
