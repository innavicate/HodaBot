package controls.hotkey;

import app.Utility;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class Hotkey extends VBox implements Initializable {

    @FXML
    private VBox hotkey;

    @FXML
    private Label hotkey_key_area;

    @FXML
    private TextField hotkey_key_input;

    @FXML
    private Label hotkey_label;


    private KeyCode keycode;
    private String label_text;
    private SimpleBooleanProperty assigned , key_changed;

    public Hotkey(String text, String resourcename) {
        this.label_text = text;
        this.assigned = new SimpleBooleanProperty(false);
        this.key_changed = new SimpleBooleanProperty(false);
        this.keycode = KeyCode.UNDEFINED;
        Utility.loadResource(resourcename, this);
    }

    public VBox getHotkey() {
        return hotkey;
    }

    public void setHotkey(VBox hotkey) {
        this.hotkey = hotkey;
    }

    public Label getHotkey_key_area() {
        return hotkey_key_area;
    }

    public void setHotkey_key_area(Label hotkey_key_area) {
        this.hotkey_key_area = hotkey_key_area;
    }

    public TextField getHotkey_key_input() {
        return hotkey_key_input;
    }

    public void setHotkey_key_input(TextField hotkey_key_input) {
        this.hotkey_key_input = hotkey_key_input;
    }

    public Label getHotkey_label() {
        return hotkey_label;
    }

    public void setHotkey_label(Label hotkey_label) {
        this.hotkey_label = hotkey_label;
    }

    public String getLabel_text() {
        return label_text;
    }

    public void setLabel_text(String label_text) {
        this.label_text = label_text;
    }

    public KeyCode getKeycode() {
        return keycode;
    }

    public void setKeycode(KeyCode keycode) {
        this.keycode = keycode;
    }

    public boolean isAssigned() {
        return assigned.get();
    }

    public void setAssigned(boolean assigned) {
        this.assigned.set(assigned);
    }

    public boolean isKey_changed() {
        return key_changed.get();
    }

    public SimpleBooleanProperty key_changedProperty() {
        return key_changed;
    }

    public void setKey_changed(boolean key_changed) {
        this.key_changed.set(key_changed);
    }

    public SimpleBooleanProperty assignedProperty() {
        return assigned;
    }


    void assignKey(KeyCode code, Hotkey key) {
        if (key.assignedProperty().getValue()) {
            System.out.println("KEY ALREADY ASSIGNED POPUP");

            if(!keycode.equals(code))
            {
                System.out.println("ASSIGNED KEY CHANGED");
                key_changed.setValue(true);
            }

            //TODO CONFIRMATION FOR KEY BINDING CHANGE
        }

        hotkey_key_input.setText(code.getName());
        System.out.println("KEY " + code.getName());
        this.keycode = code;
        this.assigned.setValue(true);
        this.key_changed.setValue(true);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.hotkey_label.setText(label_text);

        System.out.println("INIT");
        this.hotkey_key_input.setEditable(false);

        this.hotkey_key_input.setOnMouseClicked(mouseevent -> {

            this.hotkey_key_input.clear();
            this.hotkey_key_input.requestFocus();
            mouseevent.consume();
        });

        this.hotkey_key_input.setOnKeyPressed(keyevent -> {
            assignKey(keyevent.getCode(),this);

            hotkey.requestFocus();
            keyevent.consume();

        });
    }
}
