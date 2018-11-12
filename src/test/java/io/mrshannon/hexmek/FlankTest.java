package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class FlankTest {

    Flank flank;
    Flank flank1;
    Flank flank6;

    @Before
    public void setUp() throws Exception {
        flank = new Flank(100);
        flank1 = new Flank(1);
        flank6 = new Flank(6);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void moveForward() throws MovementPointsExhaustedException {
        assertEquals(new Hex(0, -1), flank6.moveForward(new Hex(0, 0), new North()));
        assertEquals(new Hex(1, 0), flank6.moveForward(new Hex(0, 0), new NorthEast()));
        assertEquals(new Hex(1, 1), flank6.moveForward(new Hex(0, 0), new SouthEast()));
        assertEquals(new Hex(0, 1), flank6.moveForward(new Hex(0, 0), new South()));
        assertEquals(new Hex(-1, 1), flank6.moveForward(new Hex(0, 0), new SouthWest()));
        assertEquals(new Hex(-1, 0), flank6.moveForward(new Hex(0, 0), new NorthWest()));
    }

    @Test(expected = MovementPointsExhaustedException.class)
    public void moveForwardOutOfMP() throws MovementPointsExhaustedException {
        assertEquals(new Hex(0, -1), flank1.moveForward(new Hex(0, 0), new North()));
        assertEquals(new Hex(1, 0), flank1.moveForward(new Hex(0, 0), new NorthEast()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void moveBackward() throws MovementPointsExhaustedException {
        Hex hex = flank.moveBackward(new Hex(0 ,0), new North());
    }

    @Test
    public void rotateRight() throws MovementPointsExhaustedException {
        assertEquals(new NorthEast(), flank6.rotateRight(new North()));
        assertEquals(new SouthEast(), flank6.rotateRight(new NorthEast()));
        assertEquals(new South(), flank6.rotateRight(new SouthEast()));
        assertEquals(new SouthWest(), flank6.rotateRight(new South()));
        assertEquals(new NorthWest(), flank6.rotateRight(new SouthWest()));
        assertEquals(new North(), flank6.rotateRight(new NorthWest()));
    }

    @Test(expected = MovementPointsExhaustedException.class)
    public void rotateRightOutOfMP() throws MovementPointsExhaustedException {
        assertEquals(new NorthEast(), flank1.rotateRight(new North()));
        assertEquals(new SouthEast(), flank1.rotateRight(new NorthEast()));
    }

    @Test
    public void rotateLeft() throws MovementPointsExhaustedException {
        assertEquals(new NorthWest(), flank6.rotateLeft(new North()));
        assertEquals(new SouthWest(), flank6.rotateLeft(new NorthWest()));
        assertEquals(new South(), flank6.rotateLeft(new SouthWest()));
        assertEquals(new SouthEast(), flank6.rotateLeft(new South()));
        assertEquals(new NorthEast(), flank6.rotateLeft(new SouthEast()));
        assertEquals(new North(), flank6.rotateLeft(new NorthEast()));
    }

    @Test(expected = MovementPointsExhaustedException.class)
    public void rotateLeftOutOfMP() throws MovementPointsExhaustedException {
        assertEquals(new NorthWest(), flank1.rotateLeft(new North()));
        assertEquals(new SouthWest(), flank1.rotateLeft(new NorthWest()));
    }

    @Test
    public void getMovementPoints() throws MovementPointsExhaustedException {
        assertEquals(1, flank1.getMovementPoints());
        assertEquals(6, flank6.getMovementPoints());
        flank6.moveForward(new Hex(0, 0), new North());
        assertEquals(5, flank6.getMovementPoints());
        flank6.rotateRight(new North());
        assertEquals(4, flank6.getMovementPoints());
        flank6.rotateLeft(new North());
        assertEquals(3, flank6.getMovementPoints());
    }

    @Test
    public void getToHitModifier() throws MovementPointsExhaustedException {
        Hex hex = new Hex(0, 0);
        Direction facing = new North();

        for (int i = 1; i <= 2; ++i) {
            hex = flank.moveForward(hex, facing);
            assertEquals(0, flank.getToHitModifier());
        }
        for (int i = 3; i <= 4; ++i) {
            hex = flank.moveForward(hex, facing);
            assertEquals(1, flank.getToHitModifier());
        }
        for (int i = 5; i <= 6; ++i) {
            hex = flank.moveForward(hex, facing);
            assertEquals(2, flank.getToHitModifier());
        }
        for (int i = 7; i <= 9; ++i) {
            hex = flank.moveForward(hex, facing);
            assertEquals(3, flank.getToHitModifier());
        }
        for (int i = 10; i <= 17; ++i) {
            hex = flank.moveForward(hex, facing);
            assertEquals(4, flank.getToHitModifier());
        }
        for (int i = 18; i <= 24; ++i) {
            hex = flank.moveForward(hex, facing);
            assertEquals(5, flank.getToHitModifier());
        }
        for (int i = 25; i < 100; ++i) {
            hex = flank.moveForward(hex, facing);
            assertEquals(6, flank.getToHitModifier());
        }
    }

    @Test
    public void getToHitModifier1() throws MovementPointsExhaustedException {
        Hex hex = new Hex(0, 0);
        Direction facing = new North();

        assertEquals(0, flank.getToHitModifier());
        hex = flank.moveForward(hex, facing); // moved 1
        assertEquals(0, flank.getToHitModifier());
        facing = flank.rotateRight(facing);
        assertEquals(0, flank.getToHitModifier());
        hex = flank.moveForward(hex, facing); // moved 2
        assertEquals(0, flank.getToHitModifier());
        facing = flank.rotateRight(facing);
        assertEquals(0, flank.getToHitModifier());
        hex = flank.moveForward(hex, facing); // moved 3
        assertEquals(1, flank.getToHitModifier());
        facing = flank.rotateRight(facing);
        assertEquals(1, flank.getToHitModifier());
        hex = flank.moveForward(hex, facing); // moved 4
        assertEquals(1, flank.getToHitModifier());
        facing = flank.rotateRight(facing);
        assertEquals(1, flank.getToHitModifier());
        hex = flank.moveForward(hex, facing); // moved 5
        assertEquals(2, flank.getToHitModifier());
        facing = flank.rotateLeft(facing);
        assertEquals(2, flank.getToHitModifier());
    }

    @Test
    public void getGunneryModifier() {
        assertEquals(6, flank.getGunneryModifier());
    }

    @Test
    public void testClone() throws MovementPointsExhaustedException {
        Hex hex = new Hex(0, 0);
        Direction facing = new North();

        for (int i = 1; i <= 8; ++i) {
            hex = flank.moveForward(hex, facing);
        }

        Object newObject = flank.clone();
        assertThat(newObject, instanceOf(Flank.class));
        Flank newFlank = (Flank) newObject;
        assertEquals(flank.getMovementPoints(), newFlank.getMovementPoints());
        assertEquals(flank.getToHitModifier(), newFlank.getToHitModifier());
        assertEquals(flank.getGunneryModifier(), newFlank.getGunneryModifier());
    }
}