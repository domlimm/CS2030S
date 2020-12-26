import java.util.function.Function;
import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;

/**
 * The Logger interface is of type generic T. It holds methods that LoggerImpl 
 * class has to implement.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 6
 */
public interface Logger<T> {
    public static <T> Logger<T> make(T value) {
        if (value == null) {
            throw new IllegalArgumentException("argument cannot be null");
        } else if (value instanceof Logger) {
            throw new IllegalArgumentException("already a Logger");
        } else {
            List<String> newLogs = new ArrayList<String>();
            newLogs.add("Value initialized. Value = " + value);

            return new LoggerImpl<T>(value, newLogs);
        }
    }

    public void printlog();
    public <U> Logger<U> map(Function<? super T, ? extends U> fn);
    public <U> Logger<U> flatMap(Function<? super T, ? extends Logger<? extends U>> fn);
    public T getValue();
    public List<String> getLogs();
    public boolean test(Predicate<? super T> pred);
}
