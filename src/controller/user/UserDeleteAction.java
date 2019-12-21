package controller.user;

import controller.Action;
import controller.Forward;
import service.ServiceException;
import service.UserService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.*;

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
