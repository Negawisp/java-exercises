package ru.mipt.dpqe;

import ru.mipt.dpqe.actions.FactorialAction;
import ru.mipt.dpqe.actions.FlushResultsToConsoleAction;
import ru.mipt.dpqe.actions.SumSecretsAction;
import ru.mipt.dpqe.session.ContextKey;

import javax.management.relation.RoleUnresolved;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            run(server, reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
/*
login First
register.action 1 max_secret_factorial arithmetic
register.action 1 sum_secrets arithmetic
register.action 1 flush printing
data.store 1 secrets data 1 2 2 3 4 5
perform.action 1 max_secret_factorial arithmetic
perform.action 1 sum_secrets arithmetic
perform.action 1 flush printing
logout 1


login First
login Second
print.actives 1             // instead of print.actives, this command needs an argument.
register.action 1 sum_secrets arithmetic
*/
    }

    private static void run(Server server, BufferedReader reader) throws IOException {
        String cmd;
        while ((cmd = reader.readLine()) != null) {
            String[] split = cmd.split(" ");
            switch (split[0]) {                         // Switch/case block is solving task of 1 choice.
                case "login": {                         // Thus, breaks should be inserted (and {} for better reading)
                    CheckArgsNumber (split, 2);
                    long id = server.onUserConnected(split[1]);
                    ContextKey answersKey = new ContextKey("answers", "data");
                    server.store(id, answersKey, new HashSet<>());
                    System.err.println(id);
                } break;

                case "logout": {
                    CheckArgsNumber (split, 2);
                    long id = Long.parseLong(split[1]);
                    server.onUserDisconnected(id);
                } break;

                case "register.action": {
                    CheckArgsNumber (split, 4);
                    long id = Long.parseLong(split[1]);
                    ContextKey actionKey = new ContextKey(split[2], split[3]);
                    server.store(id, actionKey, ActionFactory.from(actionKey));
                } break;

                case "perform.action": {
                    CheckArgsNumber (split, 4);
                    long id = Long.parseLong(split[1]);
                    ContextKey actionKey = new ContextKey(split[2], split[3]);
                    server.performAction(id, actionKey);
                } break;

                case "data.store": {
                    CheckArgsNumber (split, 4);
                    long id = Long.parseLong(split[1]);
                    ContextKey dataKey = new ContextKey(split[2], split[3]);
                    Collection<String> data = new ArrayList<>();
                    for (int i = 4; i < split.length; i++) {
                        data.add(split[i]);
                    }
                    server.store(id, dataKey, data);
                } break;

                case "print.actives": {
                    CheckArgsNumber (split, 2);
                    long id = Long.parseLong(split[1]);
                    System.err.println(server.othersIds(id));
                } break;

                default:
                    throw new RuntimeException("Unknown command!");
            }

            System.err.println("Awaiting for the next command.");
        }
    }

    private static void storeSecrets(Server server, long fisrtId, ContextKey secret) {
        String[] secrets = {"1", "2", "3", "4", "5"};
        server.store(fisrtId, secret, secrets);
    }

    private static void CheckArgsNumber (String[] s, int n)
    {
        if (s.length < n)
            throw new RuntimeException("Need at least " + (n-1) + " arguments for this command.");
    }
}
