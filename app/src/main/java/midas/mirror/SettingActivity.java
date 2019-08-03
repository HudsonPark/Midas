package midas.mirror;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
    int hourOfDay, selectedMinute, selectedMonth, selectedDay, selectedYear;

    // 다이얼로그
    private static final int DATE_DIALOG_ID = 0;
    private static final int TIME_DIALOG_ID = 1;

    // View
    private EditText input_title;
    private EditText edt_loc;
    private EditText edt_content;
    private Button btn_time;
    private Button btn_date;
    private Button btn_complete;

    // 입력 데이터
    String title;
    String selected_day;
    String time;
    String location;
    String content;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // 뷰 초기화
        initView();

        // 날짜 정보 받기
        intent = getIntent();
        SelectDay sd = (SelectDay) intent.getSerializableExtra("selected_day");
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

                TimePickerDialog timePickerDialog = new TimePickerDialog(SettingActivity.this,timeSetListener,hourOfDay,minute,false);

                timePickerDialog.setCancelable(false);

                timePickerDialog.show();

                //showDialog(TIME_DIALOG_ID);
            }
        });
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이트피커 다이얼로그 구현

                DatePickerDialog datePickerDialog = new DatePickerDialog(SettingActivity.this,dateSetListener,year,month,day);

                datePickerDialog.setCancelable(false);

                datePickerDialog.show();

                //showDialog(DATE_DIALOG_ID);
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
                    location = edt_loc.getText().toString();
                    content = edt_content.getText().toString();

                    // 리스트 뷰에 띄어줄 정보 전달
                    intent = new Intent(getApplicationContext(), ListActivity.class);
                    SelectDay sd = new SelectDay(selected_day,title);
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
        btn_complete = findViewById(R.id.btn_complete);
        input_title = findViewById(R.id.input_title);

    }
/*
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
*/

    // DatePicker 리스너
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            selectedYear = year;
            selectedMonth = month+1;
            selectedDay = dayOfMonth;

            btn_date.setText(selectedYear + "년" + selectedMonth + "월" + selectedDay + "일");
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            SettingActivity.this.hourOfDay = hourOfDay;
            selectedMinute = minute;
            btn_time.setText(hourOfDay + "시" + selectedMinute + "분");
        }
    };
}