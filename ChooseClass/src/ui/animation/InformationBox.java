package ui.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ui.component.TextBox;


public class InformationBox extends TextBox {
    String info;
    int showTimems;
    double width = 250.0;
    double height = 80.0;
    private Paint fill  = Paint.valueOf("#535344");
    Timeline timeline = new Timeline();
    //public TranslateTransition trans = new TranslateTransition(Duration.seconds(1.5));
    Label label;

    public InformationBox(AnchorPane parent, String info, int showTimems){//现在的问题是没办法显示文字
        super(250,80,info, Color.GOLD);
        this.setHeight(height);
        this.setWidth(width);

       // this.setBackground(new Background(new BackgroundFill(fill, new CornerRadii(5), new Insets(2))));
        this.info = info;
        this.showTimems = showTimems;
        this.toFront();
       // this.setClip(label);
        parent.getChildren().add(this);
        parent.setBottomAnchor(this,5.0);
        parent.setRightAnchor(this,-this.getWidth());
        KeyValue startXValue = new KeyValue(this.translateXProperty(),0);
        KeyValue waitXvalue = new KeyValue(this.translateXProperty(),-this.getWidth() - 5.0);
        KeyValue startXValueOp = new KeyValue(this.opacityProperty(),0.2);
        KeyValue waitXvalueOp = new KeyValue(this.opacityProperty(),0.8);
        KeyValue waitEndValue = new KeyValue(this.translateXProperty(),-this.getWidth() - 5.0);
        KeyFrame startFrame = new KeyFrame(Duration.ZERO,"start",startXValue,startXValueOp);
        KeyFrame waitStartFrame = new KeyFrame(Duration.seconds(0.8),"arrrive",waitXvalue,waitXvalueOp);
        KeyFrame  waitEndFrame = new KeyFrame(Duration.millis(800+showTimems/2),"wait",waitEndValue);
        timeline.getKeyFrames().addAll(startFrame,waitStartFrame,waitEndFrame);
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);
       // trans.setNode(this);
        //trans.setFromX(this.getX());
       // trans.setByX(-this.getWidth()- 5.0);
    }



    public void show(){
        timeline.play();
    }


}
