package ui.plugin.websites;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteWeb extends StackPane {
    public DeleteWeb(){
        this.setMinWidth(149);
        this.setMinHeight(35);
        Label label = new Label("管理标签...");
        label.setFont(Font.font(16));
        this.getChildren().add(label);
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(Color.AQUAMARINE.toString()), new CornerRadii(8), new Insets(2.5))));
        this.setOnMouseClicked(event -> {
            Stage primaryStage =  new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/ui/plugin/websites/dele.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setTitle("add website");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }
}
