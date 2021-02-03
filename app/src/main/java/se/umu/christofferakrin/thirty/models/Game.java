package se.umu.christofferakrin.thirty.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import se.umu.christofferakrin.thirty.utils.PointOptions;

public class Game implements Parcelable{

    public final int GAME_ROUNDS = 10;

    private ArrayList<PointOptions> availablePointOptions = PointOptions.valuesAsList();

    private Round[] rounds = new Round[GAME_ROUNDS];
    private int curRound;

    private int score;

    private String gameMessage = "Select dices to keep";

    public Game(){
        rounds[curRound] = new Round(PointOptions.LOW);
    }

    public boolean selectDie(int dieIndex){
        return rounds[curRound].selectDie(dieIndex);
    }

    public void setOption(String option){

    }

    public void nextThrow(){
        score++;
    }

    /** Set the game to next round.
     * @return false if game is out of rounds, else true. */
    public boolean nextRound(){
        availablePointOptions.remove(rounds[curRound].getCurPointOption());

        if(++curRound > GAME_ROUNDS)
            return false;

        rounds[curRound] = new Round(availablePointOptions.get(0));

        return true;
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

    protected Game(Parcel in){
        curRound = in.readInt();
        score = in.readInt();
        gameMessage = in.readString();
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
        dest.writeInt(curRound);
        dest.writeInt(score);
        dest.writeString(gameMessage);
    }
}
