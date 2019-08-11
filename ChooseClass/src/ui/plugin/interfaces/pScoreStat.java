package ui.plugin.interfaces;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import util.control.PlugInable;

import java.io.IOException;

public class pScoreStat implements PlugInable {
    public static final String img = "/util/res/pic/score.png";
    public static final String title = "学业成绩";
    AnchorPane ap = null;
    public static AnchorPane index = null;
    public static Label label = null;

    @Override
    public AnchorPane getAnchorPane() {
        try {
            ap = FXMLLoader.load(getClass().getResource("/ui/plugin/score/score.fxml"));
            //ap.getParent().getStylesheets().add(this.getClass().getResource("/ui/plugin/movie/css/default.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
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
        this.index = anchorPane;
    }

    @Override
    public void setStatusTag(Label label) {
        this.label = label;
    }
}
