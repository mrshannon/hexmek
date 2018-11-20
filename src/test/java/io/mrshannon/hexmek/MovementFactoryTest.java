package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MovementFactoryTest {

    private MovementFactory factory35;
    private MovementFactory factory46;

    @Before
    public void setUp() throws Exception {
        var map = (new MapLoader("default")).createMap();
        factory35 = new MovementFactory(map, 3);
        factory46 = new MovementFactory(map, 4);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createHalt() {
        Movement movement35 = factory35.createHalt();
        Movement movement46 = factory46.createHalt();
        assertEquals(0, movement35.getMovementPoints());
        assertEquals(0, movement46.getMovementPoints());
        assertThat(movement35, instanceOf(Halt.class));
        assertThat(movement46, instanceOf(Halt.class));
    }

    @Test
    public void createCruise() {
        Movement movement35 = factory35.createCruise();
        Movement movement46 = factory46.createCruise();
        assertEquals(3, movement35.getMovementPoints());
        assertEquals(4, movement46.getMovementPoints());
        assertThat(movement35, instanceOf(Cruise.class));
        assertThat(movement46, instanceOf(Cruise.class));
    }

    @Test
    public void createFlank() {
        Movement movement35 = factory35.createFlank();
        Movement movement46 = factory46.createFlank();
        assertEquals(5, movement35.getMovementPoints());
        assertEquals(6, movement46.getMovementPoints());
        assertThat(movement35, instanceOf(Flank.class));
        assertThat(movement46, instanceOf(Flank.class));
    }

    @Test
    public void getCruiseMovementPoints() {
        assertEquals(3, factory35.getCruiseMovementPoints());
        assertEquals(4, factory46.getCruiseMovementPoints());
    }

    @Test
    public void getFlankMovementPoints() {
        assertEquals(5, factory35.getFlankMovementPoints());
        assertEquals(6, factory46.getFlankMovementPoints());
    }

    @Test
    public void damageMobility() {
        factory35.damageMobility();
        assertEquals(2, factory35.getCruiseMovementPoints());
        assertEquals(3, factory35.getFlankMovementPoints());
        factory35.damageMobility();
        assertEquals(1, factory35.getCruiseMovementPoints());
        assertEquals(2, factory35.getFlankMovementPoints());
        factory35.damageMobility();
        assertEquals(0, factory35.getCruiseMovementPoints());
        assertEquals(0, factory35.getFlankMovementPoints());

        factory46.damageMobility();
        assertEquals(3, factory46.getCruiseMovementPoints());
        assertEquals(5, factory46.getFlankMovementPoints());
    }

    @Test
    public void destroyMobility() {
        factory35.destroyMobility();
        assertEquals(0, factory35.getCruiseMovementPoints());
        assertEquals(0, factory35.getFlankMovementPoints());

        factory46.destroyMobility();
        assertEquals(0, factory46.getCruiseMovementPoints());
        assertEquals(0, factory46.getFlankMovementPoints());
    }
}
