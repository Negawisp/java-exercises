package ru.mipt.dpqe;

import ru.mipt.dpqe.actions.*;
import ru.mipt.dpqe.session.ContextKey;

// Unused imports
// import javax.jws.soap.SOAPBinding;
// import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by krm on 04.04.2017.
 */
public class ActionFactory {
    // Generic Map can't be initialised as non-generic HashMap
    private static final Map<ContextKey, UserAction> actions = new HashMap<>() {{
        put(new ContextKey("max_secret_factorial", "arithmetic"), new FactorialAction());
        put(new ContextKey("flush", "printing"), new FlushResultsToConsoleAction());
        put(new ContextKey("hello", "printing"), new PrintHelloAction());
        put(new ContextKey("secrets", "printing"), new PrintSecretsAction());
        put(new ContextKey("sum_secrets", "arithmetic"), new SumSecretsAction());
    }};

    public static UserAction from(ContextKey actionKey) {
        return actions.get(actionKey);
    }
}
