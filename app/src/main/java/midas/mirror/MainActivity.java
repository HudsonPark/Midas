package midas.mirror;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements ExitDialogFragment.ExitDialogListener {

    private final int CHECK_COMFIRM_PRESSED = 1000;
    private ExitDialogFragment dialog;
    private GridView gd_view;
    private MonthAdapter monthView;
    private TextView tv_month;
    private Intent intent;
    private Button btn_next;
    private Button btn_prev;
    private String data;
    private int curYear;
    private int curMonth;
    private int day = 0;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 앱 화면 세로로 고정
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 뷰 초기화
        initView();

        // 월별 캘린더 뷰 객체 참조
        monthView = new MonthAdapter(this);
        gd_view.setAdapter(monthView);

        // 리스너 설정
        gd_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 현재 선택한 일자 정보 표시
                MonthItem curItem = (MonthItem) monthView.getItem(position);
                day = curItem.getDay();


                // 선택된 년, 월, 일 정보 저장
                Selected_day sd = new Selected_day();
                sd.setSelected_day(tv_month.getText().toString() + " " + day + "일");

                // ListActivity로 이동하는 인텐트
                intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("selected_day", sd);
                startActivity(intent);

            }
        });
        setMonthText();

        // 이전 월로 넘어가는 이벤트 처리
        btn_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthView.setPreviousMonth();
                monthView.notifyDataSetChanged();
                setMonthText();
            }
        });

        // 다음 월로 넘어가는 이벤트 처리

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthView.setNextMonth();
                monthView.notifyDataSetChanged();
                setMonthText();
            }
        });
    }

    private void initView() {
        gd_view = findViewById(R.id.gd_view);
        btn_prev = findViewById(R.id.btn_prev);
        btn_next = findViewById(R.id.btn_next);
        tv_month = findViewById(R.id.tv_month);
    }


    // 월 표시 텍스트 설정

    private void setMonthText() {
        curYear = monthView.getCurYear();
        curMonth = monthView.getCurMonth();
        tv_month.setText(curYear + "년 " + (curMonth + 1) + "월");
    }

    @Override
    public void onBackPressed() {
        // 버튼 클릭 시 종료 유무를 물어보는 다이얼로그 생성
        ExitDialogFragment exitDialogFragment = new ExitDialogFragment();
        exitDialogFragment.show(getSupportFragmentManager(), "exit");
    }


    @Override
    public void OnPositiveListener(DialogFragment dialog) {
        MainActivity.this.finish();
        dialog.dismiss();

    }
}

