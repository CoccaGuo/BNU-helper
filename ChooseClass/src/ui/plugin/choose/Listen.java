package ui.plugin.choose;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Listen {

    @FXML
    private RadioButton type_any;

    @FXML
    private ToggleGroup it;

    @FXML
    private RadioButton type_fliter;

    @FXML
    private CheckBox nameWith;

    @FXML
    private TextField nameWithInput;

    @FXML
    private TextField typeWithInput;

    @FXML
    private CheckBox typeIs;

    @FXML
    private RadioButton toast;

    @FXML
    private ToggleGroup tusuchi;

    @FXML
    private RadioButton alert;

    @FXML
    private RadioButton chooseClass;

    @FXML
    private ToggleGroup choose;

    @FXML
    private RadioButton nothing;

    @FXML
    private Button begin;

    @FXML
    void initialize(){
        type_fliter.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                typeIs.setDisable(false);
                typeWithInput.setDisable(false);
                nameWith.setDisable(false);
                nameWithInput.setDisable(false);
            }
            if (!newValue){
                typeIs.setDisable(true);
                typeWithInput.setDisable(true);
                nameWith.setDisable(true);
                nameWithInput.setDisable(true);
            }
        });
    }

    @FXML
    void begin(ActionEvent event) {

    }

}
