package ui.plugin.interfaces;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.control.PlugInable;

import java.io.IOException;

public class pTestArr implements PlugInable {
    public static final String img = "/util/res/pic/test.png";
    public static final String title = "考试安排";
    public static AnchorPane ap = null;
    public static AnchorPane index = null;
    Label label = null;

    @Override
    public AnchorPane getAnchorPane() {
        try {
            ap = FXMLLoader.load(getClass().getResource("/ui/plugin/exam/exam.fxml"));
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
        index = anchorPane;
    }

    @Override
    public void setStatusTag(Label label) {
        this.label = label;
    }
}
