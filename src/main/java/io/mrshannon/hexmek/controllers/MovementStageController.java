package io.mrshannon.hexmek.controllers;

import io.mrshannon.hexmek.models.Movable;
import io.mrshannon.hexmek.models.Unit;
import io.mrshannon.hexmek.views.MessageView;

import java.io.IOException;
import java.util.List;

/**
 * Movement stage prompt controller.  This adds the 'move' command over the prompt controller.
 */
public class MovementStageController extends PromptController {

    /**
     * Construct a movement stage controller from a game controller.
     *
     * @param other game controller to copy the game state from
     */
    public MovementStageController(GameController other) {
        super(other);
        setRandomPlayer();
    }

    /**
     * Run the controller, displaying the prompt and dealing with a player entered command.
     *
     * @return a controller, based on the command the user enters
     * @throws IOException
     */
    @Override
    public Controller run() throws IOException {
        if (getUnits().stream().filter(Movable::canMove).filter(u -> !turnTaken(u)).count() <= 0) {
            return new CombatStageController(this);
        }
        while (getCurrentPlayer().getMovableUnits().stream().filter(u -> !turnTaken(u)).count() <= 0) {
            nextPlayer();
        }
        return super.run();
    }

    /**
     * Return the stage name.
     *
     * @return "movement"
     */
    @Override
    protected String getStage() {
        return "movement";
    }

    /**
     * Handle input from the player, in addition to those handled by {@code PromptController}
     *
     * @param args player input as a list of commands/arguments
     * @return next controller to run
     */
    @Override
    protected Controller handleInput(List<String> args) {
        if (args.get(0).equals("move")) {
            return handleMove(args);
        }
        return super.handleInput(args);
    }

    /**
     * Handle the 'move' command, starting a movement sequence.
     *
     * @param args arguments from the user, in the form ['move', 'id']
     * @return a {@code MovementController} if there are no errors, same {@code MovementStageController} otherwise
     */
    private Controller handleMove(List<String> args) {
        if (args.size() < 2) {
            (new MessageView("The 'move' command requires the ID of the unit to move.")).render();
            return this;
        }
        if (args.size() > 2) {
            (new MessageView("The 'move' command takes exactly 1 argument.")).render();
            return this;
        }
        if (args.get(1).length() != 1) {
            (new MessageView("The unit ID must be a single character.")).render();
            return this;
        }
        char unitId = args.get(1).toUpperCase().charAt(0);
        Unit unit;
        try {
            unit = getUnit(unitId);
        } catch (NoSuchUnitException e) {
            (new MessageView(String.format("Unit '%c' does not exist.", unitId))).render();
            return this;
        }
        if (turnTaken(unit)) {
            (new MessageView(String.format("Unit '%c' has already been moved.", unit.getId()))).render();
            return this;
        }
        if (!getCurrentPlayer().getUnits().contains(unit)) {
            (new MessageView(String.format("Unit '%c' does not belong to you.", unit.getId()))).render();
            return this;
        }
        if (!unit.canMove()) {
            (new MessageView(String.format("Unit '%c' cannot move.", unit.getId()))).render();
            return this;
        }
        return new MoveController(this, unit);
    }

}
