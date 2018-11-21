package io.mrshannon.hexmek;

import io.mrshannon.hexmek.models.Player;
import io.mrshannon.hexmek.models.Unit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PlayerTest {

    Unit mockUnitA;
    Unit mockUnitB;
    Player playerA;
    Player playerB;

    @Before
    public void setUp() throws Exception {
        mockUnitA = mock(Unit.class);
        when(mockUnitA.isDestroyed()).thenReturn(false);
        mockUnitB = mock(Unit.class);
        when(mockUnitB.isDestroyed()).thenReturn(true);
        playerA = new Player("Player A");
        playerA.addUnit(mockUnitA);
        playerA.addUnit(mockUnitB);
        playerB = new Player("Player B");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addUnit() {
    }

    @Test
    public void getName() {
        assertEquals("Player A", playerA.getName());
        assertEquals("Player B", playerB.getName());
    }

    @Test
    public void getUnits() {
        assertEquals(2, playerA.getUnits().size());
        assertTrue(playerA.getUnits().contains(mockUnitA));
        assertTrue(playerA.getUnits().contains(mockUnitB));
        assertEquals(0, playerB.getUnits().size());
        assertFalse(playerB.getUnits().contains(mockUnitA));
        assertFalse(playerB.getUnits().contains(mockUnitB));
    }

    @Test
    public void getAliveUnits() {
        assertEquals(1, playerA.getAliveUnits().size());
        assertTrue(playerA.getAliveUnits().contains(mockUnitA));
        assertFalse(playerA.getAliveUnits().contains(mockUnitB));
        assertEquals(0, playerB.getAliveUnits().size());
        assertFalse(playerB.getAliveUnits().contains(mockUnitA));
        assertFalse(playerB.getAliveUnits().contains(mockUnitB));
    }

    @Test
    public void getDestroyedUnits() {
        assertEquals(1, playerA.getDestroyedUnits().size());
        assertFalse(playerA.getDestroyedUnits().contains(mockUnitA));
        assertTrue(playerA.getDestroyedUnits().contains(mockUnitB));
        assertEquals(0, playerB.getDestroyedUnits().size());
        assertFalse(playerB.getDestroyedUnits().contains(mockUnitA));
        assertFalse(playerB.getDestroyedUnits().contains(mockUnitB));
    }

    @Test
    public void inGame() {
        assertTrue(playerA.inGame());
        assertFalse(playerB.inGame());
        playerB.addUnit(mockUnitB);
        assertFalse(playerB.inGame());
        playerB.addUnit(mockUnitA);
        assertTrue(playerB.inGame());
    }
}