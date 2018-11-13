package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeaponRangeTest {

    private WeaponRange range;
    private WeaponRange rangeMinimum;

    @Before
    public void setUp() throws Exception {
        range = new WeaponRange(3, 6, 9);
        rangeMinimum = new WeaponRange(6,  7,  14,  21);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMinimumRange() {
        assertEquals(0, range.getMinimumRange());
        assertEquals(6, rangeMinimum.getMinimumRange());
    }

    @Test
    public void getShortRange() {
        assertEquals(3, range.getShortRange());
        assertEquals(7, rangeMinimum.getShortRange());
    }

    @Test
    public void getMediumRange() {
        assertEquals(6, range.getMediumRange());
        assertEquals(14, rangeMinimum.getMediumRange());
    }

    @Test
    public void getLongRange() {
        assertEquals(9, range.getLongRange());
        assertEquals(21, rangeMinimum.getLongRange());
    }

    @Test
    public void outOfRange() {
        assertFalse(range.outOfRange(1));
        assertFalse(range.outOfRange(9));
        assertTrue(range.outOfRange(10));

        assertFalse(rangeMinimum.outOfRange(1));
        assertFalse(rangeMinimum.outOfRange(21));
        assertTrue(rangeMinimum.outOfRange(22));
    }

    @Test
    public void modifier() {
        for (int i = 1; i <= 3; ++i) {
            assertEquals(0, range.modifier(i));
        }
        for (int i = 4; i <= 6; ++i) {
            assertEquals(2, range.modifier(i));
        }
        for (int i = 7; i <= 9; ++i) {
            assertEquals(4, range.modifier(i));
        }
        assertEquals(Integer.MAX_VALUE/2, range.modifier(10));


        assertEquals(6, rangeMinimum.modifier(1));
        assertEquals(5, rangeMinimum.modifier(2));
        assertEquals(4, rangeMinimum.modifier(3));
        assertEquals(3, rangeMinimum.modifier(4));
        assertEquals(2, rangeMinimum.modifier(5));
        assertEquals(1, rangeMinimum.modifier(6));
        for (int i = 8; i <= 14; ++i) {
            assertEquals(2, rangeMinimum.modifier(i));
        }
        for (int i = 15; i <= 21; ++i) {
            assertEquals(4, rangeMinimum.modifier(i));
        }
        assertEquals(Integer.MAX_VALUE/2, rangeMinimum.modifier(22));
    }
}