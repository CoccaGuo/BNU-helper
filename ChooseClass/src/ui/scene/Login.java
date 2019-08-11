package ui.scene;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import util.assist.SchoolworkAssist;
import util.control.ControlledStage;
import util.control.Regist;
import util.control.StageController;

import java.io.File;
import java.nio.charset.Charset;

public class Login implements ControlledStage {
    StageController stageController = null;

    @FXML
    private TextField ac;

    @FXML
    private ImageView pic;

    @FXML
    private PasswordField pw;

    @FXML
    private CheckBox rempw;

    @FXML
    private CheckBox autologin;

    @FXML
    private Button login;

    @FXML
    private Label status;

    @FXML
    public void initialize() {
        ReadLoginInfo readLoginInfo = new ReadLoginInfo(ac, pw, status, rempw, autologin, login);
        readLoginInfo.start();
        pic.setImage(new Image(Regist.loginPic));
        login.setStyle("-fx-background-color: #E0EEE0");
        //rempw.setStyle("-fx-background-color: #E0EEE0");
        pw.setStyle("-fx-background-color: #E0EEE0");
        //autologin.setStyle("-fx-background-color: #E0EEE0");
        ac.setStyle("-fx-background-color: #E0EEE0");

    }

    @FXML
    void login(ActionEvent event) {
        login.setDisable(true);
        login.setText("登录中");
        login.setStyle("-fx-background-color:#B4EEB4");
        MyTask task = new MyTask(ac.getText(), pw.getText());
        new Thread(task).start();
        task.valueProperty().addListener((observable, oldValue, newValue) -> {
            status.setText(newValue);
            login.setDisable(false);
            login.setText("登录");
            login.setStyle("-fx-background-color: #EE6363");
            Timeline timeline = new Timeline();
            KeyValue op1 = new KeyValue(login.backgroundProperty(), new Background(new BackgroundFill(Paint.valueOf("#EE6363"), new CornerRadii(2), Insets.EMPTY)));
            KeyValue op2 = new KeyValue(login.backgroundProperty(), new Background(new BackgroundFill(Paint.valueOf("#E0EEE0"), new CornerRadii(2), Insets.EMPTY)));
            KeyFrame f1 = new KeyFrame(Duration.ZERO,op1);
            KeyFrame f2 = new KeyFrame(Duration.seconds(2),op2);
            timeline.getKeyFrames().addAll(f1,f2);
            timeline.play();
            if (newValue.equals("登录成功")) {    //login success
                login.setStyle("-fx-background-color: #E0EEE0");
                if (rempw.isSelected()) {
                    SaveLoginInfo saveLoginInfo = new SaveLoginInfo(ac.getText(), pw.getText(), autologin.isSelected(), rempw.isSelected());
                    saveLoginInfo.start();
                }
                stageController.loadStage(Regist.index, Regist.indexFXML);
                stageController.getStage(Regist.index).setTitle("数字京师");
                stageController.getStage(Regist.index).getIcons().add(new Image(Regist.icon));
                stageController.setStage(Regist.index, Regist.login);
            }
        });
    }

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }


    class MyTask extends Task<String> {
        String ac;
        String pw;

        public MyTask(String ac, String pw) {
            this.ac = ac;
            this.pw = pw;
        }

        @Override
        protected String call() {
            try {
                Regist.assist = new SchoolworkAssist(ac, pw);
            } catch (Exception e) {
                return e.getMessage();

            }

            return "登录成功";
        }

    }

    class SaveLoginInfo extends Service<String> {
        String ac;
        String pw;
        boolean autoLogin;
        boolean remPw;

        public SaveLoginInfo(String ac, String pw, boolean autoLogin, boolean remPw) {
            this.ac = ac;
            this.pw = pw;
            this.autoLogin = autoLogin;
            this.remPw = remPw;
        }

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    File loginFile = new File(Regist.account);
                    if (!loginFile.exists()) loginFile.createNewFile();
                    String acpw = ac + "_" + pw + "_" + autoLogin;
                    FileUtils.writeStringToFile(loginFile, acpw, Charset.defaultCharset(), false);
                    return null;
                }
            };
        }
    }

    class ReadLoginInfo extends Service<String> {

        TextField ac = null;
        PasswordField pw = null;
        Label stat = null;
        CheckBox remPw;
        CheckBox autoLogin;
        Button login;

        public ReadLoginInfo(TextField ac, PasswordField pw, Label stat, CheckBox remPw, CheckBox autoLogin, Button login) {
            this.ac = ac;
            this.pw = pw;
            this.stat = stat;
            this.autoLogin = autoLogin;
            this.remPw = remPw;
            this.login = login;
            Regist.log("程序启动。");
        }

        @Override
        protected Task<String> createTask() {

            Task<String> task = new Task<String>() {
                @Override
                protected void updateValue(String value) {
                    super.updateValue(value);
                    try {
                        ReadLoginInfo.this.ac.setText(value.split("_")[0]);
                        ReadLoginInfo.this.pw.setText(value.split("_")[1]);
                        if (value.split("_")[2].equals("true")) {
                            autoLogin.setSelected(true);
                            remPw.setSelected(true);
                            login.fire();
                        }
                    } catch (Exception e) {
                        ReadLoginInfo.this.stat.setText(e.getMessage());
                    }

                }

                @Override
                protected String call() throws Exception {
                    File file = new File(Regist.account);
                    if (!file.exists()) {
                        return null;
                    }
                    return FileUtils.readFileToString(file, Charset.defaultCharset());
                }
            };

            return task;
        }
    }
}