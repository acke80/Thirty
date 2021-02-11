package se.umu.christofferakrin.thirty.models;

import android.os.Parcel;
import android.os.Parcelable;

import se.umu.christofferakrin.thirty.utils.PointOptions;


/** Contains necessary information for the ResultActivity about the game. */
public class Result implements Parcelable{

    public final int TOTAL_SCORE;
    private Round[] rounds;

    public Result(int totalScore, Round[] rounds){
        TOTAL_SCORE = totalScore;
        this.rounds = rounds;

    }

    /** @return All the round numbers as a string array. */
    public String[] getRoundsAsString(){
        String[] roundsAsString = new String[rounds.length];

        for(int i = 1; i <= rounds.length; i++)
            roundsAsString[i - 1] = Integer.toString(i);

        return roundsAsString;
    }

    /** @return The order of chosen point options for each round as a string array. */
    public String[] getOptionsAsString(){
        String[] options = new String[rounds.length];

        for(int i = 0; i < rounds.length; i++)
            options[i] = rounds[i].getCurPointOption().name();

        return options;
    }

    /** @return The final score for each round as a string array. */
    public String[] getScoresAsString(){
        String[] scores = new String[rounds.length];

        for(int i = 0; i < rounds.length; i++)
            scores[i] = Integer.toString(rounds[i].getFinalScore());

        return scores;
    }

    protected Result(Parcel in){
        TOTAL_SCORE = in.readInt();
        rounds = in.createTypedArray(Round.CREATOR);
    }

    public static final Creator<Result> CREATOR = new Creator<Result>(){
        @Override
        public Result createFromParcel(Parcel in){
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size){
            return new Result[size];
        }
    };

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(TOTAL_SCORE);
        dest.writeTypedArray(rounds, flags);
    }
}
