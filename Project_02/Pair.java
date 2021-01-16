package cs2030.simulator;

/**
 * The Pair class is a helper class that returns a Pair of 2 different objects.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 2
 */
public class Pair<T, U> {
    private final T t;
    private final U u;

    /**
     * Initialise a new Pair.
     * @param t First item in the pair
     * @param u Second item in the pair
     */
    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    /**
     * Returns a new Pair.
     * @param   t       First item in the pair
     * @param   u       Second item in the pair
     * @return  Pair    Returns a new Pair.
     */
    public static <T, U> Pair<T, U> of(T t, U u) {
        return new Pair<T,U>(t, u);
    }

    /**
     * Returns the first part of the Pair.
     * @return T Returns the first part of the Pair.
     */
    public T first() {
        return this.t;
    }

    /**
     * Returns the second part of the Pair.
     * @return U Returns the second part of the Pair.
     */
    public U second() {
        return this.u;
    }
}
