package io.mrshannon.hexmek.controllers;

/**
 * An exception that is thrown when a unit lookup by ID fails.
 */
public class NoSuchUnitException extends Exception {

    /**
     * Construct the exception.
     *
     * @param Id id of the unit that does not exist
     */
    public NoSuchUnitException(char Id) {
        super(String.format("There is no unit with ID '%c'.", Id));
    }

}
