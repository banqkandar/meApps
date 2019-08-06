package com.iskandar.meapps.Views;
/*
 * tgl pengerjaan : 3 agustus 2019
 * nim : 10116121
 * nama : mohammad iskandar
 * kelas : IF-3
 * */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.iskandar.meapps.R;

public class SplashScreenActivity extends AppCompatActivity {
    private int loadTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplication(), ViewPagerActivity.class);
                startActivity(i);
                finish();
            }
        }, loadTime);
    }
}