package com.example.elderease;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MusicPlayer2 extends AppCompatActivity {

    MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3;
    ImageView playIcon,next,previous;
    TextView title;
    SeekBar seekBar;
    Runnable runnable;
    Handler handler = new Handler();
    String[] Relaxing_Sounds = {"Rise with the Sun", "Trip to the Beach", "Spiritual"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player2);

        seekBar = findViewById(R.id.seek_Bar);
        mediaPlayer1 = MediaPlayer.create(this, R.raw.sound1);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.sound2);
        mediaPlayer3 = MediaPlayer.create(this, R.raw.sound3);


        playIcon = findViewById(R.id.playIcon);
        next=findViewById(R.id.next);
        previous=findViewById(R.id.previous);
        title=findViewById(R.id.TitleText);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().contains(Relaxing_Sounds[0])) {
                    title.setText(Relaxing_Sounds[1]);
                    mediaPlayer1.stop();
                    mediaPlayer1.seekTo(0);
                    mediaPlayer2.seekTo(0);
                    mediaPlayer2.start();
                }
                else if (title.getText().toString().contains(Relaxing_Sounds[1])) {
                    title.setText(Relaxing_Sounds[2]);
                    mediaPlayer2.stop();
                    mediaPlayer2.seekTo(0);
                    mediaPlayer3.seekTo(0);
                    mediaPlayer3.start();
                }
                else{
                    title.setText(Relaxing_Sounds[0]);
                    mediaPlayer3.stop();
                    mediaPlayer3.seekTo(0);
                    mediaPlayer1.seekTo(0);
                    mediaPlayer1.start();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().contains(Relaxing_Sounds[0])) {
                    title.setText(Relaxing_Sounds[2]);
                    mediaPlayer1.stop();
                    mediaPlayer3.seekTo(0);
                    mediaPlayer3.start();
                }
                else if (title.getText().toString().contains(Relaxing_Sounds[1])) {
                    title.setText(Relaxing_Sounds[0]);
                    mediaPlayer2.stop();
                    mediaPlayer1.seekTo(0);
                    mediaPlayer1.start();
                }
                else{
                    title.setText(Relaxing_Sounds[1]);
                    mediaPlayer3.stop();
                    mediaPlayer2 .seekTo(0);
                    mediaPlayer2.start();
                }
            }
        });

        if (title.getText().toString().contains(Relaxing_Sounds[0])){
            seekBar = findViewById(R.id.seek_Bar);
            mediaPlayer1 = MediaPlayer.create(this, R.raw.sound1);
        }
        else if (title.getText().toString().contains(Relaxing_Sounds[1])){
            seekBar = findViewById(R.id.seek_Bar);
            mediaPlayer2 = MediaPlayer.create(this, R.raw.sound2);
        }
        else{
            seekBar = findViewById(R.id.seek_Bar);
            mediaPlayer3 = MediaPlayer.create(this, R.raw.sound3);
        }

                playIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(title.getText().toString().contains(Relaxing_Sounds[0])) {
                            if (!mediaPlayer1.isPlaying()) {
                                //mediaPlayer.seekTo(0);
                                mediaPlayer1.start();
                                playIcon.setImageResource(R.drawable.ic_baseline_pause_24);
                            } else {
                                mediaPlayer1.pause();
                                playIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                            }
                        }
                        else if(title.getText().toString().contains(Relaxing_Sounds[1])) {
                            if (!mediaPlayer2.isPlaying()) {
                                //mediaPlayer.seekTo(0);
                                mediaPlayer2.start();
                                playIcon.setImageResource(R.drawable.ic_baseline_pause_24);
                            } else {
                                mediaPlayer2.pause();
                                playIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                            }
                        }
                        else if(title.getText().toString().contains(Relaxing_Sounds[2])) {
                            if (!mediaPlayer3.isPlaying()) {
                                //mediaPlayer.seekTo(0);
                                mediaPlayer3.start();
                                playIcon.setImageResource(R.drawable.ic_baseline_pause_24);
                            } else {
                                mediaPlayer3.pause();
                                playIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                            }
                        }
                    }
                });



        mediaPlayer1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setMax(mediaPlayer.getDuration());
                playCycle();
            }
        });
        mediaPlayer2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setMax(mediaPlayer.getDuration());
                playCycle();
            }
        });
        mediaPlayer3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setMax(mediaPlayer.getDuration());
                playCycle();
            }
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if (input) {
                    mediaPlayer1.seekTo(progress);
                    mediaPlayer2.seekTo(progress);
                    mediaPlayer3.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void playCycle(){
        seekBar.setProgress(mediaPlayer1.getCurrentPosition());
        seekBar.setProgress(mediaPlayer2.getCurrentPosition());
        seekBar.setProgress(mediaPlayer3.getCurrentPosition());
        if(mediaPlayer1.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable,1000);
        }
        else if(mediaPlayer2.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable,1000);
        }
        else if(mediaPlayer3.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }
}
