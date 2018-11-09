package io.mrshannon.hexmek;

/**
 * Generic memento interface to store and restore the state of an originator object.
 */
public interface Memento {

    /**
     * Restore the object this memento is associated with.
     */
    void restore();
}
