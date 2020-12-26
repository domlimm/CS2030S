import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * The LazyList class is of type generic T which extends the Comparable
 * interface. It holds Lazy objects. All of which are lazily evaluated.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 7
 */
public class LazyList<T extends Comparable<T>> {
    private List<Lazy<T>> list;

    private LazyList(List<Lazy<T>> list) {
        this.list = new ArrayList<Lazy<T>>(list);
    }

    static <T> LazyList<T> generate(int n, T seed, UnaryOperator<T> f) {
        return new LazyList<T>(
                Stream
                .iterate(Lazy.of(seed),
                        x -> Lazy.of((Supplier<T>) () -> f.apply(x.get())))
                .limit(n)
                .collect(Collectors.toList()));
    }

    public T get(int i) {
        return this.list.get(i).get();
    }

    public int indexOf(T v) {
        return this.list.indexOf(Lazy.of(v));
    }
}
