package ui.plugin.movie.card;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.plugin.interfaces.p532Download;
import ui.plugin.movie.util.VideoItem;


import java.io.IOException;

public class Card extends AnchorPane{ //为了灵活性，没有在fxml中指定Controller
    @FXML
    private ImageView pic;
    @FXML
    private Label title;
    @FXML
    private Label director;
    @FXML
    private Label actor;

    public Label getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score.setText(score);
    }

    public VideoItem getVideo() {
        return video;
    }

    public void setVideo(VideoItem video) {
        this.video = video;
    }

    @FXML
    private Label summary;
    @FXML
    private Label score;
    private VideoItem video;
    public static VideoItem choosedItem;
    @FXML
    Label ca;
    @FXML
    public void choosed() throws IOException {
        choosedItem = video;
//        Stage primaryStage =  new Stage();
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("/ui/Scenes/item.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        primaryStage.setTitle("CardTest");
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        scene.getStylesheets().add(CardTest.class.getResource("/ui/css/default.css").toExternalForm());
//        primaryStage.show();

        p532Download.ap.getChildren().removeAll();
        Parent resPage = FXMLLoader.load(getClass().getResource("/ui/plugin/movie/Scenes/item.fxml"));
        resPage.getStylesheets().add(CardTest.class.getResource("/ui/plugin/movie/css/default.css").toExternalForm());
        p532Download.ap.getChildren().setAll(resPage);


    }

    public Label getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca.setText(ca);
    }

    public Card(){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("card.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Card(VideoItem videoItem){
        this();
       init(videoItem);
    }

    public void init(VideoItem videoItem){
        ca.setText("简介：");
        this.video = videoItem;
        this.title.setText(videoItem.getName());
        this.director.setText("导演："+videoItem.getDirector());
        this.actor.setText("主演："+videoItem.getActor());
        this.summary.setText("    "+videoItem.getSummaryShort());
        this.pic.setImage(new Image(videoItem.getPicUrl()));
        this.score.setText(videoItem.getScore()+"");
    }

    public void setPic(String url) {
        this.pic.setImage(new Image(url));
    }

    public ImageView getPic() {
        return pic;
    }

    public Label getTitle() {
        return title;
    }

    public Label getDirector() {
        return director;
    }

    public Label getActor() {
        return actor;
    }

    public Label getSummary() {
        return summary;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDirector(String director) {
        this.director.setText(director);
    }

    public void setActor(String actor) {
        this.actor.setText(actor);
    }

    public void setSummary(String summary) {
        this.summary.setText(summary);
    }
}
