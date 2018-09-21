package controls.hotkey;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;


public class HotkeyA extends Hotkey implements Initializable {

    private static String resourcename = "/fxml/hotkey.fxml";
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


    public HotkeyA(String text) {
        super(text, resourcename);
        this.hotkey.getStylesheets().add("styles/css/hotkey.css");
    }
}
