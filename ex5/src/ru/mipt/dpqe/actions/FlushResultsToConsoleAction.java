package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.ContextKey;
import ru.mipt.dpqe.session.UserSession;

import java.util.Collection;
import java.util.List;

/**
 * Created by krm on 04.04.2017.
 */
public class FlushResultsToConsoleAction implements UserAction {
    private static final String ANSWER_KEY = "ANSWERS";
    private static final String PREFIX = "DATA";

    @Override
    public void doAction(UserSession session) {
        Collection<?> answers = (Collection<?>) session.get(new ContextKey(ANSWER_KEY, PREFIX));
        for (Object answer : answers) {
            System.out.println(answer);
            answers.remove(answer);
        }
        answers.clear();
    }
}
