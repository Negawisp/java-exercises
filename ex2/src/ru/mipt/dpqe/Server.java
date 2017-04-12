package ru.mipt.dpqe;

import ru.mipt.dpqe.actions.PrintHelloAction;
import ru.mipt.dpqe.actions.PrintSecretsAction;
import ru.mipt.dpqe.actions.UserAction;
import ru.mipt.dpqe.session.SessionManager;
import ru.mipt.dpqe.session.UserSession;

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

    public void store(long id, String key, Object value) {
        UserSession session = sessionManager.getSession(id);
        session.put(key, value);
    }

    public void get(long id, String key) {
        UserSession session = sessionManager.getSession(id);
        session.get(key);
    }

    public void performAction(long id, String actionName) {
        UserSession session = sessionManager.getSession(id);
        PrintSecretsAction action = (PrintSecretsAction) session.get(actionName);
        action.doAction(session);
    }
}
