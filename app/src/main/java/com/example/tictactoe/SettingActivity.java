package com.example.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import top.defaults.colorpicker.ColorPickerPopup;

public class SettingActivity extends AppCompatActivity {

//    private static final String PREFS_NAME = "prefs";
//    private static final String PREF_DARK_THEME = "dark_theme";
//    private static final String vibrate = "dark_theme";

    Button byn_color_picker;
    TextView get_color,vibrate_txt,txt_;
    SeekBar seekBar3;
    RadioButton dark_btn, light_btn;
    ImageView image_finish;
    RadioGroup group;
    Switch switch1;
    int THEME_DEFAULT = 0;
    int THEME_DEFAULT_ = -1;
    int THEME_DARK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int u = new SharedPreferencesManager(getApplicationContext()).retrieveInt("theme", THEME_DEFAULT_);
        Log.e("setDefaultNightMode",u+"");

        if(u!=1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }


        setContentView(R.layout.activity_setting);



        group = (RadioGroup) findViewById(R.id.group);

        byn_color_picker = findViewById(R.id.byn_color_picker);
        get_color = findViewById(R.id.get_color);
        dark_btn = findViewById(R.id.dark_btn);
        light_btn = findViewById(R.id.light_btn);
        image_finish = findViewById(R.id.image_finish);

        seekBar3 = findViewById(R.id.seekBar3);
        switch1 = findViewById(R.id.switch1);
        vibrate_txt = findViewById(R.id.vibrate_txt);
        txt_ = findViewById(R.id.txt_);

        int v_txt= new SharedPreferencesManager(this).retrieveInt(Put.vibrate,0);
       boolean y = new SharedPreferencesManager(this).retrieveBoolean("switch1",false);

       if(y){
           switch1.setChecked(true);
           Log.e("switch1setChecked",y+"");
           vibrate_txt.setTextColor(Color.WHITE);
           txt_.setTextColor(Color.WHITE);
       }else {
           switch1.setChecked(false);
           Log.e("switch1setChecked",y+"");

           vibrate_txt.setTextColor(Color.BLACK);
           txt_.setTextColor(Color.BLACK);
       }

        seekBar3.setProgress(v_txt);
       if(v_txt==0){
           vibrate_txt.setText("OFF");
       }else{
           vibrate_txt.setText(String.valueOf(v_txt));
       }

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                toggleTheme(isChecked);
                Log.e("switch1onCheckedChanged",isChecked+"");


                if(isChecked) {
//                    Utils.changeToTheme(SettingActivity.this, new SharedPreferencesManager(SettingActivity.this).storeInt("theme", THEME_DARK));
//
                    new SharedPreferencesManager(SettingActivity.this).storeInt("theme", THEME_DARK);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Log.e("switch1",isChecked+"");
                    new SharedPreferencesManager(SettingActivity.this).storeBoolean("switch1",isChecked);
                }else{
//                    Utils.changeToTheme(SettingActivity.this, new SharedPreferencesManager(SettingActivity.this).storeInt("theme", THEME_DEFAULT));

                    new SharedPreferencesManager(SettingActivity.this).storeInt("theme", THEME_DEFAULT);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    new SharedPreferencesManager(SettingActivity.this).storeBoolean("switch1",isChecked);
                    Log.e("switch1",isChecked+"");
                }

            }
        });


        image_finish.setOnClickListener(v -> {
            //startActivity(new Intent(SettingActivity.this, MainActivity.class));
            finish();
        });

        byn_color_picker.setOnClickListener(v -> {
            new ColorPickerPopup.Builder(this)
                    .initialColor(Color.RED) // Set initial color
                    .enableBrightness(true) // Enable brightness slider or not
                    .enableAlpha(true) // Enable alpha slider or not
                    .okTitle("Choose")
                    .cancelTitle("Cancel")
                    .showIndicator(true)
                    .showValue(true)
                    .build()
                    .show(v, new ColorPickerPopup.ColorPickerObserver() {
                        @Override
                        public void onColorPicked(int color) {
//                            sharedPreferences.edit().putInt("color",color).apply();
                        }
                    });

        });


        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

//                vibrate_txt.setText(p);
//                Toast.makeText(SettingActivity.this, ""+progress, Toast.LENGTH_SHORT).show();
//              sharedPreferences.edit().putString("vibrate",p).apply();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                String p = String.valueOf(seekBar.getProgress());
                vibrateSet(seekBar.getProgress(),p);

            }
        });


    }


    public void setThemee(String name) {
        // Create preference to store theme name
        SharedPreferences preferences = getSharedPreferences("Theme", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ThemeName", name);
        editor.apply();
        recreate();

    }


void vibrateSet(int progress,String p ){
    if(progress==0){
        vibrate_txt.setText("OFF");
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,0);


    }else if(progress==1){
        vibrate_txt.setText(p);
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,progress);

    }else if(progress==2){
        vibrate_txt.setText(p);
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,progress);

    }else if(progress==3){
        vibrate_txt.setText(p);
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,progress);

    }else if(progress==3){
        vibrate_txt.setText(p);
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,progress);

    }else if(progress==4){
        vibrate_txt.setText(p);
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,progress);

    }else if(progress==5){
        vibrate_txt.setText(p);
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,progress);

    }else if(progress==6){
        vibrate_txt.setText(p);
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,progress);

    }else if(progress==7){
        vibrate_txt.setText(p);
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,progress);

    }else if(progress==8){
        vibrate_txt.setText(p);
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,progress);

    }else if(progress==9){
        vibrate_txt.setText(p);
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,progress);

    }else if(progress==10){
        vibrate_txt.setText(p);
        new SharedPreferencesManager(SettingActivity.this).storeInt(Put.vibrate,progress);

    }
}



}


