package se.umu.christofferakrin.thirty.viewmodels;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import se.umu.christofferakrin.thirty.R;
import se.umu.christofferakrin.thirty.models.Game;

public class GameViewModel extends ViewModel{

    private SavedStateHandle stateHandle;

    private Game game;

    private int curGameScore = 10;

    public GameViewModel(SavedStateHandle stateHandle){
        this.stateHandle = stateHandle;

    }

    public void update(){
        curGameScore++;
    }

    public int getCurGameScore(){
        return curGameScore;
    }
}
