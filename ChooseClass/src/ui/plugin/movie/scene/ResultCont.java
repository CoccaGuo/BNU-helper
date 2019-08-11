package ui.plugin.movie.scene;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ui.animation.InformationBox;
import ui.plugin.interfaces.p532Download;
import ui.plugin.movie.card.Card;
import ui.plugin.movie.card.LongCard;
import ui.plugin.movie.util.VideoItem;
import util.control.Alerter;
import util.control.Errorer;

import java.io.IOException;
import java.util.ArrayList;


public class ResultCont  {

    @FXML
    private FlowPane resultArea;

    InformationBox in;

    @FXML
    void initialize(){
        p532Download.label.setText("查询中...");
        in = new InformationBox(p532Download.index,"查询中，请稍等",13000);
        in.setFill(Color.GOLD);
        in.show();
        SearchService searchService = new SearchService();
        searchService.start();
    }


    class SearchService extends Service<ArrayList<LongCard>> {

        @Override
        protected Task<ArrayList<LongCard>> createTask() {
            return new Task<ArrayList<LongCard>>() {
                @Override
                protected void updateValue(ArrayList<LongCard> value) {
                    super.updateValue(value);
                    resultArea.getChildren().addAll(value);
                    in.setVisible(false);
                    p532Download.label.setText("就绪");

                }

                @Override
                protected ArrayList<LongCard> call() {
                   Thread outoftime = new Thread(new Runnable() {
                        @Override
                        public void run() {
                           long time = System.currentTimeMillis();
                           long newTime = System.currentTimeMillis();
                           while(newTime - time < 1000*15){
                               newTime = System.currentTimeMillis();
                               try {
                                   Thread.sleep(200);
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }
                           }
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                   InformationBox inn = new InformationBox(p532Download.index,"查询超时。",3000);
                                    inn.setFill(Color.GOLD);
                                    p532Download.label.setText("查询超时");
                                    inn.setOnMouseClicked(e->{
                                        Alerter a = new Alerter(Alerter.ALERT);
                                        a.alert("检查是否连接校园网，或缩小范围后重试");
                                    });
                                    inn.show();
                                }
                            });
                        }
                    });
                   outoftime.start();
                    ArrayList<LongCard> resList = new ArrayList<>();

                    Document document = null;
                    try {
                        document = Jsoup.connect("http://532movie.bnu.edu.cn/index.php?s=video/search/wd/" + Simple.query).get();
                    } catch (IOException e) {
                        Errorer.error(p532Download.index,e);
                    }
                    Elements title = document.select(".well");
                    title = title.select("h4");
                    title = title.select("[target=_blank]");
                    ArrayList<String> urlList = new ArrayList<>();
                    for (Element e :
                            title) {
                        System.out.println(e.text());
                        Element hrefEle = e.select("[href]").first();
                        String url = hrefEle.attr("href");
                        urlList.add(url);
                    }
                    for (String url:
                            urlList) {
                        resList.add(new LongCard(new VideoItem(VideoItem.getINDEX()+url)));
                    }
                    if (resList.size()==0){
                        resList.add(new LongCard());
                    }

                    outoftime.stop();

                    return resList;
                }
            };
        }
    }

}
