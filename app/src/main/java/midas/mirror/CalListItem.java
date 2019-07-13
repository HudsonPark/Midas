package midas.mirror;

import android.util.Log;

public class CalListItem {

    private String selected_day;
    private String title;

    public CalListItem(){}

    public CalListItem(String selected_day, String title){
        this.selected_day = selected_day;
        this.title = title;
    }

    public String getSelected_day() {
        Log.d("main_selected", "item get : " + selected_day);

        return selected_day;
    }

    public void setSelected_day(String selected_day) {
        this.selected_day = selected_day;
        Log.d("main_selected", "item set : " + selected_day);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
