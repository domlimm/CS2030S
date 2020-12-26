import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The LoggerImpl class is of type generic T. It implements methods that Logger 
 * interface specifies.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 6
 */
public class LoggerImpl<T> implements Logger<T> {
    private final T value;
    private final List<String> logs;

    public LoggerImpl(T value, List<String> logs) {
        this.value = value;
        this.logs = new ArrayList<String>(logs);
    }

    public T getValue() {
        return this.value;
    }

    public List<String> getLogs() {
        return this.logs;
    }

    public void printlog() {
        for (String s : this.logs) {
            System.out.printf("%s\n", s);
        }
    }

    public <U> Logger<U> map(Function<? super T, ? extends U> fn) {
        List<String> newLogs = new ArrayList<String>(this.logs);
        U newValue = fn.apply(this.value);

        if (this.value.equals((Object) newValue)) {
            newLogs.add("Value unchanged. Value = " + newValue);
        } else {
            newLogs.add("Value changed! New value = " + newValue);
        }

        return new LoggerImpl<U>(newValue, newLogs);
    }

    public <U> Logger<U> flatMap(Function<? super T, ? extends Logger<? extends U>> fn) {
        List<String> newLogs = new ArrayList<String>(this.logs);
        Logger<? extends U> tempLogger = fn.apply(this.value);
        List<String> tempLogs = tempLogger.getLogs();
        U newValue = tempLogger.getValue();
        String initLogValue = newLogs.get(0).replaceAll("\\s+", "").split("=")[1];

        for (String t : tempLogs) {
            String currentValue = t.replaceAll("\\s+", "").split("=")[1];

            if (t.indexOf("init") == -1 && !newLogs.contains(t) && !initLogValue.equals(currentValue)) {
                newLogs.add(t);
            }
        }

        return new LoggerImpl<U>(newValue, newLogs);
    }

    public boolean test(Predicate<? super T> pred) {
        return pred.test(this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof LoggerImpl<?>) {
            LoggerImpl<?> newO = (LoggerImpl<?>) o;

            return this.value.equals(((LoggerImpl<?>) o).value) && this.logs.containsAll(newO.logs)
                    && newO.logs.containsAll(this.logs);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Logger[%s]", this.value);
    }
}
