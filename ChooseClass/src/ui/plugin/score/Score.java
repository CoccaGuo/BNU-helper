package ui.plugin.score;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ui.component.ExamResultItem;
import ui.plugin.interfaces.pScoreStat;
import util.assist.ExamScore;
import util.assist.NeedLoginException;
import util.assist.Semester;
import util.control.*;
import util.res.color.ColorIterator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Score implements Listenable{

    @FXML
    private ChoiceBox<Semester> select;

    @FXML
    private Button listen;

    @FXML
    private FlowPane pane;

    @FXML
    void listen(ActionEvent event) {
//        try {
//            Stage stage = new Stage();
//            Parent ap = FXMLLoader.load(getClass().getResource("/ui/plugin/listener/listen.fxml"));
//            Scene scene = new Scene(ap);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        listen.setText("监听中");
        listen.setDisable(true);
        System.out.println("监听中");
        listener.setFreshTime(10);
        listener().listen();
    }



    @FXML
    void fetch(ActionEvent event) {
        pScoreStat.label.setText("成绩查询中……");
        pane.getChildren().clear();
        FetchResultService resultService = new FetchResultService();
        resultService.start();
    }

    @FXML
    void initialize(){
        pScoreStat.label.setText("成绩查询模块初始化中……");
        FetchSemetersService semetersService = new FetchSemetersService();
        semetersService.start();
        select.setConverter(new StringConverter<Semester>() {
            @Override
            public String toString(Semester object) {
                return object.getName();
            }

            @Override
            public Semester fromString(String string) {
                return null;
            }
        });
    }



    @Override
    public Listener listener() {
        return listener;
    }

 Listener<ArrayList<ExamScore>> listener = new Listener<ArrayList<ExamScore>>() {
    @Override
    public ArrayList<ExamScore> refresh() {
        try {
            return fetch0();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NeedLoginException e) {
            Errorer.error(pScoreStat.index,e);
        }
        return null;
    }

    @Override
    public Filter<ArrayList<ExamScore>> filter() {
        return filter;
    }

    @Override
    public Alerter alerter() {
        return new Alerter(Alerter.SYSTEM_CALL,pScoreStat.index);
    }
};
    Filter<ArrayList<ExamScore>> filter = new Filter<ArrayList<ExamScore>>() {
        @Override
        public String compareEach(ArrayList<ExamScore> t1, ArrayList<ExamScore> t2) {
            if (t1.size()==t2.size()){
                Platform.runLater(() -> pScoreStat.label.setText("就绪-成绩监听中"));
                System.out.println("listening score changes... nothing happened.");
                return null;
            }
            for (int i = 0; i <t1.size() ; i++) {
                t2.remove(t1.get(i));
            }
            try {
                Platform.runLater(() -> pScoreStat.label.setText(t2.get(0).getCourseName()+":成绩有新动态"));
                return t2.get(0).getCourseName()+" 成绩更新了";
            }catch (Exception e){

            }
            Platform.runLater(() -> pScoreStat.label.setText("监听到成绩变化"));
            return "发生未知的成绩变化";
        }

    };

    private class FetchResultService extends Service<ArrayList<ExamScore>>{

        @Override
        protected Task<ArrayList<ExamScore>> createTask() {
            return new Task<ArrayList<ExamScore>>() {
                @Override
                protected void updateValue(ArrayList<ExamScore> value) {
                    super.updateValue(value);
                    ColorIterator colors = new ColorIterator();
                    Iterator<Color> iterator = colors.iterator();
                    if (value.isEmpty())pane.getChildren().add(new Label("nothing to show here"));
                    for (ExamScore score:
                        value ) {

                        ExamResultItem examResultItem = new ExamResultItem(score.getCourseName().split("\\]")[1],score.getScore(),score.getUsualScore(),score.getExamScore(),score.getClassification(),score.getCourseCredit());
                        examResultItem.setColor(iterator.next());
                        Score.this.pane.getChildren().add(examResultItem);
                    }
                    pScoreStat.label.setText("就绪");
                }

                @Override
                protected ArrayList<ExamScore> call() throws Exception {
                    return   fetch0();
                }
            };
        }
    }

    private  ArrayList<ExamScore> fetch0() throws IOException, NeedLoginException {
        System.out.println("fetch scores...");
        Platform.runLater(() -> pScoreStat.label.setText("查询成绩..."));
        System.out.println("监听学期："+select.getSelectionModel().getSelectedItem().getName());
        int years = Integer.parseInt(select.getSelectionModel().getSelectedItem().getYear());
        int term = Integer.parseInt(select.getSelectionModel().getSelectedItem().getTerm());
        ArrayList<ExamScore> examScores = Regist.assist.getExamScores(years,term,true);
        examScores.addAll(Regist.assist.getExamScores(years,term,false));
        return examScores;
    }


   private class FetchSemetersService extends Service<ArrayList<Semester>>{

        @Override
        protected Task<ArrayList<Semester>> createTask() {
            return new Task<ArrayList<Semester>>() {
                @Override
                protected void updateValue(ArrayList<Semester> value) {
                    super.updateValue(value);
                    select.getItems().addAll(value);
                    pScoreStat.label.setText("就绪");
                }

                @Override
                protected ArrayList<Semester> call() throws Exception {
                   ArrayList<Semester> semesters = Regist.assist.getSemesters();
                    return semesters;
                }
            };
        }
    }
}