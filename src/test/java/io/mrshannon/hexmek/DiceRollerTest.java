package io.mrshannon.hexmek;

import io.mrshannon.hexmek.models.DiceRoller;
import io.mrshannon.hexmek.models.Die;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceRollerTest {

    private Die d2;
    private Die d4;
    private Die d6;
    private Die d8;

    private DiceRoller dice1D8;
    private DiceRoller dice2D6;
    private DiceRoller dice5D6;
    private DiceRoller diceAll;

    @Before
    public void setUp() throws Exception {
        d2 = new Die(2);
        d4 = new Die(4);
        d6 = new Die(6);
        d8 = new Die(8);

        dice1D8 = new DiceRoller(d8);
        dice2D6 = new DiceRoller(d6, d6);
        dice5D6 = new DiceRoller(d6, d6, d6, d6, d6);
        diceAll = new DiceRoller(d2, d4, d6, d8);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void roll() {
    }

    @Test
    public void getTotal() {
        assertEquals(d8.getValue(), dice1D8.getTotal());
        assertEquals(d6.getValue()*2, dice2D6.getTotal());
        assertEquals(d6.getValue()*5, dice5D6.getTotal());
        assertEquals(d2.getValue() + d4.getValue() + d6.getValue() + d8.getValue(), diceAll.getTotal());
    }

    @Test
    public void getMin() {
        assertEquals(1, dice1D8.getMin());
        assertEquals(2, dice2D6.getMin());
        assertEquals(5, dice5D6.getMin());
        assertEquals(4, diceAll.getMin());
    }

    @Test
    public void getMax() {
        assertEquals(8, dice1D8.getMax());
        assertEquals(2*6, dice2D6.getMax());
        assertEquals(5*6, dice5D6.getMax());
        assertEquals(20, diceAll.getMax());
    }

    @Test
    public void probability() {
        assertEquals(0.0, dice1D8.probability(0), 0.0001);
        assertEquals(1.0/8.0, dice1D8.probability(1), 0.0001);
        assertEquals(1.0/8.0, dice1D8.probability(2), 0.0001);
        assertEquals(1.0/8.0, dice1D8.probability(3), 0.0001);
        assertEquals(1.0/8.0, dice1D8.probability(4), 0.0001);
        assertEquals(1.0/8.0, dice1D8.probability(5), 0.0001);
        assertEquals(1.0/8.0, dice1D8.probability(6), 0.0001);
        assertEquals(1.0/8.0, dice1D8.probability(7), 0.0001);
        assertEquals(1.0/8.0, dice1D8.probability(8), 0.0001);
        assertEquals(0.0, dice1D8.probability(9), 0.0001);

        assertEquals(0.0, dice2D6.probability(1), 0.0001);
        assertEquals(0.0277777777778, dice2D6.probability(2), 0.0001);
        assertEquals(0.0555555555556, dice2D6.probability(3), 0.0001);
        assertEquals(0.0833333333333, dice2D6.probability(4), 0.0001);
        assertEquals(0.111111111111, dice2D6.probability(5), 0.0001);
        assertEquals(0.138888888889, dice2D6.probability(6), 0.0001);
        assertEquals(0.166666666667, dice2D6.probability(7), 0.0001);
        assertEquals(0.138888888889, dice2D6.probability(8), 0.0001);
        assertEquals(0.111111111111, dice2D6.probability(9), 0.0001);
        assertEquals(0.0833333333333, dice2D6.probability(10), 0.0001);
        assertEquals(0.0555555555556, dice2D6.probability(11), 0.0001);
        assertEquals(0.0277777777778, dice2D6.probability(12), 0.0001);
        assertEquals(0.0, dice2D6.probability(13), 0.0001);

        assertEquals(0.0, dice5D6.probability(4), 0.0001);
        assertEquals(0.000128600823045, dice5D6.probability(5), 0.0001);
        assertEquals(0.000643004115226, dice5D6.probability(6), 0.0001);
        assertEquals(0.00192901234568, dice5D6.probability(7), 0.0001);
        assertEquals(0.00450102880658, dice5D6.probability(8), 0.0001);
        assertEquals(0.00900205761317, dice5D6.probability(9), 0.0001);
        assertEquals(0.0162037037037, dice5D6.probability(10), 0.0001);
        assertEquals(0.0263631687243, dice5D6.probability(11), 0.0001);
        assertEquals(0.0392232510288, dice5D6.probability(12), 0.0001);
        assertEquals(0.054012345679, dice5D6.probability(13), 0.0001);
        assertEquals(0.0694444444444, dice5D6.probability(14), 0.0001);
        assertEquals(0.0837191358025, dice5D6.probability(15), 0.0001);
        assertEquals(0.0945216049383, dice5D6.probability(16), 0.0001);
        assertEquals(0.100308641975, dice5D6.probability(17), 0.0001);
        assertEquals(0.100308641975, dice5D6.probability(18), 0.0001);
        assertEquals(0.0945216049383, dice5D6.probability(19), 0.0001);
        assertEquals(0.0837191358025, dice5D6.probability(20), 0.0001);
        assertEquals(0.0694444444444, dice5D6.probability(21), 0.0001);
        assertEquals(0.054012345679, dice5D6.probability(22), 0.0001);
        assertEquals(0.0392232510288, dice5D6.probability(23), 0.0001);
        assertEquals(0.0263631687243, dice5D6.probability(24), 0.0001);
        assertEquals(0.0162037037037, dice5D6.probability(25), 0.0001);
        assertEquals(0.00900205761317, dice5D6.probability(26), 0.0001);
        assertEquals(0.00450102880658, dice5D6.probability(27), 0.0001);
        assertEquals(0.00192901234568, dice5D6.probability(28), 0.0001);
        assertEquals(0.000643004115226, dice5D6.probability(29), 0.0001);
        assertEquals(0.000128600823045, dice5D6.probability(30), 0.0001);
        assertEquals(0.0, dice5D6.probability(31), 0.0001);

        assertEquals(0.0, diceAll.probability(3), 0.0001);
        assertEquals(0.00260416666667, diceAll.probability(4), 0.0001);
        assertEquals(0.0104166666667, diceAll.probability(5), 0.0001);
        assertEquals(0.0234375, diceAll.probability(6), 0.0001);
        assertEquals(0.0416666666667, diceAll.probability(7), 0.0001);
        assertEquals(0.0625, diceAll.probability(8), 0.0001);
        assertEquals(0.0833333333333, diceAll.probability(9), 0.0001);
        assertEquals(0.1015625, diceAll.probability(10), 0.0001);
        assertEquals(0.114583333333, diceAll.probability(11), 0.0001);
        assertEquals(0.119791666667, diceAll.probability(12), 0.0001);
        assertEquals(0.114583333333, diceAll.probability(13), 0.0001);
        assertEquals(0.1015625, diceAll.probability(14), 0.0001);
        assertEquals(0.0833333333333, diceAll.probability(15), 0.0001);
        assertEquals(0.0625, diceAll.probability(16), 0.0001);
        assertEquals(0.0416666666667, diceAll.probability(17), 0.0001);
        assertEquals(0.0234375, diceAll.probability(18), 0.0001);
        assertEquals(0.0104166666667, diceAll.probability(19), 0.0001);
        assertEquals(0.00260416666667, diceAll.probability(20), 0.0001);
        assertEquals(0.0, diceAll.probability(21), 0.0001);
    }

    @Test
    public void atLeastProbability() {
        assertEquals(1.0, dice2D6.atLeastProbability(2), 0.0001);
        assertEquals(0.9722222222222, dice2D6.atLeastProbability(3), 0.0001);
        assertEquals(0.9166666666665999, dice2D6.atLeastProbability(4), 0.0001);
        assertEquals(0.8333333333332999, dice2D6.atLeastProbability(5), 0.0001);
        assertEquals(0.7222222222222999, dice2D6.atLeastProbability(6), 0.0001);
        assertEquals(0.5833333333332999, dice2D6.atLeastProbability(7), 0.0001);
        assertEquals(0.4166666666662999, dice2D6.atLeastProbability(8), 0.0001);
        assertEquals(0.27777777777729995, dice2D6.atLeastProbability(9), 0.0001);
        assertEquals(0.16666666666629993, dice2D6.atLeastProbability(10), 0.0001);
        assertEquals(0.08333333333299993, dice2D6.atLeastProbability(11), 0.0001);
        assertEquals(0.027777777777399937, dice2D6.atLeastProbability(12), 0.0001);
    }

    @Test
    public void atMostProbability() {
        assertEquals(0.0277777777778, dice2D6.atMostProbability(2), 0.0001);
        assertEquals(0.0833333333334, dice2D6.atMostProbability(3), 0.0001);
        assertEquals(0.1666666666667, dice2D6.atMostProbability(4), 0.0001);
        assertEquals(0.27777777777769998, dice2D6.atMostProbability(5), 0.0001);
        assertEquals(0.4166666666667, dice2D6.atMostProbability(6), 0.0001);
        assertEquals(0.5833333333336999, dice2D6.atMostProbability(7), 0.0001);
        assertEquals(0.7222222222226999, dice2D6.atMostProbability(8), 0.0001);
        assertEquals(0.8333333333336999, dice2D6.atMostProbability(9), 0.0001);
        assertEquals(0.916666666667, dice2D6.atMostProbability(10), 0.0001);
        assertEquals(0.9722222222226, dice2D6.atMostProbability(11), 0.0001);
        assertEquals(1.0, dice2D6.atMostProbability(12), 0.0001);
    }

    @Test
    public void iterator() {
    }

    @Test
    public void size() {
        assertEquals(1, dice1D8.size());
        assertEquals(2, dice2D6.size());
        assertEquals(5, dice5D6.size());
        assertEquals(4, diceAll.size());
    }

    @Test
    public void testToString() {
    }
}