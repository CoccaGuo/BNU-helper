package ui.plugin.interfaces;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.control.Errorer;
import util.control.PlugInable;

import java.io.IOException;

public class p532Download implements PlugInable {
    public static final String img = "/util/res/pic/download.png";
    public static final String title = "532下载";
    public static AnchorPane ap = null;
    public static AnchorPane index = null;
    public static Label label = null;
    public p532Download(){
    }

    @Override
    public AnchorPane getAnchorPane() {
        try {
            ap = FXMLLoader.load(getClass().getResource("/ui/plugin/movie/scene/simple.fxml"));
            //ap.getParent().getStylesheets().add(this.getClass().getResource("/ui/plugin/movie/css/default.css").toExternalForm());
        } catch (IOException e) {
            Errorer.error(index,e);
        }

        return ap;
    }

    @Override
    public String getImg() {
        return img;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setIndexAnchorPane(AnchorPane anchorPane) {
        index = anchorPane;
    }

    @Override
    public void setStatusTag(Label label) {
        this.label = label;
    }

}
