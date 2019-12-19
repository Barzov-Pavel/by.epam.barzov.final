package controller.user;

import controller.Action;
import controller.Forward;
import domain.User;
import service.ServiceException;
import service.UserService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
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
                throw new ServletException(e);
            }
        }
        return new Forward("/user/list.html");
    }
}
