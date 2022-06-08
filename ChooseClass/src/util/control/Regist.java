package util.control;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import org.apache.commons.io.FileUtils;
import ui.scene.Main;
import util.assist.SchoolworkAssist;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;

public class Regist implements  Serializable{
    public static final String login = "login";
    public static final String loginFXML = "/ui/scene/login.fxml";
    public static final String index = "index";
    public static final String indexFXML = "/ui/scene/index.fxml";



    public static  String account = "login.dat"; //登录密码账号保存位置
    public static  String loginPic = "/util/res/pic/bggg.jpg";//登录界面图片
    public static  String icon = "/util/res/pic/logo.png";//应用程序图标
    public static  String pluginDirectory = "src/ui/plugin/interfaces";//插件包所在目录


    public static double itemButtonSize = 38;
    public static double itemButtonFont = 10;



    public static SchoolworkAssist assist;
    private static Properties properties;
    public static Thread setup;
    static File logFile = new File("log");












    public static void log(String message){
        try {
            FileUtils.writeStringToFile(logFile,"["+new Date().toString()+"]"+message+"\r\n",Charset.defaultCharset(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//
//    public static void deleteLog(){
//        try{
//            FileUtils.deleteQuietly(logFile);
//            logFile.createNewFile();
//        }catch (Exception e){
//            log("log文件删除失败。");
//            e.printStackTrace();
//        }
//    }




    public static void setup(){
        File file = new File("login.dat");
        if (!file.exists()){
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                        Desktop.getDesktop().open(new File("config.bat"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("欢迎");
                        alert.setHeaderText("第一次使用");
                        alert.setContentText("本软件需要Java 8 以及 Python 3 运行环境。\n请您确认cmd中出现的版本信息。\n请务必在确认和cmd完成进程后点击确认。");
                        alert.show();
                        alert.setOnHidden(new EventHandler<DialogEvent>() {
                            @Override
                            public void handle(DialogEvent event) {
                                String cmd = " taskkill /f /im cmd.exe";
                                try {
                                    Runtime.getRuntime().exec(cmd);
                                    Main.stageController.setStage(Regist.login);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                        System.out.println("enter setup");


                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            Main.stageController.setStage(Regist.login);
        }

    }






}
