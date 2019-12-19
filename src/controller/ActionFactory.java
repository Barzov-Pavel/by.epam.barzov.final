package controller;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();

    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);
        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
    }

    public static Action getAction(String usrl) throws ServletException {
        Class<?> action = actions.get(usrl);
        try {
            return (Action) action.getConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new ServletException(e);
        }
    }
}
