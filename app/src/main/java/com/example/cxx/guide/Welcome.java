package com.example.cxx.guide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;

public class Welcome extends AppCompatActivity {
    private boolean isFirstIn = false;
    private static final int TIME = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init(){
        SharedPreferences sharedPreferences = getSharedPreferences("jike",MODE_PRIVATE);
        isFirstIn = sharedPreferences.getBoolean("isFirstIn",true);
        if(!isFirstIn){
            handler.sendEmptyMessageDelayed(GO_HOME,TIME);
        }else{
            handler.sendEmptyMessageDelayed(GO_GUIDE,TIME);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putBoolean("isFirstIn",false);
            editor.commit();
        }
    }
    private void goHome(){
        Intent i = new Intent(Welcome.this,MainActivity.class);
        startActivity(i);
        finish();
    }
    private void goGuide(){
        Intent i = new Intent(Welcome.this,GuideActivity.class);
        startActivity(i);
        finish();
    }
}
