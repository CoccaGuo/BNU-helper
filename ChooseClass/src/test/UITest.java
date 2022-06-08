package test;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import ui.animation.InformationBox;
import ui.component.ChooseClassItem;
import ui.component.ExamResultItem;
import ui.component.Website;
import util.res.color.Colors;

import java.awt.*;
import java.util.Iterator;

public class UITest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

      // Image image = new Image("/util/res/pic/logo.png");
     // CustomedVBox customedVBox = new CustomedVBox("/util/res/pic/logo.png","532");
        //p0Calender calender = new p0Calender();
       // Regist.assist = new SchoolworkAssist("201711140121","CoccaGuo1998");
        FlowPane parent = new FlowPane();
        parent.setPrefWidth(955);
        parent.setPrefHeight(800);
        Scene scene = new Scene(parent);

        ChooseClassItem item =new ChooseClassItem("理论力学xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","煞风景可视电话发送卡粉红色法法","大家都女ID尼德兰哦按 啊","333");
        parent.getChildren().add(item);

        ChooseClassItem item2 =new ChooseClassItem("理论力学","煞风景可视电话发送卡粉红色法法xxxxxxxxxxxxxxxxxxx","大家都女ID尼德兰哦按 xxxxxxxxxxxxxxxxxxxxxxxx啊","999/999");
        parent.getChildren().add(item2);
       // Website website = new Website("体育打卡", Color.GOLD,"http://jsty.bnu.edu.cn/view/sport/student/index");
      //  parent.getChildren().add(website);

//        ExamResultItem examResultItem =new ExamResultItem("[2310038641]马克思主义基本原理马克思主义基本原理马克思主义基本原理马克思主义基本原理马克思主义基本原理马克思主义基本原理","81.0","91.5","74.0","通识教育课程类/家国情怀与价值理想模块","2.0");
//        parent.getChildren().addAll(examResultItem);
       // InformationBox informationBox =new InformationBox(parent, "hello", 3000);
//        informationBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Animation.Status status = informationBox.trans.getStatus();
//                if (status.equals(Animation.Status.PAUSED))
//                informationBox.trans.play();
//                if (status.equals(Animation.Status.RUNNING))
//                    informationBox.trans.pause();
//            }
//        });
//        informationBox.trans.setAutoReverse(true);
//        informationBox.trans.setInterpolator(Interpolator.EASE_BOTH);
//        informationBox.trans.setCycleCount(20);
//        Colors colors = new Colors();
//        Iterator<Color> iterator = colors.iterator();
//        informationBox.trans.statusProperty().addListener(new ChangeListener<Animation.Status>() {
//            @Override
//            public void changed(ObservableValue<? extends Animation.Status> observable, Animation.Status oldValue, Animation.Status newValue) {
//                informationBox.setFill(Colors.toFXColor(iterator.next()));
//            }
//        });
   // parent.getChildren().add(informationBox);
     // informationBox.setFill(Paint.valueOf("#FF4040"));

   //     informationBox.show();
        primaryStage.setScene(scene);
        primaryStage.setTitle("ui_test");
        primaryStage.show();
    }
}
