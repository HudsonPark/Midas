package midas.mirror;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SettingActivity extends AppCompatActivity {

    Intent intent;
    // 년, 월, 일, 시, 분
    Calendar calendar = Calendar.getInstance();
    int hourofDay = calendar.get(Calendar.HOUR_OF_DAY) + 9;
    int minute = calendar.get(Calendar.MINUTE);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int year = calendar.get(Calendar.YEAR);
    int hourofDay_x, minute_x, month_x, day_x, year_x;

    // 다이얼로그
    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;

    // View
    EditText input_title;
    EditText edt_loc;
    EditText edt_content;
    Button btn_time;
    Button btn_date;
    Button btn_back;
    Button btn_complete;
    String title;
    String selected_day;
    String time;
    String loc;
    String content;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midas_setting);
        // 뷰 초기화
        initView();
        // 날짜 정보 받기
        intent = getIntent();
        Selected_day sd = (Selected_day) intent.getSerializableExtra("selected_day");
        selected_day = sd.getSelected_day();
        // 날짜 정보 셋팅
        btn_date.setText(selected_day);
        // 현재 시간 btn_time에 셋팅
        btn_time.setText(hourofDay + "시" + minute + "분");

        // 시간 버튼 클릭시
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 커스텀 타임피커 다이얼로그 구현
                showDialog(TIME_DIALOG_ID);
            }
        });
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이트피커 다이얼로그 구현
                showDialog(DATE_DIALOG_ID);
            }
        });


        // 버튼 클릭시 ListActivity로 이동하는 기능
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ListActivity.class);
                Selected_day sd = new Selected_day();
                sd.setSelected_day(btn_date.getText().toString());
                intent.putExtra("selected_day", sd);
                startActivity(intent);
                finish();
            }
        });
        // 모든 내용 입력 후 complete 버튼을 눌렀을 시
        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 타이틀 부분

                if (input_title.getText().toString() != null) {
                    // 데이터 전송하는 부분
                    title = input_title.getText().toString();
                    selected_day = btn_date.getText().toString();
                    time = btn_time.getText().toString();
                    loc = edt_loc.getText().toString();
                    content = edt_content.getText().toString();

                    // 리스트 뷰에 띄어줄 정보 전달
                    intent = new Intent(getApplicationContext(), ListActivity.class);
                    Selected_day sd = new Selected_day(selected_day,title);
                    intent.putExtra("item",sd);
                    // ListActivity로 이동
                    startActivity(intent);
                    finish();
                } else {
                    // 저장되지 않고 토스트 창으로 경고
                    Toast.makeText(getApplicationContext(),
                            "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        btn_date = findViewById(R.id.btn_date);
        btn_time = findViewById(R.id.btn_time);
        edt_loc = findViewById(R.id.edt_loc);
        edt_content = findViewById(R.id.edt_content);
        btn_back = findViewById(R.id.btn_back);
        btn_complete = findViewById(R.id.btn_complete);
        input_title = findViewById(R.id.input_title);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, year, month, day);

            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, mTimeSetListener, hourofDay, minute, false);
        }

        return null;
    }

    // DatePicker 리스너
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month+1;
            day_x = dayOfMonth;
            btn_date.setText(year_x + "년" + month_x + "월" + day_x + "일");
        }
    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hourofDay_x = hourOfDay;
            minute_x = minute;
            btn_time.setText(hourOfDay + "시" + minute_x + "분");
        }
    };
}