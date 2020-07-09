package com.kiran.project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiran.project.R;
import com.kiran.project.Utils.DatabaseHandler;


public class Splash extends AppCompatActivity {
    TextView welcome_text;
    ImageView welcome_img;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        welcome_text=(TextView) findViewById(R.id.welcome_text);
        welcome_img = (ImageView)  findViewById(R.id.welcome_img);

        Animation animation = AnimationUtils.loadAnimation(Splash.this,R.anim.myanimation);
        welcome_img.startAnimation(animation);
        welcome_text.startAnimation(animation);
        db = new DatabaseHandler(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (db.checkUser()) {
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i = new Intent(getApplicationContext(), Signup.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 3000);

    }
}
