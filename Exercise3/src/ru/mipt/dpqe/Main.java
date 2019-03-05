package ru.mipt.dpqe;

import ru.mipt.dpqe.actions.PrintSecretsAction;
import ru.mipt.dpqe.actions.SumSecretsAction;
import ru.mipt.dpqe.session.ContextKey;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        long fisrtId = server.onUserConnected("Fisrt");
        ContextKey secret = new ContextKey("secrets", "data");          // Lower case is OK because
        ContextKey answersKey = new ContextKey("answers", "data");      // 'equals' with IgnoreCase.

        storeSecrets(server, fisrtId, secret);
        server.store(fisrtId, answersKey, new String[5]);                          // I wonder!!!

        ContextKey secretAction = new ContextKey("secrets", "printing");
        server.store(fisrtId, secretAction, new PrintSecretsAction());

        ContextKey secretSumAction = new ContextKey("secrets_sum", "arithmetic");
        server.store(fisrtId, secretSumAction, new SumSecretsAction());

        server.performAction(fisrtId, secretAction);
        server.performAction(fisrtId, secretSumAction);
        Object[] answers = (Object[]) server.get(fisrtId, answersKey);
        System.out.println(answers[0]);
        server.onUserDisconnected(fisrtId);
    }

    private static void storeSecrets(Server server, long id, ContextKey secret) {

//      String[] secrets = {"one", "two", "three", "foure", "five"};    // Argument of parseInt (String s)
        String[] secrets = {"1", "2", "3", "4", "5"};                   // The characters in the string must
                                                                        // all be digits of the specified radix
        server.store(id, secret, secrets);
    }
}
