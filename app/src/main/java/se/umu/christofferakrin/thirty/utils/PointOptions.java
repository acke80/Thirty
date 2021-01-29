package se.umu.christofferakrin.thirty.utils;

import java.util.ArrayList;
import java.util.Arrays;

/** Enum for defining the different Point Options the player can choose */
public enum PointOptions{
    LOW,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    ELEVEN,
    TWELVE;

    public static ArrayList<PointOptions> valuesAsList(){
        return (ArrayList<PointOptions>) Arrays.asList(PointOptions.values());
    }
}
