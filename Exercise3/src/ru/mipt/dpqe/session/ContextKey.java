package ru.mipt.dpqe.session;

/**
 * Created by krm on 04.04.2017.
 */

// This is legit now.
public class ContextKey {
    public final String key;        // They need to be open to another instance of ContextKey, when compared.
    public final String prefix;     // Also, they are final, so no trouble making them public :)

    public ContextKey(String key, String prefix) {
        this.key = key;
        this.prefix = prefix;
    }

    // Still better written equals(...) is required, but it will go for now :)
    @Override                               // We are overriding Object.equals(...).
    public boolean equals(Object obj) {     // Arguments are part of signature, so Object, not ContextKey.
        ContextKey o = (ContextKey)obj;     // Parsing Object to compare to into ContextKey.
        String otherKey = o.key;
        boolean keyEquals = key.equalsIgnoreCase(o.key);
        return key.equalsIgnoreCase(o.key) && prefix.equalsIgnoreCase(o.prefix);
    }

    @Override
    public int hashCode() {
       return  key != null ? key.toLowerCase().hashCode() : 0;  // We want to have no differences caused by cases
    }
}
