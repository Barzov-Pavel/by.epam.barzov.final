package controller.tour;

import controller.Action;
import controller.Forward;
import service.ServiceException;
import service.TourService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TourDeleteAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try {
                TourService service = getServiceFactory().getTourService();
                service.delete(id);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/user/tour-list.html");
    }
}
