package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.Collections;

public class GameplayActivity extends AppCompatActivity {

    TextView tv_timer, tv_score;

    ImageView iv_11, iv_12, iv_13, iv_21, iv_22, iv_23, iv_31, iv_32, iv_33, iv_41, iv_42, iv_43;

    Integer[] cardArray = {101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206};

    CountDownTimer countDownTimer;

    MediaPlayer gameMusic, cardPressed, cardMatch, cardNotMatch;

    int image101, image102, image103, image104, image105, image106,
            image201, image202, image203, image204, image205, image206;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;

    int cardNumber = 1;

    int playerPoints = 0, gameScore, timeLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gameplay);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gameMusic = MediaPlayer.create(GameplayActivity.this,R.raw.gameplay_audio);
        gameMusic.setVolume(2.0f,2.0f);
        gameMusic.setLooping(true);
        gameMusic.start();

        tv_timer = (TextView) findViewById(R.id.tv_timer);
        tv_score = (TextView) findViewById(R.id.tv_score);

        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_13 = (ImageView) findViewById(R.id.iv_13);

        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_23 = (ImageView) findViewById(R.id.iv_23);

        iv_31 = (ImageView) findViewById(R.id.iv_31);
        iv_32 = (ImageView) findViewById(R.id.iv_32);
        iv_33 = (ImageView) findViewById(R.id.iv_33);

        iv_41 = (ImageView) findViewById(R.id.iv_41);
        iv_42 = (ImageView) findViewById(R.id.iv_42);
        iv_43 = (ImageView) findViewById(R.id.iv_43);

        iv_11.setTag(0);
        iv_12.setTag(1);
        iv_13.setTag(2);
        iv_21.setTag(3);
        iv_22.setTag(4);
        iv_23.setTag(5);
        iv_31.setTag(6);
        iv_32.setTag(7);
        iv_33.setTag(8);
        iv_41.setTag(9);
        iv_42.setTag(10);
        iv_43.setTag(11);

        //load the card images
        frontOfCardsResources();

        //shuffle the images
        Collections.shuffle(Arrays.asList(cardArray));

        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_11, theCard);
            }
        });

        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_12, theCard);
            }
        });

        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_13, theCard);
            }
        });

        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_21, theCard);
            }
        });

        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_22, theCard);
            }
        });

        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_23, theCard);
            }
        });

        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_31, theCard);
            }
        });

        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_32, theCard);
            }
        });

        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_33, theCard);
            }
        });

        iv_41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_41, theCard);
            }
        });

        iv_42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_42, theCard);
            }
        });

        iv_43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = (int) v.getTag();
                doStuff(iv_43, theCard);
            }
        });

        startTimer();

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(GameplayActivity.this, MainActivity.class);
                startActivity(intent);
                GameplayActivity.this.finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(61000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the TextView with the remaining time
                tv_timer.setText("Timer: " + String.valueOf(millisUntilFinished / 1000));
                // Store the remaining time in the class-level variable
                timeLeft = (int) (millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // Handle timer finish event, if needed
                timeLeft = 0;
                gameScore = playerPoints;
                gameOver();
            }
        };

        // Start the countdown timer
        countDownTimer.start();
    }

    private void doStuff(ImageView iv, int card) {
        //set to the correct image view
        cardPressed = MediaPlayer.create(GameplayActivity.this,R.raw.card_pressed);
        cardPressed.setVolume(0.75f,0.75f);
        cardPressed.start();
        if (cardArray[card] == 101) {
            iv.setImageResource(image101);
        } else if (cardArray[card] == 102) {
            iv.setImageResource(image102);
        } else if (cardArray[card] == 103) {
            iv.setImageResource(image103);
        } else if (cardArray[card] == 104) {
            iv.setImageResource(image104);
        } else if (cardArray[card] == 105) {
            iv.setImageResource(image105);
        } else if (cardArray[card] == 106) {
            iv.setImageResource(image106);
        } else if (cardArray[card] == 201) {
            iv.setImageResource(image201);
        } else if (cardArray[card] == 202) {
            iv.setImageResource(image202);
        } else if (cardArray[card] == 203) {
            iv.setImageResource(image203);
        } else if (cardArray[card] == 204) {
            iv.setImageResource(image204);
        } else if (cardArray[card] == 205) {
            iv.setImageResource(image205);
        } else if (cardArray[card] == 206) {
            iv.setImageResource(image206);
        }

        //check which image is selected and save it to temp variable
        if (cardNumber == 1) {
            firstCard = cardArray[card];
            if (firstCard > 200) {
                firstCard -= 100;
            }
            cardNumber = 2;
            clickedFirst = card;

            iv.setEnabled(false);
        } else if (cardNumber == 2) {
            secondCard = cardArray[card];
            if (secondCard > 200) {
                secondCard -= 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            iv.setEnabled(false);

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_41.setEnabled(false);
            iv_42.setEnabled(false);
            iv_43.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //check if the selected images are equal
                    calculate();
                }
            }, 500);
        }

        cardPressed.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Release the MediaPlayer resources when playback is completed
                cardPressed.release();
            }
        });
    }

    private void calculate(){
        if (firstCard == secondCard){
            if(clickedFirst == 0){
                iv_11.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 1){
                iv_12.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 2){
                iv_13.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 3){
                iv_21.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 4){
                iv_22.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 5){
                iv_23.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 6){
                iv_31.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 7){
                iv_32.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 8){
                iv_33.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 9){
                iv_41.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 10){
                iv_42.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 11){
                iv_43.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond == 0){
                iv_11.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 1){
                iv_12.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 2){
                iv_13.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 3){
                iv_21.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 4){
                iv_22.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 5){
                iv_23.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 6){
                iv_31.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 7){
                iv_32.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 8){
                iv_33.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 9){
                iv_41.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 10){
                iv_42.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 11){
                iv_43.setVisibility(View.INVISIBLE);
            }

            cardMatch = MediaPlayer.create(GameplayActivity.this,R.raw.match_found);
            cardMatch.start();
            playerPoints += 200;
            tv_score.setText("Score: "+playerPoints);

            cardMatch.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // Release the MediaPlayer resources when playback is completed
                    cardMatch.release();
                }
            });

        } else {
            iv_11.setImageResource(R.drawable.cc_back);
            iv_12.setImageResource(R.drawable.cc_back);
            iv_13.setImageResource(R.drawable.cc_back);
            iv_21.setImageResource(R.drawable.cc_back);
            iv_22.setImageResource(R.drawable.cc_back);
            iv_23.setImageResource(R.drawable.cc_back);
            iv_31.setImageResource(R.drawable.cc_back);
            iv_32.setImageResource(R.drawable.cc_back);
            iv_33.setImageResource(R.drawable.cc_back);
            iv_41.setImageResource(R.drawable.cc_back);
            iv_42.setImageResource(R.drawable.cc_back);
            iv_43.setImageResource(R.drawable.cc_back);

            cardNotMatch = MediaPlayer.create(GameplayActivity.this,R.raw.match_notfound);
            cardNotMatch.setVolume(0.6f,0.6f);
            cardNotMatch.start();

            cardNotMatch.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    cardNotMatch.release();
                }
            });
        }

        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_41.setEnabled(true);
        iv_42.setEnabled(true);
        iv_43.setEnabled(true);

        //check if game is over
        checkEnd();

    }

    private void checkEnd(){
        if (iv_11.getVisibility() == View.INVISIBLE &&
                iv_12.getVisibility() == View.INVISIBLE &&
                iv_13.getVisibility() == View.INVISIBLE &&
                iv_21.getVisibility() == View.INVISIBLE &&
                iv_22.getVisibility() == View.INVISIBLE &&
                iv_23.getVisibility() == View.INVISIBLE &&
                iv_31.getVisibility() == View.INVISIBLE &&
                iv_32.getVisibility() == View.INVISIBLE &&
                iv_33.getVisibility() == View.INVISIBLE &&
                iv_41.getVisibility() == View.INVISIBLE &&
                iv_42.getVisibility() == View.INVISIBLE &&
                iv_43.getVisibility() == View.INVISIBLE){

            pauseTimer();

            gameScore = playerPoints;

            gameOver();

        }
    }

    private void pauseTimer(){
        countDownTimer.cancel();
    }

    public void gameOver(){
        Intent intent = new Intent(GameplayActivity.this,GameOverScreen.class);
        intent.putExtra("gameScore", gameScore);
        intent.putExtra("timeLeft", timeLeft);
        startActivity(intent);
        GameplayActivity.this.finish();
    }

    private void frontOfCardsResources(){
        image101 = R.drawable.cc_image101;
        image102 = R.drawable.cc_image102;
        image103 = R.drawable.cc_image103;
        image104 = R.drawable.cc_image104;
        image105 = R.drawable.cc_image105;
        image106 = R.drawable.cc_image106;
        image201 = R.drawable.cc_image201;
        image202 = R.drawable.cc_image202;
        image203 = R.drawable.cc_image203;
        image204 = R.drawable.cc_image204;
        image205 = R.drawable.cc_image205;
        image206 = R.drawable.cc_image206;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause or stop background music playback when the activity goes into the background
        if (gameMusic != null && gameMusic.isPlaying()) {
            gameMusic.pause(); // Pause the music
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Resume background music playback when the activity comes back into the foreground
        if (gameMusic != null && !gameMusic.isPlaying()) {
            gameMusic.start(); // Start or resume the music
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(gameMusic != null){
            gameMusic.release();
            gameMusic = null;
        } else if (cardPressed != null){
            cardPressed.release();
            cardPressed = null;
        } else if (cardMatch != null) {
            cardMatch.release();
            cardMatch = null;
        } else if (cardNotMatch != null) {
            cardNotMatch.release();
            cardMatch = null;
        }
        countDownTimer.cancel();
    }


}