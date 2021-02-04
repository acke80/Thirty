package se.umu.christofferakrin.thirty.viewmodels;

import androidx.annotation.NonNull;
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

    /** Called when we press a die button. */
    public void selectDie(int dieIndex){
        game.selectDie(dieIndex);
        stateHandle.set("game", game);
    }

    /** Called when we change the option in the option spinner. */
    public void setOption(int index){
        game.setOption(index);
        stateHandle.set("game", game);
    }

    /** Called when we press the throw button. */
    public void nextThrow(){
        game.nextThrow();
        stateHandle.set("game", game);
    }

    public boolean isSelectedDie(int dieIndex){
        return game.isSelectedDie(dieIndex);
    }

    public int getDieValue(int dieIndex){
        return game.getDieValue(dieIndex);
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

    public int getCurOptionIndex(){
        return game.getCurOptionIndex();
    }

    public boolean isGameOver(){
        return game.isGameOver();
    }
}
