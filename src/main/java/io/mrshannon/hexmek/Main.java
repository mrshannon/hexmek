package io.mrshannon.hexmek;

public class Main {

    public static void main(String[] args) {
        var die = new io.mrshannon.hexmek.Die(12);
        var dice = new DiceRoller(new Die(6), new Die(6));
        for (int i = 0; i <= 20; ++i) {
            System.out.println(String.format("%d = %f", i, dice.probability(i)));
        }
        System.out.println(Direction.Enum.NORTH);
        System.out.println(Direction.Enum.NORTH.ordinal());
        System.out.println(Direction.Enum.SOUTH == Direction.Enum.SOUTH);
        var d = new Direction();
        for (int i = 0; i <= 20; i++) {
            System.out.println(d);
            d.rotateRight();
        }
        for (int i = 0; i <= 20; i++) {
            System.out.println(d);
            d.rotateLeft();
        }

	// write your code here
    }
}
