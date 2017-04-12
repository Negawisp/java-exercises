package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.ContextKey;
import ru.mipt.dpqe.session.UserSession;

import java.util.Collection;
import java.util.List;

/**
 * Created by krm on 04.04.2017.
 */
public class SumSecretsAction implements UserAction {

    private static final String KEY = "SECRETS";
    private static final String ANSWER_KEY = "ANSWERS";
    private static final String PREFIX = "DATA";

    @Override
    public void doAction(UserSession session) {
        Collection<String> secrets = (Collection<String>) session.get(new ContextKey(KEY, PREFIX));
        int sum = 0;
        for (String secret : secrets) {
            sum += Integer.parseInt(secret);
        }
        Collection<Object> answers = (Collection<Object>) session.get(new ContextKey(ANSWER_KEY, PREFIX));
        answers.add(sum);
    }
}
