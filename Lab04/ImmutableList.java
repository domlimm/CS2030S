import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

/**
 * The ImmutableList class holds a List of type T (generic).
 * It contains the basic functionalities of a List.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 4
 */
class ImmutableList<T> {
    private final List<T> list;

    public ImmutableList(List<T> list) {
        this.list = new ArrayList<T>(list);
    }

    @SafeVarargs
    public ImmutableList(T... varargs) {
        this.list = new ArrayList<T>(Arrays.asList(varargs));
    }

    public ImmutableList<T> add(T t) {
        List<T> tempList = new ArrayList<T>(this.list);
        tempList.add(t);

        return toImmutableList(tempList);
    }

    public ImmutableList<T> remove(T t) {
        List<T> tempList = new ArrayList<T>(this.list);
        tempList.remove(t);

        return toImmutableList(tempList);
    }

    public ImmutableList<T> replace(T previous, T current) {
        List<T> tempList = new ArrayList<T>(this.list);
        ListIterator<T> li = tempList.listIterator();

        while (li.hasNext()) {
            T element = li.next();

            if (element.equals(previous)) {
                li.set(current);
            }
        }

        return toImmutableList(tempList);
    }

    public ImmutableList<T> limit(long length) {
        try {
            List<T> tempList = new ArrayList<T>(this.list);
            int lengthTList = tempList.size();

            return toImmutableList(tempList.subList(0,
                    length >= lengthTList ? lengthTList : (int) length));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("limit size < 0");
        }
    }

    @Override
    public boolean equals(Object data) {
        if (this == data) {
            return true;
        } else if (data instanceof ImmutableList<?>) {
            return this.list.equals(((ImmutableList<?>) data).list);
        } else {
            return false;
        }
    }

    public ImmutableList<T> sorted(Comparator<T> c) {
        if (c == null) {
            throw new NullPointerException("Comparator is null");
        }

        List<T> tempList = new ArrayList<T>(this.list);
        tempList.sort(c);

        return toImmutableList(tempList);
    }

    public Object[] toArray() {
        List<T> newList = new ArrayList<T>(this.list);

        return newList.toArray();
    }

    public <U> U[] toArray(U[] data) {
        try {
            return this.list.toArray(data);
        } catch (ArrayStoreException e) {
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
        } catch (NullPointerException e) {
            throw new NullPointerException("Input array cannot be null");
        }
    }

    public ImmutableList<T> filter(Predicate<? super T> predicate) {
        List<T> tempList = new ArrayList<T>(this.list);

        for (ListIterator<T> iterator = tempList.listIterator(); iterator.hasNext(); ) {
            T element = iterator.next();

            if (!predicate.test(element)) {
                iterator.remove();
            }
        }

        return toImmutableList(tempList);
    }

    public <R> ImmutableList<R> map(Function<? super T, ? extends R> fn) {
        List<R> finalList = new ArrayList<R>();

        for (T t: this.list) {
            finalList.add(fn.apply(t));
        }

        return new ImmutableList<R>(finalList);
    }

    public ImmutableList<T> toImmutableList(List<T> list) {
        return new ImmutableList<T>(list);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
