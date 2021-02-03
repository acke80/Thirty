package se.umu.christofferakrin.thirty.models;

import se.umu.christofferakrin.thirty.utils.PointOptions;

public class Round{

    public final int ROUND_THROWS = 3;

    private PointOptions curPointOption;
    private int curThrow;

    private Die[] dice = new Die[6];

    Round(PointOptions pointOption){
        this.curPointOption = pointOption;

        for(int i = 0; i < 6; i++)
            dice[i] = new Die();
    }

    /** Finds the maximum score.
     * @return summation of maximum score. */
    public int getMaximumPoints(Die[] dice){
        dice = Die.sortDice(dice);

        int sum = 0;

        if(curPointOption == PointOptions.LOW){

        }else{

        }

        return sum;
    }

    protected boolean selectDie(int dieIndex){
        Die die = dice[dieIndex];

        die.select();
        return die.isSelected();
    }

    protected void setCurPointOption(PointOptions curPointOption){
        this.curPointOption = curPointOption;
    }

    protected PointOptions getCurPointOption(){
        return curPointOption;
    }

    protected boolean nextThrow(){
        return ++curThrow > ROUND_THROWS;
    }

    protected int getCurThrow(){
        return curThrow;
    }

}
