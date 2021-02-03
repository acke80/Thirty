package se.umu.christofferakrin.thirty.viewmodels;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import se.umu.christofferakrin.thirty.models.Game;

public class GameViewModel extends ViewModel{

    private SavedStateHandle stateHandle;

    private Game game;

    public GameViewModel(SavedStateHandle stateHandle){
        this.stateHandle = stateHandle;

        if(stateHandle.contains("game"))
            game = stateHandle.get("game");
        else{
            game = new Game();
            stateHandle.set("game", game);
        }

    }

    /** Called when we press a die button.
     *
     * @return false if the selected die is deselected on call. True
     * if the selected die is selected on call. */
    public boolean selectDie(int dieIndex){
        return game.selectDie(dieIndex);
    }

    /** Called when we change the option in the option spinner. */
    public void setOption(String option){
        game.setOption(option);
    }

    /** Called when we press the throw button. */
    public void nextThrow(){
        game.nextThrow();
    }

    public String[] getAvailableOptions(){
        return game.getAvailableOptions();
    }

    public String getGameMessage(){
        return game.getGameMessage();
    }

    public int getCurGameScore(){
        return game.getScore();
    }

    public int getCurRound(){
        return game.getCurRound() + 1;
    }

    public int getCurThrow(){
        return game.getCurThrow() + 1;
    }
}
