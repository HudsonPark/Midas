package midas.mirror;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int Intro_time = 1;
        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(),Intro_time); // 0.001초 후에 hd handler 실행  2000ms = 2초

    }

    private class splashhandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(),MainActivity.class));
            //로딩이 끝난 후, Main으로 이동
            finish();
            // SplashActivity 제거
        }
    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }



    }

