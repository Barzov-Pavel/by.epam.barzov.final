package controller.user;

import controller.*;
import service.*;
import service.exceptions.ServiceException;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import org.apache.logging.log4j.*;

/*
 * The class implements functionality for delete user
 * We can delete a user who has never bought a tour
 */

public class UserDeleteAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(UserDeleteAction.class);

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
                service.delete(id);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("Don't delete user" + e.getMessage());
                throw new ServletException(e);
            }
        }
        return new Forward("/user/list.html");
    }
}
