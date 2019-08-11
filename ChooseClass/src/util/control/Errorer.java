package util.control;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ui.animation.InformationBox;

public class Errorer {

    public static void error(AnchorPane index, Exception e) {
        InformationBox informationBox = new InformationBox(index, "网络异常", 50000);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                informationBox.show();
            }
        });


        StackTraceElement[] traceElements = e.getStackTrace();
        StringBuilder reason = new StringBuilder();
        for (int i = 0; i < traceElements.length; i++) {
            reason.append(traceElements[i].toString() + "\n");
        }
        informationBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(e.getLocalizedMessage());
                alert.setContentText(reason.toString());
                alert.show();
                informationBox.setVisible(false);
            }
        });
    }
}
