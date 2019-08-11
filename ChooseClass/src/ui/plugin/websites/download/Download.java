package ui.plugin.websites.download;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ui.plugin.movie.util.VideoItem;
import ui.plugin.websites.Item;


public class Download extends AnchorPane{

    @FXML
    private Label num;

    @FXML
    private AnchorPane node;

    public boolean isChoosed = false;

    public Item item;

    public Download(Item item){
        this.item = item;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("download.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        node.setBackground(new Background(new BackgroundFill(Paint.valueOf(Color.ALICEBLUE.toString()),new CornerRadii(5),new Insets(2))));
        num.setText(item.getTitle()+" : "+item.getUrl());
    }

    @FXML
    void choose(MouseEvent event){

            if (isChoosed) {
                node.setBackground(new Background(new BackgroundFill(Paint.valueOf(Color.ALICEBLUE.toString()),new CornerRadii(5),new Insets(2))));
                isChoosed = !isChoosed;
            } else {
                node.setBackground(new Background(new BackgroundFill(Paint.valueOf(Color.GOLD.toString()),new CornerRadii(5),new Insets(2))));
                isChoosed = !isChoosed;
            }

    }
}
