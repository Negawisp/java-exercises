package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.ContextKey;
import ru.mipt.dpqe.session.UserSession;

/**
 * Created by krm on 04.04.2017.
 */
public class SumSecretsAction implements UserAction {

    private static final String KEY = "SECRETS";
    private static final String ANSWER_KEY = "ANSWERS";
    private static final String PREFIX = "DATA";

    @Override
    public void doAction(UserSession session) {
        String[] secrets = (String[]) session.get(new ContextKey(KEY, PREFIX));
        int sum = 0;
        for (String secret : secrets) {
            sum += Integer.parseInt(secret);
        }
        Object[] answers = (Object[]) session.get(new ContextKey(ANSWER_KEY, PREFIX));
        answers[0] = sum;
    }
}
