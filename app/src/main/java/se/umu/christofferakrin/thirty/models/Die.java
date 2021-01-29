package se.umu.christofferakrin.thirty.models;

import se.umu.christofferakrin.thirty.utils.DiceRoll;

public class Die{

    private final DiceRoll diceRoll = new DiceRoll();

    private int value;

    public Die(int value){
        setValue(value);
    }

    public Die(){
        this(0);
    }

    public void roll(){
        setValue(diceRoll.nextRoll());
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        if(value < 1 || value > 6)
            throw new IllegalArgumentException("Die value can only be between 1 and 6");
        this.value = value;
    }

    /** Sorts list of dice with bubble sort. */
    public static Die[] sortDice(Die[] dice){
        int n = dice.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (dice[j].getValue() > dice[j+1].getValue())
                {
                    Die temp = dice[j];
                    dice[j] = dice[j+1];
                    dice[j+1] = temp;
                }

        return dice;
    }
}
