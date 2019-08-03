package midas.mirror;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int CHECK_COMFIRM_PRESSED = 1000;
    private GridView gd_view;
    private TextView tv_month;
    private Button btn_prev;
    private Button btn_next;
    private MonthAdapter monthAdapter;
    private String data;
    private Intent intent;
    private int curYear;
    private int curMonth;
    private int day = 0;
    private int pressCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰 초기화
        initView();

        // 앱 화면 세로로 고정
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // 월별 캘린더 뷰 객체 참조
        monthAdapter = new MonthAdapter(this);

        gd_view.setAdapter(monthAdapter);

        // 리스너 설정
        gd_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 현재 선택한 일자 정보 표시
                MonthItem curItem = (MonthItem) monthAdapter.getItem(position);
                day = curItem.getDay();

                // 선택된 년, 월, 일 정보 저장
                SelectDay sd = new SelectDay();
                sd.setSelected_day(tv_month.getText().toString() + " " + day + "일");

                // ListActivity로 이동하는 인텐트
                intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("selected_day", sd);
                startActivity(intent);

            }
        });
        setMonthText();
    }

    private void initView() {

        gd_view = findViewById(R.id.gd_view);
        tv_month = findViewById(R.id.tv_month);
        btn_prev = findViewById(R.id.btn_prev);
        btn_next = findViewById(R.id.btn_next);
        btn_prev.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    // 월 표시 텍스트 설정

    private void setMonthText() {

        curYear = monthAdapter.getCurYear();
        curMonth = monthAdapter.getCurMonth();
        tv_month.setText(curYear + "년 " + (curMonth + 1) + "월");
    }

    @Override
    public void onBackPressed() {

        pressCount++;

        if (pressCount==2){

            finish();

        } else {

            Toast.makeText(this, "한 번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        // 이전 버튼 클릭 시
        if (v.getId() == R.id.btn_prev) {

            monthAdapter.setPreviousMonth();

            // 다음 버튼 클릭 시
        } else if (v.getId() == R.id.btn_next) {

            monthAdapter.setNextMonth();

        }

        // 뷰에 변경 알림
        monthAdapter.notifyDataSetChanged();

        // 텍스트 뷰 월 표시 변경
        setMonthText();
    }
}

