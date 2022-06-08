package ui.component;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ChooseClassItem extends AnchorPane {
    public ChooseClassItem(String name,String time,String classification,String ninzu){
        this.setMinSize(940,30);
        this.setMaxSize(940,30);
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(Color.GOLD.toString()), new CornerRadii(5), new Insets(2))));
        Label label = new Label(name);
        label.setFont(Font.font(14));
        label.setMaxWidth(300);
        this.setLeftAnchor(label,20.0);
        this.setTopAnchor(label,5.0);

        Label label2 = new Label(classification);
        label2.setFont(Font.font(14));
        label2.setMaxWidth(200);
        this.setLeftAnchor(label2,315.0);
        this.setTopAnchor(label2,5.0);

        Label label3 = new Label(time);
        label3.setFont(Font.font(14));
        label3.setMaxWidth(250);
        this.setLeftAnchor(label3,525.0);
        this.setTopAnchor(label3,5.0);

        Label label4 = new Label(ninzu);
        label4.setFont(Font.font(14));
        label4.setMaxWidth(100);
        this.setLeftAnchor(label4,780.0);
        this.setTopAnchor(label4,5.0);

        Button button = new Button("选择");
        this.setLeftAnchor(button,880.0);
        this.setTopAnchor(button,3.0);

        this.getChildren().addAll(label,label2,label3,label4,button);
    }
    public void setColor(Color color){
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(color.toString()), new CornerRadii(5),new Insets(2))));
    }
}
