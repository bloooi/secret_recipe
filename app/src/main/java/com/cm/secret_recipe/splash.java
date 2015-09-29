package com.cm.secret_recipe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ijaebeom on 2015. 9. 14..
 */
public class splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        initialize();
    }

    private void initialize()
    {
        Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                finish();    // 액티비티 종료
            }
        };
        // .sendEmptyMessageDelayed안에 숫자를 바뀌면 스플레시 엑티비티 보여주는 시간이 달라짐. (1000이 1초)
        handler.sendEmptyMessageDelayed(0, 2000);
    }
}

