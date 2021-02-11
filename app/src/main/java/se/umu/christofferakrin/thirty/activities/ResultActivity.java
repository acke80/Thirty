package se.umu.christofferakrin.thirty.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

import se.umu.christofferakrin.thirty.R;
import se.umu.christofferakrin.thirty.adapters.ResultRecyclerAdapter;
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

        resultBinding.setScore(
                getResources().getString(R.string.score) +
                " " +
                result.TOTAL_SCORE);

        String[] rounds = result.getRoundsAsString();
        String[] scores = result.getScoresAsString();
        String[] options = result.getOptionsAsString();

        for(int i = 0; i < rounds.length; i++){
            rounds[i] = getResources().getString(R.string.round) + " " + rounds[i];
            scores[i] = getResources().getString(R.string.score) + " " + scores[i];
        }

        ResultRecyclerAdapter recyclerAdapter =
                new ResultRecyclerAdapter(this, rounds, scores, options);
        RecyclerView recyclerView = resultBinding.recyclerView;
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        resultBinding.newGameButton.setOnClickListener(v ->{
            toGameActivity();
        });

    }

    private void toGameActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}