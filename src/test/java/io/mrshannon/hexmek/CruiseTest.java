package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CruiseTest {

    private Cruise cruise;
    private Cruise cruise1;
    private Cruise cruise6;

    @Before
    public void setUp() throws Exception {
        cruise = new Cruise(100);
        cruise1 = new Cruise(1);
        cruise6 = new Cruise(6);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void moveForward() throws MovementPointsExhaustedException {
        assertEquals(new Hex(0, -1), cruise6.moveForward(new Hex(0, 0), new North()));
        assertEquals(new Hex(1, 0), cruise6.moveForward(new Hex(0, 0), new NorthEast()));
        assertEquals(new Hex(1, 1), cruise6.moveForward(new Hex(0, 0), new SouthEast()));
        assertEquals(new Hex(0, 1), cruise6.moveForward(new Hex(0, 0), new South()));
        assertEquals(new Hex(-1, 1), cruise6.moveForward(new Hex(0, 0), new SouthWest()));
        assertEquals(new Hex(-1, 0), cruise6.moveForward(new Hex(0, 0), new NorthWest()));
    }

    @Test(expected = MovementPointsExhaustedException.class)
    public void moveForwardOutOfMP() throws MovementPointsExhaustedException {
        assertEquals(new Hex(0, -1), cruise1.moveForward(new Hex(0, 0), new North()));
        assertEquals(new Hex(1, 0), cruise1.moveForward(new Hex(0, 0), new NorthEast()));
    }

    @Test
    public void moveBackward() throws MovementPointsExhaustedException {
        assertEquals(new Hex(0, 1), cruise6.moveBackward(new Hex(0, 0), new North()));
        assertEquals(new Hex(-1, 1), cruise6.moveBackward(new Hex(0, 0), new NorthEast()));
        assertEquals(new Hex(-1, 0), cruise6.moveBackward(new Hex(0, 0), new SouthEast()));
        assertEquals(new Hex(0, -1), cruise6.moveBackward(new Hex(0, 0), new South()));
        assertEquals(new Hex(1, 0), cruise6.moveBackward(new Hex(0, 0), new SouthWest()));
        assertEquals(new Hex(1, 1), cruise6.moveBackward(new Hex(0, 0), new NorthWest()));
    }

    @Test(expected = MovementPointsExhaustedException.class)
    public void moveBackwardOutOfMP() throws MovementPointsExhaustedException {
        assertEquals(new Hex(0, 1), cruise1.moveBackward(new Hex(0, 0), new North()));
        assertEquals(new Hex(-1, 1), cruise1.moveBackward(new Hex(0, 0), new NorthEast()));
    }

    @Test
    public void rotateRight() throws MovementPointsExhaustedException {
        assertEquals(new NorthEast(), cruise6.rotateRight(new North()));
        assertEquals(new SouthEast(), cruise6.rotateRight(new NorthEast()));
        assertEquals(new South(), cruise6.rotateRight(new SouthEast()));
        assertEquals(new SouthWest(), cruise6.rotateRight(new South()));
        assertEquals(new NorthWest(), cruise6.rotateRight(new SouthWest()));
        assertEquals(new North(), cruise6.rotateRight(new NorthWest()));
    }

    @Test(expected = MovementPointsExhaustedException.class)
    public void rotateRightOutOfMP() throws MovementPointsExhaustedException {
        assertEquals(new NorthEast(), cruise1.rotateRight(new North()));
        assertEquals(new SouthEast(), cruise1.rotateRight(new NorthEast()));
    }

    @Test
    public void rotateLeft() throws MovementPointsExhaustedException {
        assertEquals(new NorthWest(), cruise6.rotateLeft(new North()));
        assertEquals(new SouthWest(), cruise6.rotateLeft(new NorthWest()));
        assertEquals(new South(), cruise6.rotateLeft(new SouthWest()));
        assertEquals(new SouthEast(), cruise6.rotateLeft(new South()));
        assertEquals(new NorthEast(), cruise6.rotateLeft(new SouthEast()));
        assertEquals(new North(), cruise6.rotateLeft(new NorthEast()));
    }

    @Test(expected = MovementPointsExhaustedException.class)
    public void rotateLeftOutOfMP() throws MovementPointsExhaustedException {
        assertEquals(new NorthWest(), cruise1.rotateLeft(new North()));
        assertEquals(new SouthWest(), cruise1.rotateLeft(new NorthWest()));
    }

    @Test
    public void getMovementPoints() throws MovementPointsExhaustedException {
        assertEquals(1, cruise1.getMovementPoints());
        assertEquals(6, cruise6.getMovementPoints());
        cruise6.moveForward(new Hex(0, 0), new North());
        assertEquals(5, cruise6.getMovementPoints());
        cruise6.moveBackward(new Hex(0, 0), new North());
        assertEquals(4, cruise6.getMovementPoints());
        cruise6.rotateRight(new North());
        assertEquals(3, cruise6.getMovementPoints());
        cruise6.rotateLeft(new North());
        assertEquals(2, cruise6.getMovementPoints());
    }

    @Test
    public void getToHitModifier() throws MovementPointsExhaustedException {
        Hex hex = new Hex(0, 0);
        Direction facing = new North();

        for (int i = 1; i <= 2; ++i) {
            hex = cruise.moveForward(hex, facing);
            assertEquals(0, cruise.getToHitModifier());
        }
        for (int i = 3; i <= 4; ++i) {
            hex = cruise.moveForward(hex, facing);
            assertEquals(1, cruise.getToHitModifier());
        }
        for (int i = 5; i <= 6; ++i) {
            hex = cruise.moveForward(hex, facing);
            assertEquals(2, cruise.getToHitModifier());
        }
        for (int i = 7; i <= 9; ++i) {
            hex = cruise.moveForward(hex, facing);
            assertEquals(3, cruise.getToHitModifier());
        }
        for (int i = 10; i <= 17; ++i) {
            hex = cruise.moveForward(hex, facing);
            assertEquals(4, cruise.getToHitModifier());
        }
        for (int i = 18; i <= 24; ++i) {
            hex = cruise.moveForward(hex, facing);
            assertEquals(5, cruise.getToHitModifier());
        }
        for (int i = 25; i < 100; ++i) {
            hex = cruise.moveForward(hex, facing);
            assertEquals(6, cruise.getToHitModifier());
        }
    }

    @Test
    public void getToHitModifier1() throws MovementPointsExhaustedException {
        Hex hex = new Hex(0, 0);
        Direction facing = new North();

        assertEquals(0, cruise.getToHitModifier());
        hex = cruise.moveForward(hex, facing); // moved 1
        assertEquals(0, cruise.getToHitModifier());
        facing = cruise.rotateRight(facing);
        assertEquals(0, cruise.getToHitModifier());
        hex = cruise.moveForward(hex, facing); // moved 2
        assertEquals(0, cruise.getToHitModifier());
        facing = cruise.rotateRight(facing);
        assertEquals(0, cruise.getToHitModifier());
        hex = cruise.moveForward(hex, facing); // moved 3
        assertEquals(1, cruise.getToHitModifier());
        facing = cruise.rotateRight(facing);
        assertEquals(1, cruise.getToHitModifier());
        hex = cruise.moveForward(hex, facing); // moved 4
        assertEquals(1, cruise.getToHitModifier());
        facing = cruise.rotateRight(facing);
        assertEquals(1, cruise.getToHitModifier());
        hex = cruise.moveForward(hex, facing); // moved 5
        assertEquals(2, cruise.getToHitModifier());
        facing = cruise.rotateLeft(facing);
        assertEquals(2, cruise.getToHitModifier());
        hex = cruise.moveBackward(hex, facing); // moved 6
        assertEquals(2, cruise.getToHitModifier());

        hex = new Hex(0, 0);
        hex = cruise6.moveBackward(hex, new SouthWest());
        assertEquals(0, cruise6.getToHitModifier());
        hex = cruise6.moveBackward(hex, new SouthWest());
        assertEquals(0, cruise6.getToHitModifier());
        hex = cruise6.moveBackward(hex, new SouthWest());
        assertEquals(1, cruise6.getToHitModifier());
        hex = cruise6.moveBackward(hex, new SouthWest());
        assertEquals(1, cruise6.getToHitModifier());
        hex = cruise6.moveForward(hex, new SouthWest());
        assertEquals(1, cruise6.getToHitModifier());
        hex = cruise6.moveForward(hex, new SouthWest());
        assertEquals(1, cruise6.getToHitModifier());
    }

    @Test
    public void getGunneryModifier() {
        assertEquals(5, cruise.getGunneryModifier());
    }

    @Test
    public void testClone() throws MovementPointsExhaustedException {
        Hex hex = new Hex(0, 0);
        Direction facing = new North();

        for (int i = 1; i <= 8; ++i) {
            hex = cruise.moveForward(hex, facing);
        }

        Object newObject = cruise.clone();
        assertThat(newObject, instanceOf(Cruise.class));
        Cruise newCruise = (Cruise) newObject;
        assertEquals(cruise.getMovementPoints(), newCruise.getMovementPoints());
        assertEquals(cruise.getToHitModifier(), newCruise.getToHitModifier());
        assertEquals(cruise.getGunneryModifier(), newCruise.getGunneryModifier());
    }

}
