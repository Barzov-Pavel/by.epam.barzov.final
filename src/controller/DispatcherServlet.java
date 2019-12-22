package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/*
 * The class is main servlet in application.
 * Does init connector
 * Gets service factory
 * Handles all requests and responses and calls necessary action through service factory
 */

public class DispatcherServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DispatcherServlet.class);

    @Override
    public void init() throws ServletException {
        try {
            Connector.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/new_schema?useUnicode=true&characterEncoding=utf8",
                    "root", "");
        } catch (ClassNotFoundException e) {
            LOGGER.fatal("Don't init jdbc connector " + e.getMessage());
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    public ServiceFactory getServiceFactory() {
        return new MainServiceFactoryImpl();
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String contex = req.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if (postfixIndex != -1) {
            url = url.substring(contex.length(), postfixIndex);
        } else {
            url = url.substring(contex.length());
        }
        Action action = ActionFactory.getAction(url);
        try (ServiceFactory factory = getServiceFactory()) {
            action.setServiceFactory(factory);
            Forward forward = action.execute(req, resp);
            if (forward != null && forward.isRedirect()) {
                resp.sendRedirect(contex + forward.getUrl());
            } else {
                if (forward != null && forward.getUrl() != null) {
                    url = forward.getUrl();
                }
                req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp").forward(req, resp);
            }
        } catch (Exception e) {
            LOGGER.error("Don't get service factory " + e.getMessage());
            throw new ServletException(e);
        }
    }
}
