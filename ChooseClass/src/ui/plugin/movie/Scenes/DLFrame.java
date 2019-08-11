package ui.plugin.movie.Scenes;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.animation.InformationBox;
import ui.plugin.interfaces.p532Download;
import ui.plugin.movie.download.Download;
import ui.plugin.movie.util.VideoItem;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DLFrame implements Initializable {
    String ichi = null;
    VideoItem videoItem;
    private Stage stage;
    List<Download> dlList = new ArrayList<>();

    @FXML
    private FlowPane dwPanel;

    @FXML
    private Label title;

    @FXML
    private Button dw;

    @FXML
    private Button path;
    @FXML
    private Label strPath;

    String fileName;

    public static String info = "";
  // public static DownloadService service;
    public RefreshStatus refreshStatus;

    @FXML
    void dw(ActionEvent event) {
         fileName = videoItem.getName().split("/")[0];
        DownloadService service = new DownloadService();
        service.start();
        refreshStatus = new RefreshStatus();
        refreshStatus.setDelay(Duration.ZERO);
        refreshStatus.setPeriod(Duration.seconds(1.0));
        refreshStatus.start();
        InformationBox informationBox = new InformationBox(p532Download.index,fileName+"开始下载……",2000);
        informationBox.show();
        //Platform.runLater(()->new TransparentStage().info(fileName+"开始下载……"));
        stage = (Stage)strPath.getScene().getWindow();
        stage.close();
    }

    @FXML
    void path(ActionEvent event) {
        DirectoryChooser directoryChooser=new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);
        this.ichi = file.getPath();//选择的文件夹路径
        strPath.setText("下载位置:"+ichi+"\\532movie\\output");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.videoItem = ItemCont.getItem();
        title.setText("您正在下载："+videoItem.getName());
        for (int i = 1; i < videoItem.indexUrl.length+1 ; i++) {
            Download download =new Download(videoItem,i);
            dlList.add(download);
            dwPanel.getChildren().add(download);
            strPath.setText("下载位置:/532movie/output");
        }
//       stage = (Stage)dwPanel.getScene().getWindow();
    }


//    @FXML
//    void initialize(){
//
//    }

   private class DownloadService extends Service<String>{

       @Override
       protected Task<String> createTask() {
           return new Task<String>() {
               @Override
               protected String call() throws Exception {
                   for (int i = 0; i < dlList.size(); i++) {
                       if(dlList.get(i).isChoosed){
                           DownFileUtil.download("http://172.16.215.40:5320/" + videoItem.indexUrl[i],fileName+"_"+(i+1),ichi);
                       }
                   }
                   return null;
               }
           };
       }
   }

    class RefreshStatus extends ScheduledService<String>{

       @Override
       protected Task<String> createTask() {
           return new Task<String>() {
               @Override
               protected void updateValue(String value) {
                   super.updateValue(value);
                   if (value.startsWith("qqq")){
                       p532Download.label.setText("下载完成");
                       DLFrame.this.refreshStatus.cancel();
                       return;
                   }
                   p532Download.label.setText(value);
               }

               @Override
               protected String call() throws Exception {
                   return DLFrame.info;
               }
           };
       }
   }

}
