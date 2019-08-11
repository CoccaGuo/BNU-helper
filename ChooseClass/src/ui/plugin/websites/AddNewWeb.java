package ui.plugin.websites;

import com.alibaba.fastjson.JSON;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.component.Website;
import ui.scene.Index;

public class AddNewWeb {

    @FXML
    private TextField title;

    @FXML
    private TextField url;

    @FXML
    void add(ActionEvent event) {
        Item item = new Item();
        item.setTitle(title.getText());
        item.setUrl(url.getText());
        Index.websites.add(item);
//        String json = JSON.toJSONString(Index.websites);
//        try {
//            FileUtils.writeStringToFile(new File("website.json"),json,Charset.defaultCharset(),false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
       Stage stage = (Stage)title.getParent().getScene().getWindow();
        stage.close();
    }

}
