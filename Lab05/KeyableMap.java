import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The KeyableMap class is a generic class of type V which is a child of
 * Keyable. It also implements the Keyable interface, alongisde containing
 * attributes of a KeyableMap.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 5
 */
public class KeyableMap<V extends Keyable> implements Keyable {
    private final String key;
    private final Map<String, V> m;

    public KeyableMap(String key) {
        this.key = key;
        this.m = new HashMap<String, V>();
    }

    public Optional<V> get(String key) {
        return Optional.ofNullable(this.m.get(key));
    }

    public KeyableMap<? extends Keyable> put(V item) {
        this.m.put(item.getKey(), item);
        return this;
    }

    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        String s = "";
        int size = this.m.size();

        for (Map.Entry<String, V> e : this.m.entrySet()) {
            --size;

            if (size == 0) {
                s += e.getValue();
            } else {
                s += e.getValue() + ", ";
            }
        }

        return String.format("{%s}", s);
    }
}
