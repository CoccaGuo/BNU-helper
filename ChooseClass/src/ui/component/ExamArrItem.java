package ui.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ExamArrItem extends AnchorPane {
    public ExamArrItem(String name,String score,String usualScore,String finalScore,String classification,String credit){
        this.setMinSize(430,100);
        this.setMaxSize(430,100);
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(Color.CHARTREUSE.toString()), new CornerRadii(8), new Insets(10))));
        name = name.split("\\]")[0];
        Label label = new Label(name);
        label.setFont(Font.font(18));
        label.setMaxWidth(345);
        this.setLeftAnchor(label,20.0);
        this.setTopAnchor(label,15.0);

        Label label1 = new Label(score);
        label1.setFont(Font.font(18));
        this.setRightAnchor(label1,20.0);
        this.setTopAnchor(label1,15.0);

        Label label2 = new Label("考试地点:"+usualScore+ "  "+"座位号:"+finalScore + " 考核方式:" + credit);
        label2.setFont(Font.font(14));
        label2.setWrapText(true);
        label2.setMaxWidth(420);
        this.setLeftAnchor(label2,20.0);
        this.setTopAnchor(label2,63.0);

        Label label3 = new Label(classification);
        label3.setFont(Font.font(14));
        this.setLeftAnchor(label3,20.0);
        this.setTopAnchor(label3,43.0);

        this.getChildren().addAll(label,label1,label2,label3);
    }
    public void setColor(Color color){
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(color.toString()), new CornerRadii(8),new Insets(10))));
    }
}