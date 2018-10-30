package io.mrshannon.hexmek;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DieTest {

    @Test
    public void getValue() {
        for (int faces = 1; faces <= 100; faces++) {
            var die = new Die(faces);
            assertTrue(1 <= die.getValue() && die.getValue() <= faces);
            assertEquals(faces, die.getFaces());
        }
    }

    @Test
    public void getFaces() {
        for (int faces = 1; faces <= 100; faces++) {
            var die = new Die(faces);
            assertEquals(faces, die.getFaces());
        }
    }

    @Test
    public void roll() {
        for (int faces = 1; faces <= 100; faces++) {
            var set = new HashSet<Integer>();
            var truthSet = new HashSet<Integer>();
            var die = new Die(faces);

            for (int i = 1; i <= 1000; ++i) {
                die.roll();
                set.add(die.getValue());
            }
            for (int i = 1; i <= faces; ++i) {
                truthSet.add(i);
            }
            assertEquals(truthSet, set);
        }
    }

    @Test
    public void testToString() {
        for (int faces = 1; faces <= 100; faces++) {
            var die = new Die(faces);
            assertEquals(String.format("d%d = %d", faces, die.getValue()), die.toString());
        }
    }
}