package ru.mipt.dpqe;

import ru.mipt.dpqe.actions.UserAction;
import ru.mipt.dpqe.session.ContextKey;
import ru.mipt.dpqe.session.SessionManager;
import ru.mipt.dpqe.session.UserSession;

import java.util.Set;

/**
 * Created by krm on 03.04.2017.
 */
public class Server {
    private SessionManager sessionManager = new SessionManager();

    public long onUserConnected(String name) {
        UserSession session = sessionManager.createUserSession(name);
        System.out.println("User " + session.getName() + " logged in");
        return session.getId();
    }

    public void onUserDisconnected(long id) {
        UserSession session = sessionManager.getSession(id);
        System.out.println("User " + session.getName() + " logged out");
        sessionManager.logout(id);
    }

    public void store(long id, ContextKey key, Object value) {
        UserSession session = sessionManager.getSession(id);
        session.put(key, value);
    }

    public Object get(long id, ContextKey key) {
        UserSession session = sessionManager.getSession(id);
        return session.get(key);
    }

    public void performAction(long id, ContextKey actionName) {
        UserSession session = sessionManager.getSession(id);
        UserAction action = (UserAction) session.get(actionName);
        action.doAction(session);
    }

    public Set<Long> othersIds(long id) {
        Set<Long> ids = sessionManager.allIds();
        ids.remove(id);
        return ids;
    }
}
