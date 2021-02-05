package se.umu.christofferakrin.thirty.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.Objects;

import se.umu.christofferakrin.thirty.R;
import se.umu.christofferakrin.thirty.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity{

    private ActivityStartBinding startBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        Objects.requireNonNull(getSupportActionBar()).hide();

        startBinding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(startBinding.getRoot());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            toGameActivity();
            return true;
        } else {
            return false;
        }
    }


    private void toGameActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}