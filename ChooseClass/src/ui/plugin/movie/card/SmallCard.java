package ui.plugin.movie.card;

import javafx.fxml.FXMLLoader;
import ui.plugin.movie.util.VideoItem;


public class SmallCard extends Card {

    public SmallCard(){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("smallcard.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public SmallCard(VideoItem videoItem){
        this();
        setTitle(videoItem.getName());
        setPic(videoItem.getPicUrl());
        setVideo(videoItem);
    }
}
