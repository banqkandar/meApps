package com.iskandar.meapps.Views;
/*
 * tgl pengerjaan : 3 agustus 2019
 * nim : 10116121
 * nama : mohammad iskandar
 * kelas : IF-3
 * */
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.iskandar.meapps.Models.ViewPagerItem;
import com.iskandar.meapps.Presenter.ViewPagerAdapter;
import com.iskandar.meapps.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    ViewPagerAdapter vpAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnMulai;
    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make the activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //ketika activity ini dijalankan, kita cek dulu, apa kebuka sebelumnya atau tidak
        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(mainActivity);
            finish();
        }
        setContentView(R.layout.activity_view_pager);

        //hide the action bar
//        getSupportActionBar().hide();

        //ini view
        tabIndicator = findViewById(R.id.tab_indicator);
        btnNext = findViewById(R.id.btn_next);
        btnMulai = findViewById(R.id.btn_mulai);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

//        fill list screen
        final List<ViewPagerItem> mList = new ArrayList<>();
        mList.add(new ViewPagerItem("Hai Everyone..", "Apa kabar dengan mu hari ini, selalu bersyukur untuk hari ini dimana dan kapanpun anda berada. ", R.drawable.vp1));
        mList.add(new ViewPagerItem("My Profile", "Tahukah Anda?? aplikasi ini sering digunakan oleh hotman paris hutapea, aplikasi ini gampang dan fleksibel.", R.drawable.vp2));
        mList.add(new ViewPagerItem("List Friends", "Simpan kontak temanmu untuk ngobrol bareng, dengan memakai aplikasi ini kamu dengan mudah menghubungi temanmu.", R.drawable.vp3));

        //setup viewpager
        viewPager = findViewById(R.id.screen_viewpager);
        vpAdapter = new ViewPagerAdapter(this, mList);
        viewPager.setAdapter(vpAdapter);

        // setup tabLayout with viewpager
        tabIndicator.setupWithViewPager(viewPager);

        //nest button onclick listener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = viewPager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    viewPager.setCurrentItem(position);
                }

                if (position == mList.size() - 1) { // ketika kita sampe ke laman terakhir
                    // TODO : show the GETSTARTED button and hide the indicator and the next button
                    loadLastScreen();
                }

            }
        });

        //tablayout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //tombol mulai click listener
        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open main activity
                Intent mainActivity = new Intent(ViewPagerActivity.this, LoginActivity.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();
            }
        });
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnMulai.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        // TODO : Add an animation the getstarted button
        //setup animation
        btnMulai.setAnimation(btnAnim);

    }
}
