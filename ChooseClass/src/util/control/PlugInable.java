package util.control;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public interface PlugInable {
    AnchorPane getAnchorPane();
    String getImg();
    String getTitle();
    void setIndexAnchorPane(AnchorPane anchorPane);
    void setStatusTag(Label label);
}
