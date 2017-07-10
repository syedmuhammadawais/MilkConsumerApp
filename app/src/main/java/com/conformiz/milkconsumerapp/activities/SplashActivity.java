package com.conformiz.milkconsumerapp.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new MyTimer(3000, 1000).start();
    }

    private final class MyTimer extends CountDownTimer {

        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            if (SharedPreferenceUtil.getInstance(SplashActivity.this).getKeepSignInValue()) {
                Intent i = new Intent();
                i.setClass(SplashActivity.this, LoginActivity.class);
                Log.i("Splash", " onCreate ");
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent();
                i.setClass(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }
    }

}
