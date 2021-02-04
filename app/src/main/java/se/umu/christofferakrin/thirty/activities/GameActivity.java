package se.umu.christofferakrin.thirty.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

import se.umu.christofferakrin.thirty.R;
import se.umu.christofferakrin.thirty.databinding.ActivityMainBinding;
import se.umu.christofferakrin.thirty.models.Game;
import se.umu.christofferakrin.thirty.models.ResultActivity;
import se.umu.christofferakrin.thirty.viewmodels.GameViewModel;

public class GameActivity extends AppCompatActivity{

    private GameViewModel gameViewModel;

    private ActivityMainBinding gameBinding;

    private int[] whiteDieResources;
    private int[] greyDieResources;

    private ImageButton[] dieButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();

        gameBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(gameBinding.getRoot());

        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        gameBinding.throwButton.setOnClickListener(v -> onActionButton());

        whiteDieResources = new int[]{ R.drawable.white1, R.drawable.white2,
                R.drawable.white3, R.drawable.white4, R.drawable.white5, R.drawable.white6};
        greyDieResources = new int[]{ R.drawable.grey1, R.drawable.grey2,
                R.drawable.grey3, R.drawable.grey4, R.drawable.grey5, R.drawable.grey6};

        dieButtons = new ImageButton[]{ gameBinding.die1, gameBinding.die2,
                gameBinding.die3, gameBinding.die4, gameBinding.die5, gameBinding.die6};

        createDieButtonListeners();

        gameBinding.optionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                gameViewModel.setOption((int) id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        update();
        updateOptionSpinner();
    }

    private void onActionButton(){
        if(gameViewModel.isGameOver())
            return;
        gameViewModel.nextThrow();
        updateOptionSpinner();
        update();
    }

    private void update(){
        if(gameViewModel.isGameOver()){
            toGameOverActivity(gameBinding.getRoot());
        }

        updateGameMessage();
        updateGameScore();
        updateGameRound();
        updateGameThrow();
        updateDieButtons();
    }

    private void updateOptionSpinner(){
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, gameViewModel.getAvailableOptions());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameBinding.optionsSpinner.setAdapter(spinnerArrayAdapter);

        gameBinding.optionsSpinner.setSelection(gameViewModel.getCurOptionIndex());
    }

    private void updateGameMessage(){
        gameBinding.setMessage(gameViewModel.getGameMessage());
    }

    private void updateGameScore(){
        gameBinding.setScore(
                getResources().getString(R.string.score) +
                        " " +
                        gameViewModel.getCurGameScore()
        );
    }

    private void updateGameRound(){
        gameBinding.setRound(
                getResources().getString(R.string.round) +
                        " " +
                        gameViewModel.getCurRound()
        );
    }

    private void updateGameThrow(){
        gameBinding.setThrowNum(
                getResources().getString(R.string.throwNum) +
                        " " +
                        gameViewModel.getCurThrow()
        );
    }

    private void updateDieButtons(){
        for(int i = 0; i < 6; i++){
            if(gameViewModel.isSelectedDie(i))
                dieButtons[i].setImageResource(greyDieResources[gameViewModel.getDieValue(i) - 1]);
            else
                dieButtons[i].setImageResource(whiteDieResources[gameViewModel.getDieValue(i) - 1]);
        }
    }

    private void createDieButtonListeners(){
        for(int i = 0; i < dieButtons.length; i++){
            final int finalI = i;
            dieButtons[i].setOnClickListener(v -> {
                gameViewModel.selectDie(finalI);
                update();
            });
        }
    }

    private void toGameOverActivity(View view){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("game", "game over");
        startActivity(intent);
    }
}