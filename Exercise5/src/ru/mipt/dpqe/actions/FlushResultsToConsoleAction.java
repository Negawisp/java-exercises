package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.ContextKey;
import ru.mipt.dpqe.session.UserSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
/*      for (Object answer : answers) {         // This is not a universal solution for all collections.
          System.out.println(answer);
          answers.remove(answer);
      }
*/
        for (Object answer : answers) {         // But no one said there is one!
            System.out.println(answer);         // This solution is very not perfect, but it will work
        }                                       // with any collection (too lazy to Google further)
        answers.removeAll(answers);
    }
}
