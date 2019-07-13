package midas.mirror;

public class SettingVO {

    String title;
    String date;
    String time;
    String loc;
    String content;

    public SettingVO(){}

    public SettingVO(String title, String date, String time, String loc, String content){
        this.title = title;
        this.date = date;
        this.time = time;
        this.loc = loc;
        this.content = content;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
