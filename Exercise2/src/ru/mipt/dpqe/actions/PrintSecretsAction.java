package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.UserSession;

/**
 * Created by krm on 03.04.2017.
 */


// public class PrintSecretsAction {                    // This one should implement UserAction
public class PrintSecretsAction implements UserAction {
    @Override                                           // PSA.doAction overrides UA.doAction.
    public void doAction(UserSession session) {
        String[] secrets = (String[]) session.get("SECRETS");
//      for (int i = 0; i <= secrets.length; i++) {     // Let's skip trying to get out of bounds :)
        for (int i = 0; i < secrets.length; i++) {
            System.out.println(secrets[i]);
        }
    }
}
