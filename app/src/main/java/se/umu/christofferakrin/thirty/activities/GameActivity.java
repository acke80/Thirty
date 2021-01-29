package se.umu.christofferakrin.thirty.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import se.umu.christofferakrin.thirty.R;
import se.umu.christofferakrin.thirty.databinding.ActivityMainBinding;
import se.umu.christofferakrin.thirty.viewmodels.GameViewModel;

public class GameActivity extends AppCompatActivity{

    private GameViewModel gameViewModel;

    private ActivityMainBinding gameBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        gameBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = gameBinding.getRoot();

        setContentView(view);

        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        updateGameScore();

        gameBinding.button.setOnClickListener(v -> update());
    }

    private void update(){
        gameViewModel.update();
        updateGameScore();
    }

    private void updateGameScore(){
        gameBinding.setScore(
                getResources().getString(R.string.score) +
                        " " +
                        gameViewModel.getCurGameScore()
        );
    }
}