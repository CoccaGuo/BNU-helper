package ui.scene;

import com.alibaba.fastjson.JSON;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import ui.component.ItemButton;
import ui.component.Website;
import ui.plugin.websites.AddNewWebsite;
import ui.plugin.websites.DeleteWeb;
import ui.plugin.websites.Item;
import util.control.ControlledStage;
import util.control.PlugInable;
import util.control.Regist;
import util.control.StageController;
import util.res.color.ColorIterator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

import ui.plugin.interfaces.*;

import static java.util.Arrays.asList;

public class Index implements ControlledStage {
    StageController stageController = null;
    public static ObservableList<Item> websites ;

    @FXML
    private AnchorPane index;

    @FXML
    private Label name;

    @FXML
    private Label time;

    @FXML
    private Label stat;

    @FXML
    private VBox website;


    @FXML
    private HBox tools;

    @FXML
    private TabPane main;

    public static Iterator<Color> iterator;

    @FXML
    void info(MouseEvent event) {//返回登录界面
        stageController.setStage(Regist.login,Regist.index);

    }


    @FXML
   void initialize(){
        ColorIterator colors = new ColorIterator();
        iterator = colors.iterator();
        TimeService timeService = new TimeService();
        timeService.setDelay(Duration.ZERO);
        timeService.setPeriod(Duration.seconds(1));
        timeService.start();
        SetupService setupService = new SetupService();
        setupService.start();
        LoadPluginService loadPluginService = new LoadPluginService();
        loadPluginService.start();
        WebsiteService websiteService = new WebsiteService();
        websiteService.start();
    }

    private class WebsiteService extends Service<ObservableList<Item>>{

        @Override
        protected Task<ObservableList<Item>> createTask() {
            return new Task<ObservableList<Item>>() {
                @Override
                protected void updateValue(ObservableList<Item> value) {
                    super.updateValue(value);
                    value.forEach(item ->
                        Index.this.website.getChildren().add(new Website(item,iterator.next())));
                    AddNewWebsite addNewWebsite = new AddNewWebsite();
                    DeleteWeb deleteWeb =new DeleteWeb();
                    Index.this.website.getChildren().addAll(addNewWebsite,deleteWeb);
                    websites.addListener(new ListChangeListener<Item>(){
                        @Override
                        public void onChanged(Change<? extends Item> c) {
                            while (c.next()) {
                                if (c.wasAdded())
                                    c.getAddedSubList().forEach(newitem ->
                                            Index.this.website.getChildren().add(website.getChildren().size()-2,new Website(newitem,iterator.next())));
                                if (c.wasRemoved())
                                    c.getRemoved().forEach(item -> {
                                        for (int i = 0;
                                             i < Index.this.website.getChildren().size(); i++) {
                                            try {
                                                Website item1 = (Website) Index.this.website.getChildren().get(i);
                                                if (item1.getUrl().equals(item.getUrl()))
                                                    Index.this.website.getChildren().remove(i);
                                            }catch(ClassCastException e){

                                            }
                                        }
                                    });

                                String json = JSON.toJSONString(Index.websites);
                                try {
                                    FileUtils.writeStringToFile(new File("website.json"), json, Charset.defaultCharset(), false);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }

                @Override
                protected ObservableList<Item> call() {
                    System.out.println("called");
                    String json = null;
                    try {
                        File file = new File("website.json");
                        if (!file.exists())file.createNewFile();
                        json = FileUtils.readFileToString(file, Charset.defaultCharset());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    List<Item> list = JSON.parseArray(json,Item.class);
                    websites = FXCollections.observableList(list);
                    return websites;
                }
            };
        }
    }

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
     }

    class LoadPluginService extends Service<ArrayList<PlugInable>>{

        @Override
        protected Task<ArrayList<PlugInable>> createTask() {
            return new Task<ArrayList<PlugInable>>() {
                @Override
                protected void updateValue(ArrayList<PlugInable> value) {
                    super.updateValue(value);
                   main.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
                   main.setTabMinWidth(70);
                   main.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                       @Override
                       public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                           main.getSelectionModel().select(newValue.intValue());
                       }
                   });
                    //main.setSide(Side.LEFT);
                    for (PlugInable plugin:
                         value) {
                        ItemButton itemButton = new ItemButton(plugin.getImg(),plugin.getTitle());
                        tools.getChildren().add(itemButton);
                        if (plugin.getTitle().equals("课程表")){
                            Tab tab = new Tab(plugin.getTitle());
                            tab.setContent(plugin.getAnchorPane());
                            tab.setClosable(false);
                            main.getTabs().add(tab);
                        }
                        itemButton.setOnMouseClicked((e)->{

                                Tab tab = new Tab(plugin.getTitle());

                                if ( main.getTabs().parallelStream().filter(allTabs -> allTabs.getText().equals(plugin.getTitle())).count()==0){
                                    main.getTabs().add(tab);
                                    tab.setContent(plugin.getAnchorPane());
                                    main.getSelectionModel().select(tab);
                                }else {
                                    List<Tab> tabList = main.getTabs();
                                    int i = 0;
                                    for (Tab t:
                                         tabList) {
                                        if (t.getText().equals(plugin.getTitle()))main.getSelectionModel().select(i);
                                        i++;
                                    }
                                }
                        });
                    }

                }

                @Override
                protected ArrayList<PlugInable> call() throws Exception {
                    ArrayList<PlugInable> plugInList = new ArrayList<>();

                        PlugInable p0 = new p0Calender();
                        PlugInable p1 = new p532Download();
                        PlugInable p2 = new pChooseClass();
                        PlugInable p3 = new pScoreStat();
                        PlugInable p4 = new pTestArr();

                        plugInList.addAll(asList(p0,p1,p2,p3,p4));
                    for (PlugInable plugInObject : plugInList) {
                        plugInObject.setIndexAnchorPane(index);
                        plugInObject.setStatusTag(stat);
                    }


                    return plugInList;
                }
            };
        }
    }

    class SetupService extends Service<String>{

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected void updateValue(String value) {
                    super.updateValue(value);
                    Index.this.name.setText(value);
                    Index.this.stat.setText("就绪");
                }

                @Override
                protected String call() throws Exception {
                    Regist.log("登录成功。");
                    return Regist.assist.getStudentDetails().getName()+ " "+Regist.assist.getStudentDetails().getId();
                }
            };
        }
    }

    class TimeService extends ScheduledService<String>{

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected void updateValue(String value) {
                    super.updateValue(value);
                    Index.this.time.setText(value);
                }

                @Override
                protected String call() throws Exception {
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
                    // G 年代标志符  	y 年		M 月		 d 日    h 时 在上午或下午 (1~12)   H 时 在一天中 (0~23)   m 分   s 秒    S 毫秒
                   // E 星期 			D 一年中的第几天        	 F 一月中第几个星期几		w 一年中第几个星期		W 一月中第几个星期
                   // a 上午 / 下午 标记符 		k 时 在一天中 (1~24)		K 时 在上午或下午 (0~11)		z 时区
                    String dateString = formatter.format(date);
                    return dateString;
                }
            };
        }
    }
    private void getAllFiles(File file,ArrayList<File> getFiles) {
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isFile())        //若是文件，直接打印
                getFiles.add(f);
        }
    }
}
