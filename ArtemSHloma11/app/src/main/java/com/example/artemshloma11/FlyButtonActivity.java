package com.example.artemshloma11;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class FlyButtonActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton;
    private boolean isPlaying = false;
    private int screenWidth, screenHeight;
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fly_button);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playButton = findViewById(R.id.playButton);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://zvukitop.com/wp-content/uploads/2021/09/gimn-rossii-slova.mp3?_=2");
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    mediaPlayer.pause();
                    playButton.setText("Play");
                    stopAnimations();
                } else {
                    mediaPlayer.start();
                    playButton.setText("Pause");
                    startAnimations();
                }
                isPlaying = !isPlaying;
            }
        });

        setupScreenDimensions();


        TextView textView = findViewById(R.id.myTextView);
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(textView, "rotation", 0f, 360f*128);
        rotationAnimator.setDuration(1000*128);
        rotationAnimator.setInterpolator(new LinearInterpolator());
        rotationAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        rotationAnimator.setRepeatMode(ObjectAnimator.RESTART);
        rotationAnimator.start();

    }

    public void Next(View view){
        Intent intent = new Intent(this, UvedomleniyeActivity.class);
        startActivity(intent);
    }

    private void setupScreenDimensions() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

    private void startAnimations() {
        animateButtonToRandomPosition();
    }

    private void stopAnimations() {
        playButton.animate().cancel();
    }

    private void animateButtonToRandomPosition() {
        int targetX = random.nextInt(screenWidth - playButton.getWidth());
        int targetY = random.nextInt(screenHeight - playButton.getHeight());
        float targetScaleX = 0.5f + (random.nextFloat()*2);
        float targetScaleY = 0.5f + (random.nextFloat()*2);

        ObjectAnimator animX = ObjectAnimator.ofFloat(playButton, "x", playButton.getX(), targetX);
        ObjectAnimator animY = ObjectAnimator.ofFloat(playButton, "y", playButton.getY(), targetY);
        ObjectAnimator animScaleX = ObjectAnimator.ofFloat(playButton, "scaleX", playButton.getScaleX(), targetScaleX);
        ObjectAnimator animScaleY = ObjectAnimator.ofFloat(playButton, "scaleY", playButton.getScaleY(), targetScaleY);

        animX.setDuration(2000);
        animY.setDuration(2000);
        animScaleX.setDuration(2000);
        animScaleY.setDuration(2000);

        animX.setInterpolator(new LinearInterpolator());
        animY.setInterpolator(new LinearInterpolator());
        animScaleX.setInterpolator(new LinearInterpolator());
        animScaleY.setInterpolator(new LinearInterpolator());

        Animator.AnimatorListener repeatAnimationListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isPlaying) {
                    animateButtonToRandomPosition();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        };

        animX.addListener(repeatAnimationListener);

        animX.start();
        animY.start();
        animScaleX.start();
        animScaleY.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}