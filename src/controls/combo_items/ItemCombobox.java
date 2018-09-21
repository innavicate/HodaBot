package controls.combo_items;

import app.Utility;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemCombobox extends VBox implements Initializable {

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
    private ObservableList<String> items;
    private SimpleStringProperty label_text;


    public ItemCombobox(String text) {
        label_text = new SimpleStringProperty(text);
        Utility.loadResource(resource_path,this);
        this.getStylesheets().add(css_path);
    }

    public static String getResource_path() {
        return resource_path;
    }

    public static void setResource_path(String resource_path) {
        ItemCombobox.resource_path = resource_path;
    }

    public static String getCss_path() {
        return css_path;
    }

    public static void setCss_path(String css_path) {
        ItemCombobox.css_path = css_path;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.items = FXCollections.observableArrayList();
        this.item_combobox_label.setText(label_text.getValue());
        this.item_combobox_combo.setItems(items);

       CheckBox checkbox = new CheckBox();
       checkbox.setVisible(false);
        item_combobox_hbox.getChildren().add(0, checkbox);



    }

    public VBox getItem_combobox() {
        return item_combobox;
    }

    public void setItem_combobox(VBox item_combobox) {
        this.item_combobox = item_combobox;
    }

    public HBox getItem_combobox_hbox() {
        return item_combobox_hbox;
    }

    public void setItem_combobox_hbox(HBox item_combobox_hbox) {
        this.item_combobox_hbox = item_combobox_hbox;
    }

    public Label getItem_combobox_label() {
        return item_combobox_label;
    }

    public void setItem_combobox_label(Label item_combobox_label) {
        this.item_combobox_label = item_combobox_label;
    }

    public ComboBox<String> getItem_combobox_combo() {
        return item_combobox_combo;
    }

    public void setItem_combobox_combo(ComboBox<String> item_combobox_combo) {
        this.item_combobox_combo = item_combobox_combo;
    }

    public ObservableList<String> getItems() {
        return items;
    }

    public void setItems(ObservableList<String> items) {
        this.items = items;
    }

    public String getLabel_text() {
        return label_text.get();
    }

    public void setLabel_text(String label_text) {
        this.label_text.set(label_text);
    }

    public SimpleStringProperty label_textProperty() {
        return label_text;
    }
}
