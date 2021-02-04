package se.umu.christofferakrin.thirty.utils;

import java.util.ArrayList;
import java.util.Arrays;

/** Enum for defining the different Point Options the player can choose. */
public enum PointOptions{
    LOW,
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    TWELVE(12);

    public final int SUM;

    PointOptions(int sum){
        SUM = sum;
    }

    PointOptions(){
        SUM = -1;
    }

    public static ArrayList<PointOptions> valuesAsList(){
        return new ArrayList<>(Arrays.asList(PointOptions.values()));
    }

    public static String[] toStringArray(ArrayList<PointOptions> pointOptions){
        String[] names = new String[pointOptions.size()];

        for(int i = 0; i < names.length; i++)
            names[i] = pointOptions.get(i).name();

        return names;
    }
}
