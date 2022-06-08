package ui.plugin.movie.scene;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ui.plugin.interfaces.p532Download;
import ui.plugin.movie.card.Card;
import ui.plugin.movie.util.VideoItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class Simple {

    @FXML
    private TextField input;

    @FXML
    private ImageView pic;

    @FXML
    private TextArea ping;

    static String query;


    @FXML
    void check(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER) {
            try {
                search(new ActionEvent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void search(ActionEvent event) throws IOException {
        query = input.getText();
        p532Download.ap.getChildren().removeAll();
        Parent resPage = FXMLLoader.load(getClass().getResource("/ui/plugin/movie/scene/result.fxml"));
        p532Download.ap.autosize();
        p532Download.ap.getChildren().setAll(resPage);


    }

    @FXML
    void initialize(){
        pic.setImage(new Image("util/res/pic/p532.jpg"));
        input.setStyle("-fx-background-color: #ffffff66");
        ping.setOpacity(0.6);
        ping.setText("网络状况测试中...\n");
        NetworkService networkService = new NetworkService();
        networkService.start();

    }
    @FXML
    void toBanner(MouseEvent event) {

    }

    class NetworkService extends Service<String>{

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected void updateValue(String value) {
                    super.updateValue(value);
                    ping.setVisible(true);
                    ping.setText(value);
                }

                @Override
                protected String call() throws Exception {
                    return networkTest();
                }
            };
        }
    }

    String networkTest(){
        String result = "\t\t\t\t******532连通性测试******\n";
        try {
            Process ps = Runtime.getRuntime().exec("ping 532movie.bnu.edu.cn");

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream(),Charset.forName("GBK")));
            String line = "";
            while ((line = br.readLine()) != null) {
                String finalLine = line;
                Platform.runLater(()->ping.appendText(finalLine +"\n"));
                System.out.println(line);
                result+=line+"\n";
            }
            br.close();
            ps.waitFor();
            result+="\n加载的速度取决于您的网络状况。";
            return result;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    return "";
    }




}

