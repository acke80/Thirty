package se.umu.christofferakrin.thirty.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

import se.umu.christofferakrin.thirty.R;
import se.umu.christofferakrin.thirty.utils.PointOptions;

public class Game implements Parcelable{

    public final int GAME_ROUNDS = 10;

    private ArrayList<PointOptions> availablePointOptions = PointOptions.valuesAsList();

    private Round[] rounds = new Round[GAME_ROUNDS];
    private int curRound;

    private int score;

    private boolean gameOver;
    private boolean nextRound;

    private Result result;

    public Game(){
        rounds[curRound] = new Round(PointOptions.LOW);
    }

    public void selectDie(int dieIndex){
        rounds[curRound].selectDie(dieIndex);
    }

    public boolean isSelectedDie(int dieIndex){
        return rounds[curRound].isSelectedDie(dieIndex);
    }

    public boolean isNextRound(){
        return nextRound;
    }

    public int getDieValue(int dieIndex){
        return rounds[curRound].getDieValue(dieIndex);
    }

    public void setOption(int index){
        rounds[curRound].setCurPointOption(availablePointOptions.get(index));
    }

    public void nextThrow(){
        nextRound = rounds[curRound].nextThrow();
    }

    /** Set the game to next round. */
    public void nextRound(){
        nextRound = false;

        availablePointOptions.remove(rounds[curRound].getCurPointOption());

        score += rounds[curRound].getFinalScore();

        if(isLastRound()){
            gameOver = true;
            result = new Result(score, rounds);
        }else
            rounds[++curRound] = new Round(availablePointOptions.get(0));

    }

    public int getGameMessageResource(){
        if(gameOver)
            return R.string.finish;
        else if(isLastRound() && nextRound)
            return R.string.onlyOneOptionLeft;
        else if(nextRound)
            return R.string.choosePointOption;
        else
            return R.string.selectDicesToKeep;
    }

    public int getScore(){
        return score;
    }

    public int getCurRound(){
        return curRound;
    }

    public int getCurThrow(){
        return rounds[curRound].getCurThrow();
    }

    public int getCurOptionIndex(){
        for(int i = 0; i < availablePointOptions.size(); i++){
            if(availablePointOptions.get(i) == rounds[curRound].getCurPointOption())
                return i;
        }
        return 0;
    }

    public boolean isLastRound(){
        return curRound >= GAME_ROUNDS - 1;
    }

    /** Converts the available point options to a string array. */
    public String[] getAvailableOptions(){
        String[] options = new String[availablePointOptions.size()];

        for(int i = 0; i < options.length; i++){
            PointOptions po = availablePointOptions.get(i);
            if(po.SUM != -1)
                options[i] = Integer.toString(po.SUM);
            else
                options[i] = po.name();
        }

        return options;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public Result getResult(){
        return result;
    }

    protected Game(Parcel in){
        rounds = in.createTypedArray(Round.CREATOR);
        curRound = in.readInt();
        score = in.readInt();
        result = in.readParcelable(Result.class.getClassLoader());
        String[] names = (String[]) in.readValue(String[].class.getClassLoader());
        System.out.println(Arrays.toString(names));
        availablePointOptions.clear();
        for(String name : names) availablePointOptions.add(PointOptions.valueOf(name));
    }

    public static final Creator<Game> CREATOR = new Creator<Game>(){
        @Override
        public Game createFromParcel(Parcel in){
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size){
            return new Game[size];
        }
    };

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeTypedArray(rounds, flags);
        dest.writeInt(curRound);
        dest.writeInt(score);
        dest.writeParcelable(result, flags);
        dest.writeValue(PointOptions.toStringArray(availablePointOptions));
    }
}
