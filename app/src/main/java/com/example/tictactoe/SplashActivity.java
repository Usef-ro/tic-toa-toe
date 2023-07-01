package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        LinearLayout layout = findViewById(R.id.main_splash);
//
//        GradientDrawable gd = new GradientDrawable(
//                GradientDrawable.Orientation.TOP_BOTTOM,
//                new int[] {0xFF3E66B1,0xFF131313});
//        gd.setCornerRadius(0f);
//        layout.setBackgroundDrawable(gd);

//        Thread.sleep();
//        Intent newe=new Intent(this,MainActivity.class);
//        newe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(newe);
//        finish();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent newe = new Intent(SplashActivity.this, MainActivity.class);
                newe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(newe);
                finish();
            }
        }, 2000);


    }


}