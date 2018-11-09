package io.mrshannon.hexmek;

/**
 * Generic originator interface to produce a memento to store the state of an object.
 */
public interface Originator {

    /**
     * Create a memento, saving the state of the originator object.
     *
     * @return memento with saved state
     */
    Memento save();

}
