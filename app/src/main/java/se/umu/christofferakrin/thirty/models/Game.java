package se.umu.christofferakrin.thirty.models;

import java.util.ArrayList;

import se.umu.christofferakrin.thirty.utils.PointOptions;

public class Game{

    public final int GAME_ROUNDS = 10;

    private ArrayList<PointOptions> availablePointOptions = PointOptions.valuesAsList();

    private Round[] rounds = new Round[GAME_ROUNDS];
    private int curRound;

    public Game(){

    }

    /** Set the game to next round.
     * @return false if game is out of rounds, else true. */
    public boolean nextRound(){
        if(++curRound > GAME_ROUNDS)
            return false;

        rounds[curRound] = new Round(availablePointOptions.get(0));

        return true;
    }

}
