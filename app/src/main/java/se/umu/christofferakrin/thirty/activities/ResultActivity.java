package se.umu.christofferakrin.thirty.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

import se.umu.christofferakrin.thirty.R;
import se.umu.christofferakrin.thirty.databinding.ActivityResultBinding;
import se.umu.christofferakrin.thirty.models.Result;

public class ResultActivity extends AppCompatActivity{

    ActivityResultBinding resultBinding;

    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Objects.requireNonNull(getSupportActionBar()).hide();

        resultBinding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(resultBinding.getRoot());

        Intent intent = getIntent();
        result = intent.getParcelableExtra("result");

        resultBinding.setScore(Integer.toString(result.TOTAL_SCORE));
    }
}