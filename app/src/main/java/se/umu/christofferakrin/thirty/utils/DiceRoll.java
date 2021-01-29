package se.umu.christofferakrin.thirty.utils;

import java.util.Random;

/** Creates a new Dice roll generator using {@link java.util.Random}. */
public class DiceRoll{

    private final Random random;

    public DiceRoll(){
        random = new Random();
    }

    public int nextRoll(){
        return random.nextInt(6) + 1;
    }

}
