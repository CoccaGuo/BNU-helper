package ui.plugin.movie.Scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import ui.plugin.movie.card.Card;
import ui.plugin.movie.card.CardTest;
import ui.plugin.movie.download.Download;
import ui.plugin.movie.util.VideoItem;
import util.control.ControlledStage;
import util.control.StageController;


import java.io.IOException;



public class ItemCont implements ControlledStage {
    StageController myStageController;
    @FXML
    private TextArea info;

    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Button dlBtn;
    @FXML
    private TextArea summary;

    static private VideoItem item;


    public static VideoItem getItem(){

        return  item;
    }


    @FXML
    void initialize(){
        item = Card.choosedItem;
        start();
    }
    public void start(){
        name.setText(item.getName());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("导演："+item.getDirector()+"\n");
        stringBuilder.append("编剧："+item.getScreenwriter()+"\n");
        stringBuilder.append("主演："+item.getActor()+"\n");
        stringBuilder.append(item.getLanguage()+"\n");
        stringBuilder.append(item.getRegion()+"\n");
        stringBuilder.append(item.getEpisode()+"\n");
        stringBuilder.append(item.getEpiTime()+"\n");
        stringBuilder.append(item.getShowTime()+"\n");
        stringBuilder.append(item.getRefreshTime()+"\n");
        stringBuilder.append("评分："+item.getScore()+"\n");
        info.setText(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        for (int i = 0; i <item.getSummary().length ; i++) {
            stringBuilder.append(item.getSummary()[i]+"\n");
        }
        summary.setText(stringBuilder.toString());
        img.setImage(new Image(item.picUrl));

    }
    @FXML
    public void download(){
        Stage primaryStage =  new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/plugin/movie/Scenes/dl.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("download");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(CardTest.class.getResource("/ui/plugin/movie/css/default.css").toExternalForm());
        primaryStage.show();

    }


    @Override
    public void setStageController(StageController stageController) {
        this.myStageController = stageController;
    }


}
