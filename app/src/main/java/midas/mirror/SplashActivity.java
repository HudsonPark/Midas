package midas.mirror;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int Intro_time = 1000;
        Handler handler = new Handler();
        handler.postDelayed(new splashhandler(), Intro_time); // 0.001초 후에 hd handler 실행  2000ms = 2초

    }

    private class splashhandler implements Runnable {

        public void run() {

            //로딩이 끝난 후, Main으로 이동
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

            // SplashActivity 제거
            finish();
        }
    }

    @Override
    public void onBackPressed() {

        //초반 플래시 화면에서 뒤로가기 버튼 못누르게 함

    }
}

