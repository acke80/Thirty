package se.umu.christofferakrin.thirty.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

import se.umu.christofferakrin.thirty.utils.PointOptions;

public class Game implements Parcelable{

    public final int GAME_ROUNDS = 10;

    private ArrayList<PointOptions> availablePointOptions = PointOptions.valuesAsList();

    private Round[] rounds = new Round[GAME_ROUNDS];
    private int curRound;

    private int score;

    private boolean gameOver;

    private String gameMessage = "Select dices to keep";

    public Game(){
        rounds[curRound] = new Round(PointOptions.LOW);
    }

    public void selectDie(int dieIndex){
        rounds[curRound].selectDie(dieIndex);
    }

    public boolean isSelectedDie(int dieIndex){
        return rounds[curRound].isSelectedDie(dieIndex);
    }

    public int getDieValue(int dieIndex){
        return rounds[curRound].getDieValue(dieIndex);
    }

    public void setOption(int index){
        rounds[curRound].setCurPointOption(availablePointOptions.get(index));
    }

    public void nextThrow(){
        if(rounds[curRound].nextThrow())
            nextRound();
    }

    /** Set the game to next round. */
    public void nextRound(){
        availablePointOptions.remove(rounds[curRound].getCurPointOption());

        score += rounds[curRound].getFinalScore();

        System.out.println("SCORE: "+ score + " OPTION:" + rounds[curRound].getCurPointOption().name());

        if(++curRound >= GAME_ROUNDS){
            gameOver = true;
            return;
        }

        rounds[curRound] = new Round(availablePointOptions.get(0));

    }

    public String getGameMessage(){
        return gameMessage;
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

    protected Game(Parcel in){
        rounds = in.createTypedArray(Round.CREATOR);
        curRound = in.readInt();
        score = in.readInt();
        gameMessage = in.readString();

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
        dest.writeString(gameMessage);
        dest.writeValue(PointOptions.toStringArray(availablePointOptions));
    }
}
