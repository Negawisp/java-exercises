package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.UserSession;
import ru.mipt.dpqe.session.ContextKey;

/**
 * Created by krm on 03.04.2017.
 */
public class PrintSecretsAction implements UserAction {

    private static final String KEY = "SECRETS";
    private static final String PREFIX = "DATA";

    @Override
    public void doAction(UserSession session) {
        String[] secrets = (String[]) session.get(new ContextKey(KEY, PREFIX));
        for (int i = 0; i < secrets.length; i++) {
            System.out.println(secrets[i]);
        }
    }
}
