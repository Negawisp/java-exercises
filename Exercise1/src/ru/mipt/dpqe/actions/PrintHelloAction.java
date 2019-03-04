package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.UserSession;

/**
 * Created by krm on 03.04.2017.
 */

// class PrintHelloAction extends UserAction {            //Interfaces are implemented, not extended!
public class PrintHelloAction implements UserAction {     //Class is used outside of package, it has to be public.
    @Override
    public void doAction(UserSession session) {
        System.out.println("Hello");
    }
}
