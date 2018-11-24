package io.mrshannon.hexmek.controllers;

import java.io.IOException;

import static java.lang.System.exit;

/**
 * Exit controller, run this controller to exit the game.
 */
public class ExitController implements Controller {

    @Override
    public Controller run() throws IOException {
        exit(0);
        return null;
    }

}
