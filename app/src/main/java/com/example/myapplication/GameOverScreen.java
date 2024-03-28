package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameOverScreen extends AppCompatActivity {

    int gameScore, timeLeft, finalScore, imgResult;

    MediaPlayer goodJobMusic, excellentMusic ,gameOverMusic;

    TextView tv_GameScore, tv_TimeLeft, tv_FinalScore;

    ImageView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_over_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        excellentMusic = MediaPlayer.create(GameOverScreen.this,R.raw.excellent_audio);
        goodJobMusic = MediaPlayer.create(GameOverScreen.this,R.raw.goodjob_audio);
        gameOverMusic = MediaPlayer.create(GameOverScreen.this,R.raw.gameover_audio);

        tv_GameScore = (TextView) findViewById(R.id.tv_GameScore);
        tv_TimeLeft = (TextView) findViewById(R.id.tv_TimeLeft);
        tv_FinalScore = (TextView) findViewById(R.id.tv_FinalScore);

        results = (ImageView) findViewById(R.id.results);

        gameScore = getIntent().getIntExtra("gameScore",0);
        timeLeft = getIntent().getIntExtra("timeLeft",0);

        tv_GameScore.setText("Game Score: "+gameScore);
        tv_TimeLeft.setText("Time Left: "+timeLeft+" * 20");

        if(timeLeft>=40){
            imgResult = R.drawable.excellent;
            excellentMusic.setVolume(1.0f,1.0f);
            excellentMusic.start();
        }else if(timeLeft > 0){
            imgResult = R.drawable.good_job;
            goodJobMusic.setVolume(1.0f,1.0f);
            goodJobMusic.start();
        }else{
            imgResult = R.drawable.game_over;
            gameOverMusic.setVolume(1.0f,1.0f);
            gameOverMusic.start();
        }

        results.setImageResource(imgResult);

        finalScore = gameScore + (timeLeft * 20);

        tv_FinalScore.setText("Final Score: "+finalScore);

        ImageView buttonReplay = findViewById(R.id.btnReplay);
        buttonReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverScreen.this,GameplayActivity.class);
                startActivity(intent);
                GameOverScreen.this.finish();
            }
        });

        ImageView buttonMainMenu = findViewById(R.id.btnMenu);
        buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverScreen.this,MainActivity.class);
                startActivity(intent);
                GameOverScreen.this.finish();
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(GameOverScreen.this, MainActivity.class);
                startActivity(intent);
                GameOverScreen.this.finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(excellentMusic != null){
            excellentMusic.release();
            excellentMusic = null;
        } else if(goodJobMusic != null){
            goodJobMusic.release();
            goodJobMusic = null;
        } else if(gameOverMusic != null) {
            gameOverMusic.release();
            gameOverMusic = null;
        }
    }

}