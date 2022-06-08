package util.control;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import ui.animation.InformationBox;

import java.io.IOException;

public class Alerter {
    int alertType;



    AnchorPane pane = null;
    String roastTime = "86400";

    public static final int INFORMATION_BOX = 0;
    public static final int SYSTEM_CALL = 1;
    public static final int ALERT = 2;
    //public static final int MESSAGE = 3;







    public void alert(String info) {
        if (alertType == INFORMATION_BOX) {
            System.out.println(info);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    InformationBox informationBox = new InformationBox(pane, info, 5000);
                    informationBox.show();
                }
            });
        }else if (alertType == SYSTEM_CALL){
            String exe = "python";
            String command = "toast.py";
            String title = "数字京师";
            String[] cmdArr = new String[] {exe, command, title, info, roastTime};
            try {
                Runtime.getRuntime().exec(cmdArr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(alertType == ALERT){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("提示");
            alert.setContentText(info);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    alert.showAndWait();
                }
            });
        }

    }


    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    public void setRoastTime(String roastTime) {
        this.roastTime = roastTime;
    }


    public Alerter(int alertType){
        this.alertType = alertType;
    }

    public Alerter(int alertType,AnchorPane pane){
           this(alertType);
            this.pane = pane;
        }

    }



