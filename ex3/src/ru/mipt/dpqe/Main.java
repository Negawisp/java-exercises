package ru.mipt.dpqe;

import ru.mipt.dpqe.actions.PrintSecretsAction;
import ru.mipt.dpqe.actions.SumSecretsAction;
import ru.mipt.dpqe.session.ContextKey;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        long fisrtId = server.onUserConnected("First");
        ContextKey secret = new ContextKey("secrets", "data");
        ContextKey answersKey = new ContextKey("answers", "data");
        server.store(fisrtId, answersKey, new String[5]);
        storeSecrets(server, fisrtId, secret);

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

    private static void storeSecrets(Server server, long fisrtId, ContextKey secret) {
        String[] secrets = {"one", "two", "three", "foure", "five"};
        server.store(fisrtId, secret, secrets);
    }
}
