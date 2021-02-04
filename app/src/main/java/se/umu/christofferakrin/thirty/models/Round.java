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

    Round(PointOptions pointOption){
        this.curPointOption = pointOption;

        for(int i = 0; i < 6; i++)
            dice[i] = new Die();
    }


    /** Finds the maximum score for the selected option.
     * @return summation of maximum score. */
    protected int getFinalScore(){
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

            while(dieVals.size() > 1){
                int firstVal = dieVals.get(0); /* Largest value in list. */
                int diff = sum - firstVal;

                ArrayList<Integer> combination = new ArrayList<>();
                combination.add(firstVal);

                /* Finds the largest values that combine with the largest value
                * to add up to the option sum.  */
                for(int i = 1; i < dieVals.size(); i++){
                    int curVal = dieVals.get(i);

                    if(curVal > diff) continue;

                    if(curVal == diff){
                        combination.add(curVal);
                        break;
                    }

                    combination.add(curVal);
                    diff -= curVal;
                }

                int combSum = 0;
                for(Integer val : combination)
                    combSum += val;

                /* If the combined sum equals the option sum, we add the sum to the score. */
                if(combSum == sum){
                    score += sum;
                }

                /* Remove the value(s) that are no longer needed. */
                for(Integer val : combination){
                    dieVals.remove(val);
                }
            }

        }

        return score;
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

    /** @return True if the round is over, else false. */
    protected boolean nextThrow(){
        if(++curThrow >= ROUND_THROWS)
            return true;

        for(Die die : dice)
            if(!die.isSelected())
                die.roll();

        return false;
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
