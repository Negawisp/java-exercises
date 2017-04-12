package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.UserSession;

/**
 * Created by krm on 03.04.2017.
 */
public class PrintSecretsAction {

    public void doAction(UserSession session) {
        String[] secrets = (String[]) session.get("SECRETS");
        for (int i = 0; i <= secrets.length; i++) {
            System.out.println(secrets[i]);
        }
    }
}
