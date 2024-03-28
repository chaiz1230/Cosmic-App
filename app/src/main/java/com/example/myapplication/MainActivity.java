package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView credits_page, instructions_page;

    MediaPlayer menuMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        menuMusic = MediaPlayer.create(MainActivity.this,R.raw.menu_audio);
        menuMusic.setLooping(true);
        menuMusic.start();

        credits_page = (ImageView) findViewById(R.id.imgCredits);
        instructions_page = (ImageView) findViewById(R.id.imgInstructions);

        ImageView buttonStart = findViewById(R.id.btnStartGame);
        buttonStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GameplayActivity.class);
                startActivity(intent);
                menuMusic.stop();
                MainActivity.this.finish();
            }
        });

        ImageView btnInstruction = findViewById(R.id.btnInstruction);
        btnInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(instructions_page.getVisibility() == View.VISIBLE){
                    instructions_page.setVisibility(View.INVISIBLE);
                } else {
                    instructions_page.setVisibility(View.VISIBLE);
                }
            }
        });

        ImageView btnCredits = findViewById(R.id.btnCredits);
        btnCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(credits_page.getVisibility() == View.VISIBLE){
                    credits_page.setVisibility(View.INVISIBLE);
                } else {
                    credits_page.setVisibility(View.VISIBLE);
                }
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (credits_page.getVisibility() == View.VISIBLE) {
                    credits_page.setVisibility(View.INVISIBLE);
                } else if (instructions_page.getVisibility() == View.VISIBLE) {
                    instructions_page.setVisibility(View.INVISIBLE);
                } else {
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause or stop background music playback when the activity goes into the background
        if (menuMusic != null && menuMusic.isPlaying()) {
            menuMusic.pause(); // Pause the music
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Resume background music playback when the activity comes back into the foreground
        if (menuMusic != null && !menuMusic.isPlaying()) {
            menuMusic.start(); // Start or resume the music
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(menuMusic != null){
            menuMusic.release();
            menuMusic = null;
        }
    }


}