package ui.plugin.calender;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import ui.component.CourseCard;
import ui.plugin.interfaces.p0Calender;
import util.assist.OneClass;
import util.assist.TableCourse;
import util.control.Regist;
import util.res.color.ColorIterator;
import util.res.color.Colors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class SchedulePane extends AnchorPane implements Serializable {
    public SchedulePane(){
        ImageView bg = new ImageView(new Image("/util/res/pic/bg_final_.png"));
        bg.setFitWidth(955);
        bg.setFitHeight(624);
        this.getChildren().add(bg);
        this.setBottomAnchor(bg,0.0);
        this.setRightAnchor(bg,0.0);
        bg.autosize();
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"),null,null)));
//        ImageView cal = new ImageView(new Image("/util/res/pic/timetable.png"));
////        cal.setFitWidth(955);
////        cal.setFitHeight(624);
//        this.getChildren().add(cal);
//        this.setTopAnchor(cal,0.0);
//        this.setLeftAnchor(cal,0.0);
//        cal.autosize();
        //this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"),null,null)));
    //        ArrayList<TableCourse> tableCourses = null;
//        try {
//            tableCourses = Regist.assist.getTableCourses(Regist.assist.getSemesters().get(0));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Label label3 = new Label();
//        label3.toFront();
//        label3.setWrapText(true);
//        label3.setMaxSize(250,600);
//        //label3.autosize();
//        label3.setFont(Font.font(14));
//        this.setTopAnchor(label3,30.0);
//        this.setRightAnchor(label3,5.0);
//        this.getChildren().add(label3);
//
//        //Colors colors = new Colors();
//        ColorIterator colors = new ColorIterator();
//        Iterator<Color> iterator = colors.iterator();
//        StringBuilder classCounter = new StringBuilder();
//        for (TableCourse course:
//                tableCourses) {
//            classCounter.append(course.getName()+"\n");
//            Color color = iterator.next();
//            System.out.println(color.toString());
//            ArrayList<OneClass> oneClasses = course.parseTime();
//            System.out.println(course.getName());
//            for (OneClass c :
//                    oneClasses) {
////                System.out.println( "c.getWeekday()"+ c.getWeekday());
//                double x = (c.getWeekday()-1)*100.0;
//                double y = (c.getStartTime()-1)*50+30;
//                double height = (c.getEndTime()-c.getStartTime()+1)*50;
//                //    StringBuilder stringBuilder = new StringBuilder(16);
//                //  c.getWeeks().forEach(e->stringBuilder.append(e+" "));
////                double width = 100;
////                System.out.println("rectangle:"+x+ " "+y +" "+ height+" "+ width);
//                CourseCard rectangle = new CourseCard(height,course.getName(),c.getPlace(),color);
//
//                rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        label3.setText("");
//                        String info = "课程信息\n课程名称："+course.getName()+"\n时间地点："+course.getLocationTime()+"\n学分："+course.getCredit()+"\n课程代码："+course.getCode();
//                        label3.setText(info);
//                    }
//                });
//                label3.setText("已选课程：\n"+classCounter.toString()+"\n\n点击课程来查看详细信息。");
//                this.getChildren().add(rectangle);
//                this.setTopAnchor(rectangle,y);
//                this.setLeftAnchor(rectangle,x);
//            }
//
//        }
        NetworkService networkService = new NetworkService();
        networkService.start();

        this.autosize();
    }
 class NetworkService extends Service<ArrayList<TableCourse>>{

     @Override
     protected Task<ArrayList<TableCourse>> createTask() {
         return new Task<ArrayList<TableCourse>>() {
             @Override
             protected void updateValue(ArrayList<TableCourse> tableCourses) {
                 super.updateValue(tableCourses);
                 ImageView cal = new ImageView(new Image("/util/res/pic/timetable.png"));
//        cal.setFitWidth(955);
//        cal.setFitHeight(624);
                 SchedulePane.this.getChildren().add(cal);
                 SchedulePane.this.setTopAnchor(cal,0.0);
                 SchedulePane.this.setLeftAnchor(cal,0.0);
                 cal.autosize();

                 Label label3 = new Label();
                 label3.toFront();
                 label3.setWrapText(true);
                 label3.setMaxSize(250,600);
                 //label3.autosize();
                 label3.setFont(Font.font(14));
                 SchedulePane.this.setTopAnchor(label3,30.0);
                 SchedulePane.this.setRightAnchor(label3,5.0);
                 SchedulePane.this.getChildren().add(label3);

                 //Colors colors = new Colors();
                 ColorIterator colors = new ColorIterator();
                 Iterator<Color> iterator = colors.iterator();
                 StringBuilder classCounter = new StringBuilder();
                 for (TableCourse course:
                         tableCourses) {
                     classCounter.append(course.getName()+"\n");
                     Color color = iterator.next();
                     System.out.println(color.toString());
                     ArrayList<OneClass> oneClasses = course.parseTime();
                     System.out.println(course.getName());
                     for (OneClass c :
                             oneClasses) {
//                System.out.println( "c.getWeekday()"+ c.getWeekday());
                         double x = (c.getWeekday()-1)*100.0;
                         double y = (c.getStartTime()-1)*50+30;
                         double height = (c.getEndTime()-c.getStartTime()+1)*50;
                         //    StringBuilder stringBuilder = new StringBuilder(16);
                         //  c.getWeeks().forEach(e->stringBuilder.append(e+" "));
//                double width = 100;
//                System.out.println("rectangle:"+x+ " "+y +" "+ height+" "+ width);
                         CourseCard rectangle = new CourseCard(height,course.getName(),c.getPlace(),color);

                         rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                             @Override
                             public void handle(MouseEvent event) {
                                 label3.setText("");
                                 String info = "课程信息\n课程名称："+course.getName()+"\n时间地点："+course.getLocationTime()+"\n学分："+course.getCredit()+"\n课程代码："+course.getCode();
                                 label3.setText(info);
                             }
                         });
                         label3.setText("已选课程：\n"+classCounter.toString()+"\n\n点击课程来查看详细信息。");
                         SchedulePane.this.getChildren().add(rectangle);
                         SchedulePane.this.setTopAnchor(rectangle,y);
                         SchedulePane.this.setLeftAnchor(rectangle,x);
                     }

                 }
                 p0Calender.label.setText("就绪");
             }

             @Override
             protected ArrayList<TableCourse> call() throws Exception {
                 ArrayList<TableCourse> tableCourses = null;
                 try {
                     tableCourses = Regist.assist.getTableCourses(Regist.assist.getSemesters().get(0));
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
                 return tableCourses;
             }
         };
     }
 }
}
