package ru.mipt.dpqe.session;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by krm on 03.04.2017.
 */

// Seems legit for me
public class SessionManager {
    private final AtomicLong idSeq = new AtomicLong();
    private Map<Long, UserSession> activeSessions = new HashMap<>();
    private Map<String, UserSession> registered = new HashMap<>();

    public UserSession createUserSession(String name) {
        if (registered.containsKey(name)) {
            UserSession session = registered.get(name);
            activeSessions.put(session.getId(), session);
            return session;
        }
        UserSession session = new UserSession(idSeq.incrementAndGet(), name);
        registered.put(name, session);
        activeSessions.put(session.getId(), session);
        return session;
    }

    public UserSession getSession(long id) {
        return activeSessions.get(id);
    }

    public void logout(long id) {
        activeSessions.remove(id);
    }
}
