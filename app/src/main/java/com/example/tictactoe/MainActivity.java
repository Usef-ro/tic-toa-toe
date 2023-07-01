package com.example.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    ImageView about, setting, imageView_grid;
    AdView adView;
    AdRequest adRequest;
    TextView textView, status;
    ConstraintLayout background;


    Boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2,};
    int[][] winPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    View view1;
    ImageView img;
    String themeName;

    public void playerTap(View view) {
        img = (ImageView) view;
        view1 = view;
        int tappedImg = Integer.parseInt(img.getTag().toString());
        int tappedImge = tappedImg;
        if (!gameActive || (gameState[0] != 2 && gameState[1] != 2 && gameState[2] != 2
                && gameState[3] != 2 && gameState[4] != 2 && gameState[5] != 2 && gameState[6] != 2
                && gameState[7] != 2 && gameState[8] != 2)) {
            gameReset(view);
        }

        if (gameState[tappedImg] == 2 && gameActive) {
            gameState[tappedImg] = activePlayer;
            img.setTranslationX(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(checktheme_x());
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn Tap To Play");
            } else {
                img.setImageResource(checktheme_o());
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X 's Turn Tap To Play");
            }
            img.animate().translationXBy(1000f).setDuration(300);
        }

        //check if player has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]]
                    == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                //someone has win the game !!
                //Find out who is win !!
                String winstr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winstr = "X has WON";
//                    dialog(winstr);
                    vibrate();
                    wairt(winstr);
                } else {
                    winstr = "O has WON";
//                    dialog(winstr);
                    vibrate();
                    wairt(winstr);
                }
                //Display the winner on statusbar(textview)
                TextView status = findViewById(R.id.status);
                status.setText(winstr);
            }

        }

    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X 's Turn Tap To Play");

    }


    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    int u;
    int THEME_DEFAULT = 0;
    int THEME_DEFAULT_ = -1;
    int THEME_DARK = 1;
    /////////////////////////////////////////////////////////////////////////


    @Override
    protected void onStart() {
        u = new SharedPreferencesManager(getApplicationContext()).retrieveInt("theme", THEME_DEFAULT_);
        Log.e("setDefaultNightMode",u+"");

        if(u!=1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adView = findViewById(R.id.adView);
        about = findViewById(R.id.about);
        setting = findViewById(R.id.setting);
        textView = findViewById(R.id.textView);
        status = findViewById(R.id.status);
        background = findViewById(R.id.background);
        imageView_grid = findViewById(R.id.imageView_grid);

        u = new SharedPreferencesManager(getApplicationContext()).retrieveInt("theme", THEME_DEFAULT_);

//
        if (u==1) {
            imageView_grid.setImageResource(R.drawable.gride_white);
//            background.setBackgroundColor(Color.BLACK);
            status.setTextColor(Color.WHITE);
            textView.setTextColor(Color.WHITE);

        } else {
            imageView_grid.setImageResource(R.drawable.gride);
//            background.setBackgroundColor(Color.WHITE);


            status.setTextColor(Color.BLACK);
            textView.setTextColor(Color.BLACK);

        }


        about.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        });

        setting.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
            //finish();
        });
    }

    void dialog(String w) {
        MaterialAlertDialogBuilder d = new MaterialAlertDialogBuilder(MainActivity.this);
        d.setTitle("Win!");
        d.setMessage(w);
        d.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameReset(view1);
            }
        });

        d.setCancelable(false);
        d.create();
        d.show();
    }



    public   void vibrate() {
       int vibrate = new SharedPreferencesManager(getApplicationContext()).retrieveInt(Put.vibrate, 0);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(vibrateGet(vibrate), VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(vibrateGet(vibrate));
        }



    }

    int vibrateGet(int v){
            if(v==0){
              return 0;

            }else if(v==1){
                return 100;
            }else if(v==2){
                return 200;
            }else if(v==3){
                return 300;
            }else if(v==4){
                return 300;
            }else if(v==5){
                return 500;
            }else if(v==6){
                return 600;
            }else if(v==7){
                return 700;
            }else if(v==8){
                return 800;
            }else if(v==9){
                return 900;
            }else if(v==10){
                return 1000;
            }
            return 0;
    }

    void wairt(String w) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                dialog(w);
            }
        }, 100);

    }

    void ads() {
        // Initialize the Mobile Ads SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

                Toast.makeText(MainActivity.this, " sucesfull ", Toast.LENGTH_SHORT).show();
            }
        });


        adRequest = new AdRequest.Builder().build();
//        adView.setAdSize(AdSize.BANNER);
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Toast.makeText(MainActivity.this, "onAdFailedToLoad", Toast.LENGTH_SHORT).show();
                Log.e("onAdFailedToLoad ", "onAdFailedToLoad: " + loadAdError.getMessage());
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Toast.makeText(MainActivity.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
                Log.e("onAdLoaded ", "onAdLoaded");
            }
        });

    }


    int checktheme_x() {

        if (u==1) {
            return R.drawable.x_white;
        } else {
            return R.drawable.x;
        }

    }

    int checktheme_o() {
        if (u==1) {
            return R.drawable.o_white;
        } else {
            return R.drawable.o;
        }

    }

    private void dark_() {
        background.setBackgroundColor(Color.BLACK);
        status.setTextColor(Color.BLACK);
        textView.setTextColor(Color.BLACK);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        finish();
    }


}