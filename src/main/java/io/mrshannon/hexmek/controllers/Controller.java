package io.mrshannon.hexmek.controllers;

import java.io.IOException;

/**
 * Interface for all controllers.  This allows for the use of the state design pattern.
 */
public interface Controller {

    /**
     * Run the controller, and return the next controller.
     *
     * @return next controller to run
     */
    Controller run() throws IOException;

}
