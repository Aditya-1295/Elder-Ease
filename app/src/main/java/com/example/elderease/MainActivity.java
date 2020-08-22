package com.example.elderease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView splashLogo;
    TextView slogan,appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


//        Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


//        Declarations of button and textView

        splashLogo = findViewById(R.id.splashLogo);
        slogan = findViewById(R.id.slogan);
        appName = findViewById(R.id.appName);

//       Applying animation to respective components

        splashLogo.setAnimation(topAnim);
        appName.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

    }
}