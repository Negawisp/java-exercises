package ru.mipt.dpqe.session;

/**
 * Created by krm on 04.04.2017.
 */
public class ContextKey {
    private final String key;
    private final String prefix;

    public ContextKey(String key, String prefix) {
        this.key = key;
        this.prefix = prefix;
    }

    public boolean equals(ContextKey o) {
        return key.equalsIgnoreCase(o.key) && prefix.equalsIgnoreCase(o.prefix);
    }

    public int hashCode() {
       return  key != null ? key.hashCode() : 0;
    }
}
