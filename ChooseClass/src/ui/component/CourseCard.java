package ui.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.Serializable;

public class CourseCard extends StackPane implements Serializable {
    String className;
    String timeLocation;
    Color fill;
    Label label;
    Label label2;

    public CourseCard(double height,String className,String timeLocation,Color fill){
        this.fill = fill;
        this.className = className;
        this.timeLocation =timeLocation;
        this.setMinWidth(100);
        this.setMinHeight(height);
        label = new Label(className);
        label2 = new Label(timeLocation);
        this.setAlignment(label, Pos.TOP_CENTER);
        this.setAlignment(label2,Pos.BOTTOM_CENTER);
        this.getChildren().addAll(label,label2);

        label.setWrapText(true);
        label.setMaxSize(90,70);
        label.setMinWidth(90);
        label.setFont(Font.font(14));



        label2.setWrapText(true);
        label2.setMaxSize(90,30);
        label2.setMinWidth(90);
        label2.setFont(Font.font(12));


        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(fill.toString()), new CornerRadii(8), new Insets(0,1,0,0))));
    }

}

