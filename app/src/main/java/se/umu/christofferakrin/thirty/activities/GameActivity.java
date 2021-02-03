package se.umu.christofferakrin.thirty.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import java.util.Objects;

import se.umu.christofferakrin.thirty.R;
import se.umu.christofferakrin.thirty.databinding.ActivityMainBinding;
import se.umu.christofferakrin.thirty.viewmodels.GameViewModel;

public class GameActivity extends AppCompatActivity{

    private GameViewModel gameViewModel;

    private ActivityMainBinding gameBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();

        gameBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = gameBinding.getRoot();

        setContentView(view);

        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        gameBinding.throwButton.setOnClickListener(v -> {
            gameViewModel.nextThrow();
            update();
        });

        createButtons();

        update();
    }

    private void update(){
        updateOptionSpinner();
        updateGameMessage();
        updateGameScore();
        updateGameRound();
        updateGameThrow();
    }

    private void updateOptionSpinner(){
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, gameViewModel.getAvailableOptions());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameBinding.optionsSpinner.setAdapter(spinnerArrayAdapter);
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

    private void updateDices(){

    }

    private void createButtons(){
        /* TODO: make less hard coded by implementing die button listener */
        gameBinding.die1.setOnClickListener(v -> {
            if(gameViewModel.selectDie(0)){
                gameBinding.die1.setImageResource(R.drawable.grey1);
            }else{
                gameBinding.die1.setImageResource(R.drawable.white1);
            }
        });
        gameBinding.die2.setOnClickListener(v -> {
            if(gameViewModel.selectDie(1)){
                gameBinding.die2.setImageResource(R.drawable.grey2);
            }else{
                gameBinding.die2.setImageResource(R.drawable.white2);
            }
        });
        gameBinding.die3.setOnClickListener(v -> {
            if(gameViewModel.selectDie(2)){
                gameBinding.die3.setImageResource(R.drawable.grey3);
            }else{
                gameBinding.die3.setImageResource(R.drawable.white3);
            }
        });
        gameBinding.die4.setOnClickListener(v -> {
            if(gameViewModel.selectDie(3)){
                gameBinding.die4.setImageResource(R.drawable.grey4);
            }else{
                gameBinding.die4.setImageResource(R.drawable.white4);
            }
        });
        gameBinding.die5.setOnClickListener(v -> {
            if(gameViewModel.selectDie(4)){
                gameBinding.die5.setImageResource(R.drawable.grey5);
            }else{
                gameBinding.die5.setImageResource(R.drawable.white5);
            }
        });
        gameBinding.die6.setOnClickListener(v -> {
            if(gameViewModel.selectDie(5)){
                gameBinding.die6.setImageResource(R.drawable.grey6);
            }else{
                gameBinding.die6.setImageResource(R.drawable.white6);
            }
        });
    }
}