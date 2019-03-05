package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.ContextKey;
import ru.mipt.dpqe.session.UserSession;

import java.util.List;

/**
 * Created by krm on 04.04.2017.
 */

// Checked!
public class FlushResultsToConsoleAction implements UserAction {
    private static final String ANSWER_KEY = "ANSWERS";
    private static final String PREFIX = "DATA";

    @Override
    public void doAction(UserSession session) {
        List<?> answers = (List) session.get(new ContextKey(ANSWER_KEY, PREFIX));

        /*                                      // With such realisation,
        for (Object answer : answers) {         // next item will become
            System.out.println(answer);         // first and will be inevitably
            answers.remove(answer);             // missed.
        }                                       //
        */                                      //

        /*
        while (!answers.isEmpty())
        {
            Object answer = answers.get(0);
            System.out.println(answer);
            answers.remove(0);
        }
        */

        while (!answers.isEmpty()) {
            int n = answers.size() - 1;
            Object answer = answers.get(n);
            System.out.println(answer);
            answers.remove(n);
        }
    }
}
