package ru.mipt.dpqe.session;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by krm on 03.04.2017.
 */
public class UserSession {
    private long id;
    private String name;
    private Map<String, Object> context = new HashMap<>();

    public UserSession(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void put(String key, Object value) {
        context.put(key, value);
    }

    public Object get(String key) {
        return context.get(key);
    }
}
