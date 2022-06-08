package ui.plugin.choose;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.component.ChooseClassItem;
import util.assist.NeedLoginException;
import util.assist.PlanCourse;
import util.control.Regist;
import util.res.color.ColorIterator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Choose {

    @FXML private FlowPane pane;

    @FXML
    void initialize(){
        try {
            ArrayList<PlanCourse> planCourses = Regist.assist.getPlanCourses(true);
            ColorIterator colors = new ColorIterator();
            Iterator<Color> iterator = colors.iterator();
            for (PlanCourse planCours : planCourses) {
                ChooseClassItem e = new ChooseClassItem(planCours.getCourseName(), planCours.getType1(), planCours.getType2(), planCours.getType3());
                e.setColor(iterator.next());
                pane.getChildren().add(e);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NeedLoginException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listen(ActionEvent event) {
        Stage primaryStage =  new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/plugin/choose/listen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("监听设置");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        //scene.getStylesheets().add(CardTest.class.getResource("/ui/plugin/movie/css/default.css").toExternalForm());
        primaryStage.show();
    }
}
