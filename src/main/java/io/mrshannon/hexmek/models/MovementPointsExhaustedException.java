package io.mrshannon.hexmek.models;

/**
 * An exception to be thrown when a unit has exhausted it's movement points.
 */
public class MovementPointsExhaustedException extends Exception {

    /**
     * Create exception.
     *
     * @param errorMessage error message
     */
    public MovementPointsExhaustedException(String errorMessage) {
        super(errorMessage);
    }

}
