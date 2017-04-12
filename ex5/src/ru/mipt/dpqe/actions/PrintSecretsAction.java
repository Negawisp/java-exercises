package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.ContextKey;
import ru.mipt.dpqe.session.UserSession;

import java.util.Collection;

/**
 * Created by krm on 03.04.2017.
 */
public class PrintSecretsAction implements UserAction {

    private static final String KEY = "SECRETS";
    private static final String PREFIX = "DATA";

    @Override
    public void doAction(UserSession session) {
        Collection<String> secrets = (Collection<String>) session.get(new ContextKey(KEY, PREFIX));
        for (String secret : secrets) {
            System.out.println(secret);
        }
    }
}
