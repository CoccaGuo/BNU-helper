package ui.plugin.interfaces;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import util.control.Errorer;
import util.control.PlugInable;
import util.control.Regist;

import java.io.IOException;

public class pChooseClass implements PlugInable {
    public static final String img = "/util/res/pic/choose.png";
    public static final String title = "选课助手";
    public static AnchorPane ap = null;
    public static AnchorPane index = null;
    public static Label label = null;
    Label tag;

    @Override
    public AnchorPane getAnchorPane() {
        try {


            ap = new AnchorPane();
            ImageView bg = new ImageView(new Image("/util/res/pic/bg_final_.png"));
            bg.setFitWidth(955);
            bg.setFitHeight(624);
            ap.getChildren().add(bg);
            ap.setBottomAnchor(bg,0.0);
            ap.setRightAnchor(bg,0.0);
            bg.autosize();
            ap.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"),null,null)));

            tag = new Label();
            CircleButton zx = new CircleButton("正 选","#fc9c8c");
            CircleButton gx = new CircleButton("公 选","#8EE5EE");

            zx.setOnMouseClicked(event -> {
                pChooseClass.ap.getChildren().removeAll();
                Parent resPage = null;
                try {
                    resPage = FXMLLoader.load(getClass().getResource("/ui/plugin/choose/choose.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pChooseClass.ap.autosize();
                pChooseClass.ap.getChildren().setAll(resPage);
            });

            gx.setOnMouseClicked(event -> {
                pChooseClass.ap.getChildren().removeAll();
                Parent resPage = null;
                try {
                    resPage = FXMLLoader.load(getClass().getResource("/ui/plugin/choose/chooseGX.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pChooseClass.ap.autosize();
                pChooseClass.ap.getChildren().setAll(resPage);
            });


            ap.setTopAnchor(zx,200.0);
            ap.setLeftAnchor(zx,350.0);
            ap.setTopAnchor(gx,200.0);
            ap.setRightAnchor(gx,350.0);


            FetchChooseTimeService service = new FetchChooseTimeService();
            service.start();


            ap.getChildren().addAll(gx,zx,tag);
            //ap = FXMLLoader.load(getClass().getResource("/ui/plugin/.../simple.fxml"));
            //ap.getParent().getStylesheets().add(this.getClass().getResource("/ui/plugin/movie/css/default.css").toExternalForm());
        } catch (Exception e) {
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


    private class FetchChooseTimeService extends Service<String> {


        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected void updateValue(String value) {
                    super.updateValue(value);
                    if (value.equals("0"))
                        tag.setText("当前不是选课期间");
                    else
                        tag.setText("现在是选课期间");
                }

                @Override
                protected String call() throws Exception {
                   return Regist.assist.fetchSelectInfo().getIsValidTimeRange();
                }
            };
        }
    }


    private class CircleButton extends AnchorPane{
        Circle circle;
        Label label;
        CircleButton(String text,String color){
            circle = new Circle();
            label = new Label();
            circle.setRadius(70);
            circle.autosize();
            circle.setCenterX(70);
            circle.setCenterY(70);
            circle.setFill(Paint.valueOf(color));
            label.setText(text);
            label.setFont(Font.font(26));
            this.setLeftAnchor(label,43.0);
            this.setTopAnchor(label,47.0);
            this.getChildren().addAll(circle,label);
            this.autosize();
        }
    }
}
