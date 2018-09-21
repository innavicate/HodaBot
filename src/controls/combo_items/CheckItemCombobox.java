package controls.combo_items;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckItemCombobox extends ItemCombobox implements Initializable {

    private static String resource_path = "/fxml/item_combobox.fxml";
    private static String css_path = "/styles/css/item_combobox.css";
    @FXML
    private VBox item_combobox;
    @FXML
    private HBox item_combobox_hbox;
    @FXML
    private Label item_combobox_label;
    @FXML
    private ComboBox<String> item_combobox_combo;
    private CheckBox checkbox;

    public CheckItemCombobox(String label_text) {
        super(label_text);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        checkbox = new CheckBox();
        item_combobox_hbox.getChildren().add(0, checkbox);
        item_combobox_combo.getItems().add("TEST");
        item_combobox_combo.setDisable(true);


        item_combobox_label.setText(label_textProperty().getValue());


        checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                item_combobox_combo.setDisable(!newValue);
            }
        });


    }
}
