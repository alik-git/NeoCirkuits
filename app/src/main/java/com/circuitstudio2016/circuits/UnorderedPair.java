package com.circuitstudio2016.circuits;

import java.util.ArrayList;

/**
 * Created by aliqk on 6/11/2017.
 */

public final class UnorderedPair<T> {
    private final T first;
    private final T second;

    public UnorderedPair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    @Override public int hashCode() {
        return first.hashCode() + second.hashCode();
    }

    @Override public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UnorderedPair)) {
            return false;
        }

        UnorderedPair<?> otherPair = (UnorderedPair<?>) other;
        return (first.equals(otherPair.first) && second.equals(otherPair.second))
                || (first.equals(otherPair.second) && second.equals(otherPair.first));
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public boolean inside(ArrayList<UnorderedPair<T>> list) {
        for (UnorderedPair<T> other: list) {
            if (this.equals(other)) {
                return true;
            }
        } return false;
    }
}
