package test;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.control.Alerter;
import util.control.Filter;
import util.control.Listenable;
import util.control.Listener;


public class ListenTest extends Application implements Listenable {
    TextField textField;
    AnchorPane parent;

    @Override
    public void start(Stage primaryStage) throws Exception {

        parent = new AnchorPane();
        parent.setPrefWidth(800);
        parent.setPrefHeight(800);
        Scene scene = new Scene(parent);
        textField = new TextField();
        Button button = new Button("监听");
        Button button1 = new Button("停止");
        AnchorPane.setLeftAnchor(button, 200.0);
        AnchorPane.setLeftAnchor(button1, 250.0);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("开始监听");
                listener.listen();
            }
        });
        listener.setFreshTime(1);
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                listener.stop();
            }
        });
        parent.getChildren().addAll(textField, button, button1);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ui_test");
        primaryStage.show();
    }

    @Override
    public Listener listener() {
        return listener;
    }

    Listener<String> listener = new Listener<String>() {
        @Override
        public String refresh() {
            return ListenTest.this.textField.getText();
        }

        @Override
        public Filter<String> filter() {
            return filter;
        }

        @Override
        public Alerter alerter() {
            return new Alerter(Alerter.INFORMATION_BOX,parent);
        }

        Filter<String> filter = new Filter<String>() {
            @Override
            public String compareEach(String t1, String t2) {
                if (t1.equals(t2)) return null;
                return t2;
            }

        };

    };
}
