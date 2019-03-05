package ru.mipt.dpqe;

import ru.mipt.dpqe.actions.FactorialAction;
import ru.mipt.dpqe.actions.FlushResultsToConsoleAction;
import ru.mipt.dpqe.actions.PrintSecretsAction;
import ru.mipt.dpqe.actions.SumSecretsAction;
import ru.mipt.dpqe.session.ContextKey;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        long firstId = server.onUserConnected("First");
        ContextKey secret = new ContextKey("secrets", "data");
        ContextKey answersKey = new ContextKey("answers", "data");
        server.store(firstId, answersKey, new ArrayList<>());
        storeSecrets(server, firstId, secret);

        ContextKey secretSumAction = new ContextKey("secrets_sum", "arithmetic");
        server.store(firstId, secretSumAction, new SumSecretsAction());
        ContextKey factorialAction = new ContextKey("max_secret_factorial", "arithmetic");
        server.store(firstId, factorialAction, new FactorialAction());
        ContextKey flushAction = new ContextKey("flush", "printing");
        server.store(firstId, flushAction, new FlushResultsToConsoleAction());
        server.performAction(firstId, factorialAction);
        server.performAction(firstId, secretSumAction);
        server.performAction(firstId, flushAction);


        server.onUserDisconnected(firstId);
    }

    private static void storeSecrets(Server server, long fisrtId, ContextKey secret) {
        String[] secrets = {"1", "2", "2", "13"};
        server.store(fisrtId, secret, secrets);
    }
}
