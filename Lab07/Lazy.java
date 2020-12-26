import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The Lazy class is of type generic T which extends the Comparable interface.
 * It contains the methods of an ArrayList. All of which are lazily evaluated.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 7
 */
public class Lazy<T extends Comparable<T>> {
    private Optional<T> value;
    private final Supplier<T> supplier;
    private boolean resolved;

    public Lazy(Supplier<T> supplier) {
        this.value = Optional.empty();
        this.supplier = supplier;
        this.resolved = false;
    }

    public static <T extends Comparable<T>> Lazy<T> of(T v) {
        return new Lazy<T>(() -> v);
    }

    public static <T extends Comparable<T>> Lazy<T> of(Supplier<T> s) {
        Optional<Supplier<T>> tempOptional = Optional.ofNullable(s);
        tempOptional.orElseThrow(() -> new NoSuchElementException("No value present"));

        return new Lazy<T>(s);
    }

    public T get() {
        if (!resolved) {
            this.value = Optional.of(this.supplier.get());
            this.resolved = true;
        }
        
        return this.value.orElseThrow();
    }

    public <U extends Comparable<U>> Lazy<U> map(Function<? super T, ? extends U> f) {
        return new Lazy<U>(() -> f.apply(this.get()));
    }

    public <U extends Comparable<U>> Lazy<U> flatMap(
            Function<? super T, ? extends Lazy<? extends U>> f) {
        return new Lazy<U>(() -> f.apply(this.get()).get());
    }

    public <U extends Comparable<U>, V extends Comparable<V>> Lazy<V> combine(Lazy<U> secondLazy,
            BiFunction<T, U, V> bf) {
        return new Lazy<V>(() -> bf.apply(this.get(), secondLazy.get()));
    }

    public Lazy<Boolean> test(Predicate<? super T> p) {
        return new Lazy<Boolean>(() -> p.test(this.get()));
    }

    public Lazy<Integer> compareTo(Lazy<T> secondLazy) {
        return new Lazy<Integer>(() -> this.get().compareTo(secondLazy.get()));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Lazy<?>) {
            return this.get().equals(((Lazy<?>) o).get());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.resolved ? String.valueOf(this.get()) : "?";
    }
}
