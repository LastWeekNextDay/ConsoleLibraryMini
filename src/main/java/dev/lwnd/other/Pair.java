package dev.lwnd.other;

/**
 * A generic class representing a pair of values.
 *
 * @param <T> the type of the first value
 * @param <U> the type of the second value
 */
public class Pair<T, U> {
    private T first;
    private U second;

    /**
     * Constructs a new Pair object with the specified values.
     *
     * @param first  the first value of the pair
     * @param second the second value of the pair
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first value of the pair.
     *
     * @return the first value
     */
    public T getFirst() {
        return first;
    }

    /**
     * Sets the first value of the pair.
     *
     * @param first the new value for the first value
     */
    public void setFirst(T first) {
        this.first = first;
    }

    /**
     * Returns the second value of the pair.
     *
     * @return the second value
     */
    public U getSecond() {
        return second;
    }

    /**
     * Sets the second value of the pair.
     *
     * @param second the new value for the second value
     */
    public void setSecond(U second) {
        this.second = second;
    }
}
