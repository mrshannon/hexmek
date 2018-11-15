package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

public class WeaponTest {

    Weapon ac20;
    Weapon lrm15;

    Component component;

    HexMap map;

    Unit alpha;
    Unit beta;

    Unit charlie;
    Unit delta;

    @Before
    public void setUp() throws Exception {

        ac20 = new Weapon("Autocannon/20",
                new WeaponRange(3, 6, 9),
                new DirectFireStrategy(),
                new NormalFiringStrategy(20));
        lrm15 = new Weapon("LRM 15",
                new WeaponRange(6, 7, 14, 21),
                new IndirectFireStrategy(),
                new ClusterFiringStrategy(1, 15));

        map = (new MapLoader("default")).createMap();

        component = mock(Component.class);
        when(component.getName()).thenReturn("Mock Component");

        var ac20record = new ArrayList<DamageRecord>();
        ac20record.add(new Hit(ac20, component, 20));
        var lrm15record = new ArrayList<DamageRecord>();
        lrm15record.add(new Hit(lrm15, component, 1));

        alpha = mock(Unit.class);
        when(alpha.getHex()).thenReturn(new Hex(10, 4));
        when(alpha.getGunneryModifier()).thenReturn(4);
        when(alpha.getToHitModifier()).thenReturn(2);
        when(alpha.applyDamage(ac20, 20)).thenReturn(ac20record);
        when(alpha.applyDamage(lrm15, 1)).thenReturn(lrm15record);

        beta = mock(Unit.class);
        when(beta.getHex()).thenReturn(new Hex(10, 6));
        when(beta.getGunneryModifier()).thenReturn(5);
        when(beta.getToHitModifier()).thenReturn(1);
        when(beta.applyDamage(ac20, 20)).thenReturn(ac20record);
        when(beta.applyDamage(lrm15, 1)).thenReturn(lrm15record);

        charlie = mock(Unit.class);
        when(charlie.getHex()).thenReturn(new Hex(4, 4));
        when(charlie.getGunneryModifier()).thenReturn(0);
        when(charlie.getToHitModifier()).thenReturn(0);
        when(charlie.applyDamage(ac20, 20)).thenReturn(ac20record);
        when(charlie.applyDamage(lrm15, 1)).thenReturn(lrm15record);

        delta = mock(Unit.class);
        when(delta.getHex()).thenReturn(new Hex(4, 10));
        when(delta.getGunneryModifier()).thenReturn(0);
        when(delta.getToHitModifier()).thenReturn(0);
        when(delta.applyDamage(ac20, 20)).thenReturn(ac20record);
        when(delta.applyDamage(lrm15, 1)).thenReturn(lrm15record);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getType() {
        assertEquals("Autocannon/20", ac20.getType());
        assertEquals("LRM 15", lrm15.getType());
    }

    @Test
    public void getRange() {
        assertEquals(new WeaponRange(3, 6, 9), ac20.getRange());
        assertEquals(new WeaponRange(6, 7, 14, 21), lrm15.getRange());
    }

    @Test
    public void getDamage() {
        assertEquals(20, ac20.getDamage());
        assertEquals(15, lrm15.getDamage());
    }

    @Test
    public void modifier() {
        assertEquals(6, ac20.modifier(alpha, beta, map));
        assertTrue(ac20.modifier(beta, alpha, map) > 12);

        assertEquals(10, lrm15.modifier(alpha, beta, map));
        assertEquals(12, lrm15.modifier(beta, alpha, map));
    }

    @Test
    public void getHitChance() {
        assertEquals(0.7222222222222999, ac20.getHitChance(alpha, beta, map), 0.0001);
        assertEquals(0.0, ac20.getHitChance(beta, alpha, map), 0.0001);

        assertEquals(0.16666666666629993, lrm15.getHitChance(alpha, beta, map), 0.0001);
        assertEquals(0.027777777777399937, lrm15.getHitChance(beta, alpha, map), 0.0001);
    }

    @Test
    public void fire() {
        var records = ac20.fire(charlie, delta, map);
        assertEquals(1, records.size());
        assertThat(records.get(0), instanceOf(Hit.class));
        assertEquals("Autocannon/20 hit Mock Component for 20 damage.", records.get(0).toString());

        for (int i = 1; i <= 1000; ++i) {
            records = lrm15.fire(charlie, delta, map);
            assertTrue(records.size() >= 5);
            assertTrue(records.size() <= 15);
            for (var record : records) {
                assertEquals("LRM 15 hit Mock Component for 1 damage.", record.toString());
            }
        }
    }
}
