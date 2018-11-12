package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class HaltTest {

    private Halt halt;

    @Before
    public void setUp() throws Exception {
        halt = new Halt();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = UnsupportedOperationException.class)
    public void moveForward() throws MovementPointsExhaustedException {
        Hex hex = halt.moveForward(new Hex(0 ,0), new North());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void moveBackward() throws MovementPointsExhaustedException {
        Hex hex = halt.moveBackward(new Hex(0 ,0), new North());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void rotateRight() throws MovementPointsExhaustedException {
        Direction facing = halt.rotateRight(new North());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void rotateLeft() throws MovementPointsExhaustedException {
        Direction facing = halt.rotateLeft(new North());
    }

    @Test
    public void getMovementPoints() {
        assertEquals(0, halt.getMovementPoints());
    }

    @Test
    public void getToHitModifier() {
        assertEquals(0, halt.getToHitModifier());
    }

    @Test
    public void getGunneryModifier() {
        assertEquals(4, halt.getGunneryModifier());
    }

    @Test
    public void testClone() {
        Object newObject = halt.clone();
        assertThat(newObject, instanceOf(Halt.class));
        Halt newHalt = (Halt) newObject;
        assertEquals(0, halt.getToHitModifier());
        assertEquals(4, halt.getGunneryModifier());
    }

}
