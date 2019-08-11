package ui.component;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import util.control.Regist;

public class ItemButton extends VBox {
    ImageView imageView;
    Label label ;

    public ItemButton(String img,String title){
        super();
        this.setAlignment(Pos.CENTER);
        Image image = new Image(img);
        imageView = new ImageView(image);
        imageView.setFitWidth(Regist.itemButtonSize);
        imageView.setFitHeight(Regist.itemButtonSize);
        this.getChildren().add(imageView);
        label = new Label(title);
        label.setLabelFor(imageView);
        label.setFont(Font.font(Regist.itemButtonFont));
        label.setAlignment(Pos.CENTER);
        this.getChildren().add(label);

    }

}
