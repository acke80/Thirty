package se.umu.christofferakrin.thirty.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;


public class Die implements Parcelable{

    private final Random random = new Random();

    private int value;

    private boolean selected;

    public Die(int value){
        setValue(value);
    }

    public Die(){
        this(1);
        roll();
    }

    public void roll(){
        setValue(nextRoll());
    }

    public void select(){
        selected = !selected;
    }

    public int getValue(){
        return value;
    }

    public boolean isSelected(){
        return selected;
    }

    public void setValue(int value){
        if(value < 1 || value > 6)
            throw new IllegalArgumentException("Die value can only be between 1 and 6");
        this.value = value;
    }

    /** Sorts list of dice with bubble sort. */
    public static Die[] sortDice(Die[] dice){
        int n = dice.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (dice[j].getValue() > dice[j+1].getValue())
                {
                    Die temp = dice[j];
                    dice[j] = dice[j+1];
                    dice[j+1] = temp;
                }

        return dice;
    }

    private int nextRoll(){
        return random.nextInt(6) + 1;
    }

    protected Die(Parcel in){
        value = in.readInt();
        selected = in.readByte() != 0;
    }

    public static final Creator<Die> CREATOR = new Creator<Die>(){
        @Override
        public Die createFromParcel(Parcel in){
            return new Die(in);
        }

        @Override
        public Die[] newArray(int size){
            return new Die[size];
        }
    };

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(value);
        dest.writeByte((byte) (selected ? 1 : 0));
    }
}
