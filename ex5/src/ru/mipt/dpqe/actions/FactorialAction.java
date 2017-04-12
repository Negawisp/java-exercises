package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.ContextKey;
import ru.mipt.dpqe.session.UserSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Created by krm on 04.04.2017.
 */
public class FactorialAction implements UserAction {
    private final Comparer comparer = new Comparer();
    private static final String KEY = "SECRETS";
    private static final String ANSWER_KEY = "ANSWERS";
    private static final String PREFIX = "DATA";

    @Override
    public void doAction(UserSession session) {
        Collection<String> secrets = (Collection<String>) session.get(new ContextKey(KEY, PREFIX));
        List<Integer> numbers = new ArrayList<>();
        populateNumbersWithSecrets(numbers, secrets);
        int max = numbers.stream().max(comparer).get();
        int fact = factorial(max);
        Collection<Object> answers = (Collection<Object>) session.get(new ContextKey(ANSWER_KEY, PREFIX));
        answers.add(fact);
    }

    private int factorial(int max) {
        if (max == 0) {
            return 1;
        }
        return max * factorial(max - 1);
    }

    private void populateNumbersWithSecrets(List<? super Integer> numbers, Collection<String> secrets) {
        for (String secret : secrets) {
            numbers.add(Integer.parseInt(secret));
        }
    }

    private class Comparer implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }
}
