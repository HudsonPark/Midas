package midas.mirror;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListItemView extends LinearLayout {
    private CalListItem items;
    TextView list_item;

    public ListItemView(Context context) {
        super(context);
        init(context);
    }

    public ListItemView(Context context, AttributeSet attrs) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        // 레이아웃 인플레이터에 사용하여 사용할 레이아웃과 아이템을 설정
        // 레이아웃 인플레이터는 xml 레이아웃의 정보를 객체화하기 위해 쓰임

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item, this, false);

        // 레이아웃에 있는 id 값 연결
        list_item = findViewById(R.id.list_item);

    }

    public CalListItem getItems() {
        return items;
    }

    public void setItems(CalListItem items) {
        this.items = items;
    }

    public CalListItem getTitle() {
        return items;
    }
}
