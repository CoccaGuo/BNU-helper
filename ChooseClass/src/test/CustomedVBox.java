package test;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CustomedVBox extends VBox {
    ImageView imageView;
    Label label ;

  public CustomedVBox(String img,String title){
      super();
      this.setAlignment(Pos.CENTER);
      Image image = new Image(img);
      imageView = new ImageView(image);
      imageView.setFitWidth(40);
      imageView.setFitHeight(40);
      this.getChildren().add(imageView);
      label = new Label(title);
      label.setLabelFor(imageView);
      label.setFont(Font.font(14));
      label.setAlignment(Pos.CENTER);
      this.getChildren().add(label);

  }


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}