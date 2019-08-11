package ui.plugin.movie.download;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ui.plugin.movie.util.VideoItem;


public class Download extends AnchorPane{

    @FXML
    private Label num;

    @FXML
    private AnchorPane node;
    int movCount;
    VideoItem video;
    public boolean isChoosed = false;
    public Download(VideoItem videoItem,int movCount){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("download.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        num.setText(movCount+"");
        this.movCount = movCount;
        this.video = videoItem;
    }

    @FXML
    void choose(MouseEvent event){

            if (isChoosed) {
                node.setStyle("-fx-background-color: #ffffff");
                isChoosed = !isChoosed;
            } else {
                node.setStyle("-fx-background-color: #22ffbe");
                isChoosed = !isChoosed;
            }

    }
}
