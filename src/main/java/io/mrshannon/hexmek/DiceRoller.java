package io.mrshannon.hexmek;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import java.util.*;

/**
 * A dice roller is a collection of dice that can be rolled and the total computed.
 */
public class DiceRoller extends AbstractCollection<Die> {

    private Collection<Die> dice;
    private HashMap<Integer, Double> probabilities;

    /**
     * Construct a dice roller from 1 or more dice.
     *
     * @param die zero or more dice to use for the roller.
     */
    public DiceRoller(Die... die) {
        dice = Arrays.asList(die);

    }

    /**
     * Roll all the dice, generating a new random total.
     */
    public void roll() {
        for (var die : this) {
            die.roll();
        }
    }

    /**
     * Get total of all dice.
     *
     * @return total value of all dice
     */
    public int getTotal() {
        int total = 0;
        for (Die die : dice) {
            total += die.getValue();
        }
        return total;
    }

    /**
     * Get the minimum value the dice roller can have.
     *
     * @return minimum possible total
     */
    public int getMin() {
        return size();
    }

    /**
     * Get the maximum value the dice roller can have.
     *
     * @return maximum possible total
     */
    public int getMax() {
        int sum = 0;
        for (var die : this) {
            sum += die.getFaces();
        }
        return sum;
    }

    /**
     * Calculate the probability that a roll will generate a given value.
     *
     * @param value the value to compute the probability for
     * @return probability that the given value will be the total on the next roll
     */
    // https://math.stackexchange.com/a/20197
    public double probability(int value) {
        // initialize probability map
        if (probabilities == null) {
            probabilities = new HashMap<>();
            PolynomialFunction generatorFunction = null;

            // compute probabilities via generator function
            for (var die : this) {
                double coefficients[] = new double[die.getFaces() + 1];
                Arrays.fill(coefficients, 1.0/die.getFaces());
                coefficients[0] = 0;
                if (generatorFunction == null) {
                    generatorFunction = new PolynomialFunction(coefficients);
                } else {
                    generatorFunction = generatorFunction.multiply(new PolynomialFunction(coefficients));
                }
            }

            // store the probabilities
            double coefficients[] = generatorFunction.getCoefficients();
            for (int i = 0; i < coefficients.length; ++i) {
                probabilities.put(i, coefficients[i]);
            }
        }

        // return probability of getting given value
        if (probabilities.containsKey(value)) {
            return probabilities.get(value);
        }
        return 0;
    }

    /**
     * Compute probability that the next roll will produce a total with at least the given value.
     *
     * @param value value that total should be no less than
     * @return probability that the next roll will produce a total at least the given value
     */
    public double atLeastProbability(int value) {
        double sum = 0.0;
        for (int i = value; i <= getMax(); i++) {
            sum += probability(i);
        }
        return sum;
    }

    /**
     * Compute probability that the next roll will produce a total with at most the given value.
     *
     * @param value value that total should be no greater than
     * @return probability that the next roll will produce a total at most the given value
     */
    public double atMostProbability(int value) {
        double sum = 0.0;
        for (int i = 1; i <= value; i++) {
            sum += probability(i);
        }
        return sum;
    }


    /**
     * Returns an iterator over the dice contained in the roller.
     *
     * @return an iterator that traverses the dice in the roller
     */
    public Iterator<Die> iterator() {
        return dice.iterator();
    }

    /**
     * Returns the number of dice in the roller.
     *
     * @return number of dice in the roller
     */
    public int size() {
        return dice.size();
    }

    /**
     * Get string representing dice roller.
     *
     * @return representation of dice roller
     */
    public String toString() {
        var collector = new HashMap<Integer, Integer>();
        var strings = new ArrayList<String>();
        for (Die die : this) {
            collector.merge(die.getFaces(), 1, (old, one) -> old + one);
        }
        for (var entry : collector.entrySet()) {
            strings.add(String.format("%dd%d", entry.getValue(), entry.getKey()));
        }
        return String.join(" + ", strings) + String.format(" = %d", getTotal());
    }
}
