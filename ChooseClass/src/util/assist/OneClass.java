package util.assist;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 描述的是一堂课的信息
 * 真的是就一节课 比如3-4之类的
 */
public class OneClass implements Serializable {
    ArrayList<Integer> weeks;
    int weekday;
    String place;
    int startTime;
    int endTime;

    public ArrayList<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(ArrayList<Integer> weeks) {
        this.weeks = weeks;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }


}
