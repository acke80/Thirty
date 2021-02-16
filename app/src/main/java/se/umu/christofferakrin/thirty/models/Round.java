package se.umu.christofferakrin.thirty.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import se.umu.christofferakrin.thirty.utils.PointOptions;

public class Round implements Parcelable{

    public final int ROUND_THROWS = 3;

    private PointOptions curPointOption;
    private int curThrow;

    private Die[] dice = new Die[6];

    private int finalScore = -1;

    Round(PointOptions pointOption){
        this.curPointOption = pointOption;

        for(int i = 0; i < 6; i++)
            dice[i] = new Die();


    }


    /** Finds the maximum score for the selected option.
     * @return summation of maximum score. */
    protected int getFinalScore(){
        if(finalScore != -1)
            return finalScore;

        /* Sort the dies by value and store the values in a list
        * in decreasing order for simplicity. */
        dice = Die.sortDice(this.dice);
        ArrayList<Integer> dieVals = new ArrayList<>();
        for(int i = dice.length - 1; i >= 0; i--)
            dieVals.add(dice[i].getValue());

        int score = 0;

        /* Handle special case were option is LOW. */
        if(curPointOption == PointOptions.LOW){
            for(Die die : dice){
                int val = die.getValue();
                if(val <= 3) score += val;
            }
        }else{
            int sum = curPointOption.SUM;

            /* Remove values larger than the sum.
            * Also add to score and remove values that equals sum. */
            for(Integer val : new ArrayList<>(dieVals)){
                if(val > sum)
                    dieVals.remove(val);
                else if(val == sum){
                    score += val;
                    dieVals.remove(val);
                }
            }

            int offset = 0;
            while(true){
                if(isListSmallerThanSum(dieVals, sum)) break; /* Break if sum of list is smaller than sum. */
                if(dieVals.size() <= 1) break; /* Break if we have 1 or 0 values left. */

                ArrayList<Integer> combination = new ArrayList<>();
                int firstVal = dieVals.get(0); /* Largest value in list. */
                combination.add(firstVal);

                /* Offsets the next value from first value in list.
                 * We start the summation from this offset value. */
                offset++;
                if(offset == dieVals.size()){
                    dieVals.remove(0);
                    if(dieVals.size() <= 1) break;
                    /* If first value was not able to combine itself to equal sum, and
                     * the next value equals the first value, and their summation is smaller than sum,
                     * we set their sum as the first value instead. */
                    if(dieVals.get(1).equals(firstVal) && dieVals.get(1)*2 < sum){
                        dieVals.remove(0);
                        dieVals.add(0, firstVal*2);
                    }

                    offset = 0;
                    continue;
                }

                for(int i = offset; i < dieVals.size(); i++){
                    int curVal = dieVals.get(i);
                    combination.add(curVal);

                    int combSum = 0; /* Summation of combinations. */
                    for(Integer val : combination)
                        combSum += val;

                    /* If the combined sum equals the sum, we can remove these values
                     * from the whole list and add to score. */
                    if(combSum == sum){
                        score += sum;

                        for(Integer val : combination){
                            dieVals.remove(val);
                        }
                        offset = 0;
                        break;
                    }else if(combSum > sum){
                        break;
                    }
                }
            }
        }

        finalScore = score;

        return finalScore;
    }

    private boolean isListSmallerThanSum(ArrayList<Integer> values, int sum){
        int combSum = 0;
        for(Integer val : values) combSum += val;
        return combSum < sum;
    }

    protected void selectDie(int dieIndex){
        dice[dieIndex].select();
    }

    protected boolean isSelectedDie(int dieIndex){
        return dice[dieIndex].isSelected();
    }

    protected int getDieValue(int dieIndex){
        return dice[dieIndex].getValue();
    }

    protected void setCurPointOption(PointOptions curPointOption){
        this.curPointOption = curPointOption;
    }

    protected PointOptions getCurPointOption(){
        return curPointOption;
    }

    protected boolean isLastThrow(){
        return curThrow >= ROUND_THROWS - 1;
    }

    /** @return True if the round is over, else false. */
    protected boolean nextThrow(){
        curThrow++;

        for(Die die : dice)
            if(!die.isSelected())
                die.roll();

        return curThrow >= ROUND_THROWS - 1;
    }

    protected int getCurThrow(){
        return curThrow;
    }

    protected Round(Parcel in){
        curThrow = in.readInt();
        dice = in.createTypedArray(Die.CREATOR);
        curPointOption = PointOptions.valueOf(in.readString());

    }

    public static final Creator<Round> CREATOR = new Creator<Round>(){
        @Override
        public Round createFromParcel(Parcel in){
            return new Round(in);
        }

        @Override
        public Round[] newArray(int size){
            return new Round[size];
        }
    };


    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(curThrow);
        dest.writeTypedArray(dice, flags);
        dest.writeString(curPointOption.name());
    }

}
