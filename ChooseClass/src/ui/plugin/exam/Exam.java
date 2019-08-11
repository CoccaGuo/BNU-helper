package ui.plugin.exam;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import ui.component.ExamArrItem;
import ui.plugin.interfaces.pScoreStat;
import util.assist.ExamArrangement;
import util.assist.ExamRound;
import util.assist.NeedLoginException;
import util.control.Regist;
import util.res.color.ColorIterator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Exam {

    @FXML
    private ChoiceBox<ExamRound> select;

    @FXML
    private FlowPane pane;




    @FXML
    void fetch(ActionEvent event) {
        pScoreStat.label.setText("查询中……");
        pane.getChildren().clear();
        FetchResultService resultService = new FetchResultService();
        resultService.start();
    }

    @FXML
    void initialize(){
        pScoreStat.label.setText("考试安排查询模块初始化中……");
        FetchSemetersService semetersService = new FetchSemetersService();
        semetersService.start();
        select.setConverter(new StringConverter<ExamRound>() {
            @Override
            public String toString(ExamRound object) {
                return object.getName();
            }

            @Override
            public ExamRound fromString(String string) {
                return null;
            }
        });
    }





    private class FetchResultService extends Service<ArrayList<ExamArrangement>>{

        @Override
        protected Task<ArrayList<ExamArrangement>> createTask() {
            return new Task<ArrayList<ExamArrangement>>() {
                @Override
                protected void updateValue(ArrayList<ExamArrangement> value) {
                    super.updateValue(value);
                    ColorIterator colors = new ColorIterator();
                    Iterator<Color> iterator = colors.iterator();
                    if (value.isEmpty())pane.getChildren().add(new Label("nothing to show here"));
                    for (ExamArrangement arr:
                        value ) {
                        double leftDays = (double)(arr.getBeginTime().getTimeInMillis() - System.currentTimeMillis())/(1000*24*3600);
                        String left = "已结束";
                        if (leftDays>0) left = String.format("%.1f",leftDays)+"天";
                        ExamArrItem examResultItem = new ExamArrItem(arr.getCourseName(),left,arr.getLocation(),arr.getSeat(),arr.getTimeString(),arr.getExamType());
                        examResultItem.setColor(iterator.next());
                        Exam.this.pane.getChildren().add(examResultItem);
                    }
                    pScoreStat.label.setText("就绪");
                }

                @Override
                protected ArrayList<ExamArrangement> call() throws Exception {
                    return   fetch0();
                }
            };
        }
    }

    private ArrayList<ExamArrangement> fetch0() throws IOException, NeedLoginException {
        System.out.println("fetch scores");
        Platform.runLater(() -> pScoreStat.label.setText("查询考试安排..."));
        ArrayList<ExamArrangement> examScores = Regist.assist.getExamArrangement(select.getSelectionModel().getSelectedItem());
        //examScores.addAll(Regist.assist.getExamScores(years,term,false));
        return examScores;
    }


   private class FetchSemetersService extends Service<ArrayList<ExamRound>>{

        @Override
        protected Task<ArrayList<ExamRound>> createTask() {
            return new Task<ArrayList<ExamRound>>() {
                @Override
                protected void updateValue(ArrayList<ExamRound> value) {
                    super.updateValue(value);
                    select.getItems().addAll(value);
                    pScoreStat.label.setText("就绪");
                }

                @Override
                protected ArrayList<ExamRound> call() throws Exception {
                   ArrayList<ExamRound> examRounds = Regist.assist.getExamRounds();
                    return examRounds;
                }
            };
        }
    }
}