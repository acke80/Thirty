package se.umu.christofferakrin.thirty.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

import se.umu.christofferakrin.thirty.R;
import se.umu.christofferakrin.thirty.databinding.ActivityGameBinding;
import se.umu.christofferakrin.thirty.databinding.ActivityStartBinding;
import se.umu.christofferakrin.thirty.models.ResultActivity;

public class StartActivity extends AppCompatActivity{

    private ActivityStartBinding startBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Objects.requireNonNull(getSupportActionBar()).hide();

        startBinding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(startBinding.getRoot());

        startBinding.startButton.setOnClickListener(v -> toGameActivity(startBinding.getRoot()));
    }


    private void toGameActivity(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}