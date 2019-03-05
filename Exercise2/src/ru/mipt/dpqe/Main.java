package ru.mipt.dpqe;

import ru.mipt.dpqe.actions.PrintHelloAction;
import ru.mipt.dpqe.actions.PrintSecretsAction;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
//      long fisrtId = server.onUserConnected("First");             // User is intended to be called "Fisrt"
        long fisrtId = server.onUserConnected("Fisrt");
        String secret = "SECRETS";
        String action = "PrintAction";
        server.store(fisrtId, action, new PrintHelloAction());
        String secretAction = "PrintSecretsAction";
        server.store(fisrtId, secretAction, new PrintSecretsAction());
        storeSecrets(server, fisrtId, secret);
        server.performAction(fisrtId, secretAction);
        server.performAction(fisrtId, action);
        server.onUserDisconnected(fisrtId);
    }

//  private void storeSecrets(Server server, long fisrtId, String secret) {     // Only static methods can be
    private static void storeSecrets(Server server, long id, String secret) {   // called from static method.
        String[] secrets = {"one", "two", "three", "four", "five"};             // Also it's just id here, 'cause
        server.store(id, secret, secrets);                                      // we can have any user to store
    }                                                                           // their secrets.
}
