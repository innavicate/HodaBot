package notifications;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Confirm extends AnchorPane implements Initializable {

    @FXML
    private Label description;

    @FXML
    private JFXButton button_apply;

    @FXML
    private JFXButton button_discard;

    public JFXButton getButton_apply() {
        return button_apply;
    }

    public JFXButton getButton_discard() {
        return button_discard;
    }

    public Label getDescription() {
        return description;
    }

    public void setDescription(Label description) {
        this.description = description;
    }

    public void loadResource(String resourceName) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceName));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Confirm()
    {
        loadResource("/fxml/notifications/confirm.fxml");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.description.setText("Do you wish to save changes ?");
        System.out.println("NOTIFICATION");
    }
}
