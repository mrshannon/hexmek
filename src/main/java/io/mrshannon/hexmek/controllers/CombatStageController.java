package io.mrshannon.hexmek.controllers;

import io.mrshannon.hexmek.models.Unit;
import io.mrshannon.hexmek.views.MessageView;

import java.io.IOException;
import java.util.List;

/**
 * Movement stage prompt controller.  This adds the 'fire' command over the prompt controller.
 */
public class CombatStageController extends PromptController {

    /**
     * Construct a movement stage controller from a game controller.
     *
     * @param other game controller to copy the state from
     */
    public CombatStageController(GameController other) {
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
        if (getUnits().stream().filter(Unit::canFire).filter(u -> !turnTaken(u)).count() <= 0) {
            return new EndOfTurnController(this);
        }
        while (getCurrentPlayer().getMovableUnits().stream().filter(u -> !turnTaken(u)).count() <= 0) {
            nextPlayer();
        }
        return super.run();
    }

    /**
     * Return the stage name.
     *
     * @return "combat"
     */
    @Override
    protected String getStage() {
        return "combat";
    }

    /**
     * Handle input from the player, in addition to those handled by {@code PromptController}
     *
     * @param args player input as a list of commands/arguments
     * @return next controller to run
     */
    @Override
    protected Controller handleInput(List<String> args) {
        if (args.get(0).equals("fire")) {
            return handleFire(args);
        }
        return super.handleInput(args);
    }

    /**
     * Handle the 'fire' command, starting firing sequence.
     *
     * @param args arguments from the user, in the form ['fire', 'attacker', 'target']
     * @return a {@code FireController} if there are no errors, same {@code CombatStageController} otherwise
     */
    private Controller handleFire(List<String> args) {
        if (args.size() < 2) {
            (new MessageView("The 'fire' command requires the ID of the attacking unit.")).render();
            return this;
        }
        if (args.size() < 3) {
            (new MessageView("The 'fire' command requires the ID of the target unit.")).render();
            return this;
        }
        if (args.size() > 3) {
            (new MessageView("The 'fire' command takes exactly 2 arguments.")).render();
            return this;
        }
        if (args.get(1).length() != 1) {
            (new MessageView("The attacking unit ID must be a single character.")).render();
            return this;
        }
        if (args.get(2).length() != 1) {
            (new MessageView("The target unit ID must be a single character.")).render();
            return this;
        }
        char attackerId = args.get(1).toUpperCase().charAt(0);
        char targetId = args.get(2).toUpperCase().charAt(0);
        Unit attacker;
        Unit target;
        try {
            attacker = getUnit(attackerId);
        } catch (NoSuchUnitException e) {
            (new MessageView(String.format("Unit '%c' does not exist.", attackerId))).render();
            return this;
        }
        try {
            target = getUnit(targetId);
        } catch (NoSuchUnitException e) {
            (new MessageView(String.format("Unit '%c' does not exist.", targetId))).render();
            return this;
        }
        if (turnTaken(attacker)) {
            (new MessageView(String.format("Unit '%c' cannot fire again.", attacker.getId()))).render();
            return this;
        }
        if (!getCurrentPlayer().getUnits().contains(attacker)) {
            (new MessageView(String.format("Unit '%c' does not belong to you.", attacker.getId()))).render();
            return this;
        }
        if (getCurrentPlayer().getUnits().contains(target)) {
            (new MessageView("Friendly fire is not allowed.")).render();
            return this;
        }
        if (!attacker.canFire()) {
            (new MessageView(String.format("Unit '%c' cannot fire.", attacker.getId()))).render();
            return this;
        }
        return new FireController(this, attacker, target);
    }

}
