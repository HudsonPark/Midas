package midas.mirror;

import java.io.Serializable;

public class SelectDay implements Serializable {

    String selected_day;
    String title;

    public SelectDay(){}

    public SelectDay(String selected_day, String title) {
        this.selected_day = selected_day;
        this.title = title;
    }

    public String getSelected_day() {
        return selected_day;
    }

    public void setSelected_day(String selected_day) {
        this.selected_day = selected_day;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

}


