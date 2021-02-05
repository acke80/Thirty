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

    public PointOptions getRoundOption(int roundIndex){
        if(roundIndex >= rounds.length - 1)
            throw new IllegalArgumentException("Round Index out of range.");

        return rounds[roundIndex].getCurPointOption();
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
