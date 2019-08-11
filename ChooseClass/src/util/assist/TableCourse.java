package util.assist;

import java.io.Serializable;
import java.util.*;

/**
 * Created by xuhon on 2016/8/11.
 *
 * @author Hongxu Xu
 * @version 0.1
 */
public class TableCourse implements Serializable {
    private String code = "";
    private String name = "";
    private String credit = "";
    private String teacher = "";
    private String locationTime = "";
    //private int weekday = 0;//1->Monday ... 7 -> Sunday 0 -> null

    private boolean freeToListen = false;

    private static final long serialVersionUID = -7048272870067608021L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.substring(name.indexOf("]") + 1);
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        teacher = teacher.substring(teacher.indexOf("]") + 1);
        this.teacher = teacher;
    }

    public String getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(String locationTime) {
        this.locationTime = locationTime;
    }

    @Override
    public String toString() {
        return "{\"code\":\"" + code
                + "\",\"name\":\"" + name
                + "\",\"credit\":\"" + credit
                + "\",\"teacher\":\"" + teacher
                + "\",\"freeToListen\":\"" + freeToListen
                + "\",\"locationTime\":\"" + locationTime + "\"}";
    }


    public boolean isFreeToListen() {
        return freeToListen;
    }

    public void setFreeToListen(boolean freeToListen) {
        this.freeToListen = freeToListen;
    }

    public ArrayList<OneClass> parseTime(){
        String parse = getLocationTime();
        ArrayList<OneClass> classes = new ArrayList<>();
        String[] res= parse.split("\\),");
        for (int j = 0; j < res.length; j++) {
            //System.out.println("res[j]: "+res[j]);
           String[] cache=  res[j].split(" ");
           OneClass oneClass = new OneClass();
           oneClass.setWeeks(parseWeeksInfo(cache[0]));
           oneClass.setWeekday(parseWeekday(cache[1]));
           ArrayList<Integer> integer = parseClassTime(cache[1]);
           oneClass.setStartTime(integer.get(0));
           oneClass.setEndTime(integer.get(integer.size()-1));
           if (!cache[2].endsWith(")"))cache[2] += ")";
           oneClass.setPlace(cache[2]);
           classes.add(oneClass);
        }
        return classes;
    }


    private ArrayList<Integer> parseClassTime(String info) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        String pp = info.split("\\[")[1].split("\\]")[0];
        String[] ress = pp.split("-");
        if (ress.length == 1) {
            arrayList.add(Integer.parseInt(ress[0]));
            return arrayList;
        } else {
            for (int i = Integer.parseInt(ress[0]); i <= Integer.parseInt(ress[1]); i++) {
                arrayList.add(i);
            }
        }
    return  arrayList;
    }


    private int parseWeekday(String info){
        switch (info.charAt(0)){
            case '一':
                return 1;
            case '二':
                return 2;
            case '三':
                return 3;
            case '四':
                return 4;
            case '五':
                return 5;
            case '六':
                return 6;
            case '日':
                return 7;
        }
        return 0;
    }

    private ArrayList<Integer> parseWeeksInfo(String info){
        String[] rrr = info.split("周");

        String[] j = rrr[0].split(",");
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < j.length ; i++) {
            String[] res = j[i].split("-");
            if (res.length == 1){
                integers.add(Integer.parseInt(res[0]));
                continue;
            } else {
              for (i = Integer.parseInt(res[0]);i<= Integer.parseInt(res[1]);i++){
                  integers.add(i);
              }
            }
        }
        if (rrr.length!=1){
            if (rrr[1].equals("(双)")){
                for(int i = 0;i < integers.size();i++){
                    Integer number = integers.get(i);
                    if(number%2 != 0){
                        integers.remove(i);
                        i --;
                    }
                }
            }
            if (rrr[1].equals("(单)")){
                for(int i = 0;i < integers.size();i++){
                    Integer number = integers.get(i);
                    if(number%2 == 0){
                        integers.remove(i);
                        i --;
                    }
                }
            }
        }
        Collections.sort(integers);
        return integers;
    }

    public String getWeekday(){

        return  null;
    }
}
