package io.mrshannon.hexmek;

import io.mrshannon.hexmek.models.Miss;
import io.mrshannon.hexmek.models.Weapon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MissTest {

    private Weapon mockedWeapon;
    private Miss miss;

    @Before
    public void setUp() throws Exception {
        mockedWeapon = mock(Weapon.class);
        when(mockedWeapon.getType()).thenReturn("Mock Weapon");
        miss = new Miss(mockedWeapon);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getWeapon() {
        assertEquals(mockedWeapon, miss.getWeapon());
    }

    @Test
    public void getComponent() {
        assertEquals(null, miss.getComponent());
    }

    @Test
    public void getDamage() {
        assertEquals(0, miss.getDamage());
    }

    @Test
    public void testToString() {
        assertEquals("Mock Weapon missed.", miss.toString());
    }
}