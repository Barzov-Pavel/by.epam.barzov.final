package controller;

import controller.purchase.*;
import controller.tour.*;
import controller.user.*;
import org.apache.logging.log4j.*;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/*
 * The class returns action corresponding url
 */

public class ActionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ActionFactory.class);

    private static Map<String, Class<? extends Action>> actions = new HashMap<>();

    /*
     * Set compliance between url and action
     */
    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);
        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
        actions.put("/tour/tour-list", TourListAction.class);
        actions.put("/tour/tour-edit", TourEditAction.class);
        actions.put("/tour/tour-save", TourSaveAction.class);
        actions.put("/tour/tour-delete", TourDeleteAction.class);
        actions.put("/tour/tour-buy", TourBuyAction.class);
        actions.put("/tour/bought-tours", BoughtToursAction.class);
    }

    /*
     * Get action from map using url
     */
    public static Action getAction(String url) throws ServletException {
        Class<?> action = actions.get(url);
        try {
            return (Action) action.getConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            LOGGER.error("Don't get action " + e.getMessage());
            throw new ServletException(e);
        }
    }
}
