package ru.mipt.dpqe;

import ru.mipt.dpqe.actions.PrintHelloAction;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        long fisrtId = server.onUserConnected("Fisrt");
        String action = "PrintAction";
        server.store(fisrtId, action, new PrintHelloAction());
        server.performAction(fisrtId, action);
        server.onUserDisconnected(fisrtId);
    }
}
