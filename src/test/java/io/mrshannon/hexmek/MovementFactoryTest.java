package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MovementFactoryTest {

    private MovementFactory factory35;
    private MovementFactory factory47;

    @Before
    public void setUp() throws Exception {
        factory35 = new MovementFactory(3, 5);
        factory47 = new MovementFactory(4, 7);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createHalt() {
        Movement movement35 = factory35.createHalt();
        Movement movement47 = factory47.createHalt();
        assertEquals(0, movement35.getMovementPoints());
        assertEquals(0, movement47.getMovementPoints());
        assertThat(movement35, instanceOf(Halt.class));
        assertThat(movement47, instanceOf(Halt.class));
    }

    @Test
    public void createCruise() {
        Movement movement35 = factory35.createCruise();
        Movement movement47 = factory47.createCruise();
        assertEquals(3, movement35.getMovementPoints());
        assertEquals(4, movement47.getMovementPoints());
        assertThat(movement35, instanceOf(Cruise.class));
        assertThat(movement47, instanceOf(Cruise.class));
    }

    @Test
    public void createFlank() {
        Movement movement35 = factory35.createFlank();
        Movement movement47 = factory47.createFlank();
        assertEquals(5, movement35.getMovementPoints());
        assertEquals(7, movement47.getMovementPoints());
        assertThat(movement35, instanceOf(Flank.class));
        assertThat(movement47, instanceOf(Flank.class));
    }
}
