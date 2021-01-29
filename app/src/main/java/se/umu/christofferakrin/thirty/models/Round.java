package se.umu.christofferakrin.thirty.models;

import se.umu.christofferakrin.thirty.utils.PointOptions;

public class Round{

    private PointOptions pointOption;

    Round(PointOptions pointOption){
        this.pointOption = pointOption;
    }

    /** Finds the maximum score.
     * @return summation of maximum score. */
    public int getMaximumPoints(Die[] dice){
        dice = Die.sortDice(dice);

        int sum = 0;

        if(pointOption == PointOptions.LOW){

        }else{

        }

        return sum;
    }


}
