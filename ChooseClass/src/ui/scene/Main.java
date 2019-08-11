package ui.scene;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.control.Regist;
import util.control.StageController;

public class Main extends Application  {
   public static StageController stageController;
    @Override
    public void start(Stage primaryStage)  {
        stageController = new StageController();
        stageController.setPrimaryStage(Regist.login,primaryStage);
        stageController.loadStage(Regist.login, Regist.loginFXML);
        stageController.getStage(Regist.login).setTitle("数字京师-登录");
        stageController.getStage(Regist.login).getIcons().add(new Image(Regist.icon));
        Regist.setup();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
