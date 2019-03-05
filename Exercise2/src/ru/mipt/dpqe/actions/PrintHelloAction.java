package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.UserSession;

/**
 * Created by krm on 03.04.2017.
 */

// Seems legit
public class PrintHelloAction implements UserAction {
    @Override
    public void doAction(UserSession session) {
        System.out.println("Hello");
    }
}
