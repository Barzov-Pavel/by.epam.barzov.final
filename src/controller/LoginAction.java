package controller;

import domain.User;
import org.apache.logging.log4j.*;
import service.*;
import service.exceptions.ServiceException;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/*
 * The class checks login and password and returns appropriate object user
 */

public class LoginAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(LoginAction.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.findByLoginAndPassword(login, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("currentUser", user);
                    return new Forward("/index.html");
                } else {
                    return new Forward("/login.html?message=login.message");
                }
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("Don't get service factory " + e.getMessage());
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
