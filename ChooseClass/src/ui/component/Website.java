package ui.component;

import com.alibaba.fastjson.annotation.JSONField;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import ui.plugin.websites.Item;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Website extends StackPane {
    @JSONField(name = "URL")
    String url = null;

    @JSONField(name = "TITLE")
    String title = null;

    @JSONField(name = "URL")
    public String getUrl() {
        return url;
    }

    @JSONField(name = "URL")
    public void setUrl(String url) {
        this.url = url;
    }

    @JSONField(name = "TITLE")
    public String getTitle() {
        return title;
    }

    @JSONField(name = "TITLE")
    public void setTitle(String title) {
        this.title = title;
    }

    public Website(String title,String url){
        this.title = title;
        this.url = url;
    }
    public Item getItem(){
         Item item = new Item();
         item.setUrl(url);
         item.setTitle(title);
        return item;
    }

    public Website(Item item, Color fill) {
        this.url = item.getUrl();
        this.title = item.getTitle();
        String text = item.getTitle();
        this.setMinWidth(149);
        this.setMinHeight(35);
        Label label = new Label(text);
        label.setFont(Font.font(16));
        this.getChildren().add(label);
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(fill.toString()), new CornerRadii(8), new Insets(2.5))));
        try {
            URI  url = new URI(item.getUrl());

            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        Desktop.getDesktop().browse(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
