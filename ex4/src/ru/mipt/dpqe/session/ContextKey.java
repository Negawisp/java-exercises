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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContextKey that = (ContextKey) o;

        if (key != null ? !key.equalsIgnoreCase(that.key) : that.key != null) return false;
        return prefix != null ? prefix.equalsIgnoreCase(that.prefix) : that.prefix == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.toLowerCase().hashCode() : 0;
        result = 31 * result + (prefix != null ? prefix.toLowerCase().hashCode() : 0);
        return result;
    }
}
