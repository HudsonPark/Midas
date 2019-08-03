package midas.mirror;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements Serializable {

    final int ADD_REQUEST_CODE = 1001;
    private ListView listView;
    private ListAdapter adapter;
    private CalListItem item;
    private TextView tv_date;
    private TextView tv_notify;
    private Button btn_setting;

    // SettingActivity로 이동하는 인텐트
    Intent intent;
    SelectDay selectDay = new SelectDay();
    ArrayList<CalListItem> Items = new ArrayList<CalListItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        // View 초기화
        initView();
        // 해당 날짜 텍스트 뷰에 출력
        intent = getIntent();
        final SelectDay sd = (SelectDay) intent.getSerializableExtra("selected_day");
        Log.d("sss",sd.getSelected_day());
        tv_date.setText(sd.getSelected_day());

        // data 날짜에 해당하는 리스트 정보 가져오기

        adapter = new ListAdapter(getApplicationContext(),R.layout.list_item,Items);
        listView = new ListView(getApplicationContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //  ListView 안의 아이템 수에 따른 View 변화
        if(adapter.getCount()==0){
            tv_notify.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        } else if(adapter.getCount()!=0){
            tv_notify.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }

        // SettingActivity 로 이동하는 인텐트 선언
        intent = new Intent(getApplicationContext(),SettingActivity.class);
        sd.setSelected_day(tv_date.getText().toString());
        // list의 item 클릭시
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      intent.putExtra("selected_day",sd);
                      // 어떻게 구현해야 할지
                      intent.putExtra("position",position);
                      startActivity(intent);
                }
            });
        // 추가 버튼 클릭시
        btn_setting.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                // SettingActivity로 이동하는 인텐트

                    intent.putExtra("selected_day",sd);
                    startActivityForResult(intent,ADD_REQUEST_CODE);
            }
        });
    }

    private void initView() {
        btn_setting = findViewById(R.id.btn_setting);
        tv_date = findViewById(R.id.tv_date);
        tv_notify = findViewById(R.id.tv_notify);
        listView = findViewById(R.id.listView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            switch (requestCode){
                // SettingActivity에서 정보 입력하고 되돌아올 경우
                case ADD_REQUEST_CODE :
                    selectDay = (SelectDay) data.getSerializableExtra("item");
                    tv_date.setText(selectDay.getSelected_day());
                    item = new CalListItem();
                    item.setTitle(selectDay.getTitle());
                    break;
            }



        }

    }
}
