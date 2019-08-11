package ui.plugin.websites;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import ui.plugin.websites.download.Download;
import ui.scene.Index;

public class Dele {

    @FXML
    private FlowPane pane;

    @FXML
    void initialize(){
        Index.websites.forEach(item -> pane.getChildren().add(new Download(item)));
    }

    @FXML
    void dele(ActionEvent event) {
        pane.getChildren().forEach(dl ->{
            Download download = (Download)dl;
            if (download.isChoosed){
                pane.getChildren().remove(download);
                Index.websites.remove(download.item);
            }
        });

    }

}
