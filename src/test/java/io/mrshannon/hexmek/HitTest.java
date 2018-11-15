package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HitTest {

    private Weapon mockedWeapon;
    private Component mockedComponent;
    private Hit hit;

    @Before
    public void setUp() throws Exception {
        mockedWeapon = mock(Weapon.class);
        when(mockedWeapon.getType()).thenReturn("Mock Weapon");
        mockedComponent = mock(Component.class);
        when(mockedComponent.getType()).thenReturn("Mock Component");
        hit = new Hit(mockedWeapon, mockedComponent, 23);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getWeapon() {
        assertEquals(mockedWeapon, hit.getWeapon());
    }

    @Test
    public void getComponent() {
        assertEquals(mockedComponent, hit.getComponent());
    }

    @Test
    public void getDamage() {
        assertEquals(23, hit.getDamage());
    }

    @Test
    public void testToString() {
        assertEquals("Mock Weapon hit Mock Component for 23 damage.", hit.toString());
    }
}
