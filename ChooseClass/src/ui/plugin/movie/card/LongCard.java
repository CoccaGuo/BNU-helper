package ui.plugin.movie.card;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import ui.plugin.movie.util.VideoItem;

public class LongCard extends Card {
    @FXML
    private Label info;

    public LongCard(){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("longcard.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public LongCard(VideoItem videoItem){
        this();
        setCa("简介：");
        setActor("主演："+videoItem.getActor());
        setDirector("导演："+videoItem.getDirector());
        setSummary("    "+videoItem.getSummaryShort());
        setTitle(videoItem.getName());
        setPic(videoItem.getPicUrl());
        setScore(videoItem.getScore());
        setVideo(videoItem);
    }
}
