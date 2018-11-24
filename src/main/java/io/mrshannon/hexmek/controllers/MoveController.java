package io.mrshannon.hexmek.controllers;

import io.mrshannon.hexmek.models.Memento;
import io.mrshannon.hexmek.models.MovementPointsExhaustedException;
import io.mrshannon.hexmek.models.Unit;
import io.mrshannon.hexmek.views.MapView;
import io.mrshannon.hexmek.views.MessageView;
import io.mrshannon.hexmek.views.MovementSelectView;
import io.mrshannon.hexmek.views.MovementTypeSelectView;

import java.io.IOException;

/**
 * Move controller, allows the player to move a single unit.
 */
public class MoveController extends GameController {

    private MovementStageController returnTo;
    private Unit unit;
    private Memento unitMemento;

    /**
     * Construct a move controller.
     *
     * @param other {@code MovementStageController} to copy the game state from and return to after movement is over
     * @param unit unit to move
     */
    public MoveController(MovementStageController other, Unit unit) {
        super(other);
        this.unit = unit;
        this.returnTo = other;
    }

    /**
     * Run the controller, this displays the map and movement status and then asks the player which move they wish to
     * make.
     *
     * @return this controller until the movement is complete, then the original stage controller
     * @throws IOException
     */
    @Override
    public Controller run() throws IOException {
        unitMemento = unit.save();
        var view = new MovementTypeSelectView(unit);
        String type;
        do {
            view.render();
            type = readLine();
        } while (!applyMovementType(type));
        return returnTo;
    }

    /**
     * Start the proper sequence for the given movement type.
     *
     * Allowed types are:
     *
     * <ul>
     *     <li>halt</li>
     *     <li>walk/cruise</li>
     *     <li>run/flank</li>
     * </ul>
     *
     * @param type type of movement to start
     * @return true if the movement type is valid, false otherwise.
     * @throws IOException
     */
    private boolean applyMovementType(String type) throws IOException {
        switch (type.toLowerCase()) {
            case "halt":
                unit.halt();
                break;
            case "walk":
            case "cruise":
                unit.cruise();
                break;
            case "run":
            case "flank":
                unit.flank();
                break;
            default:
                (new MessageView(String.format("Unknown movement type '%s'.", type))).render();
                return false;
        }
        finishMovement();
        return true;
    }

    /**
     * Ask player for each step of the movement and handle the movement.
     *
     * @throws IOException
     */
    private void finishMovement() throws IOException {
        while (unit.getMovementPoints() > 0) {
            (new MapView(getMap(), getUnits())).render();
            (new MovementSelectView(unit)).render();
            var command = readLine();
            try {
                switch (command) {
                    case "forward":
                        unit.moveForward();
                        break;
                    case "back":
                        unit.moveBackward();
                        break;
                    case "left":
                        unit.rotateLeft();
                        break;
                    case "right":
                        unit.rotateRight();
                        break;
                    case "end":
                        returnTo.takeTurn(unit);
                        return;
                    case "reset":
                        unitMemento.restore();
                        return;
                    default:
                        (new MessageView(String.format("Unknown movement command '%s'.", command))).render();
                }
            } catch (UnsupportedOperationException e) {
                (new MessageView(String.format("'%s' is not supported for current movement type", command))).render();
            } catch (IndexOutOfBoundsException e) {
                (new MessageView("Cannot move out out bounds.")).render();
            } catch (MovementPointsExhaustedException e) {
                break;
            }
        }
        returnTo.takeTurn(unit);
    }

}
