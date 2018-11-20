package io.mrshannon.hexmek;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DestroyedTest {

    private Weapon mockedWeapon;
    private Component mockedComponent;
    private Destroyed destroyed;

    @Before
    public void setUp() throws Exception {
        mockedWeapon = mock(Weapon.class);
        when(mockedWeapon.getType()).thenReturn("Mock Weapon");
        mockedComponent = mock(Component.class);
        when(mockedComponent.getType()).thenReturn("Mock Component");
        destroyed = new Destroyed(mockedWeapon, mockedComponent, 23);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getWeapon() {
        assertEquals(mockedWeapon, destroyed.getWeapon());
    }

    @Test
    public void getComponent() {
        assertEquals(mockedComponent, destroyed.getComponent());
    }

    @Test
    public void getDamage() {
        assertEquals(23, destroyed.getDamage());
    }

    @Test
    public void testToString() {
        assertEquals("Mock Weapon hit Mock Component for 23 damage.\nMock Component destroyed!",
                destroyed.toString());
    }

}
