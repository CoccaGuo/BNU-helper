package ui.plugin.movie.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ui.plugin.interfaces.p532Download;
import ui.plugin.movie.card.Card;
import ui.plugin.movie.util.VideoItem;

import java.io.IOException;


public class Simple {

    @FXML
    private TextField input;

    @FXML
    private ImageView pic;



    static String query;


    @FXML
    void check(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER) {
            try {
                search(new ActionEvent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void search(ActionEvent event) throws IOException {
        query = input.getText();
        p532Download.ap.getChildren().removeAll();
        Parent resPage = FXMLLoader.load(getClass().getResource("/ui/plugin/movie/scene/result.fxml"));
        p532Download.ap.autosize();
        p532Download.ap.getChildren().setAll(resPage);


    }

    @FXML
    void initialize(){
        pic.setImage(new Image("util/res/pic/p532.jpg"));
        input.setStyle("-fx-background-color: #ffffff66");

    }
    @FXML
    void toBanner(MouseEvent event) {

    }




}

