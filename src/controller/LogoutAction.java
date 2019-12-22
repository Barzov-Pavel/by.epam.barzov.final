package controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/*
 * The class makes logout and close session
 */

public class LogoutAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new Forward("/index.html");
    }
}
