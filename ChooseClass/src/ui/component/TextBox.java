package ui.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class TextBox extends StackPane {
    String text;
    Color fill;
    Label label;

    public TextBox(double width,double height,String text,Color fill){
        this.fill = fill;
        this.text = text;
       this.setMinWidth(width);
       this.setMinHeight(height);
       label = new Label(text);
       this.getChildren().add(label);
       this.setBackground(new Background(new BackgroundFill(Paint.valueOf(fill.toString()), CornerRadii.EMPTY, Insets.EMPTY)));
   }

   public void setFill(Color filll){
       this.setBackground(new Background(new BackgroundFill(Paint.valueOf(filll.toString()), CornerRadii.EMPTY, Insets.EMPTY)));
   }

   public void setText(String text){
       label.setText(text);
   }
}
