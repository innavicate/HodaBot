package notifications;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class NotificationController extends HBox  {


    @FXML
    private HBox notification_container;

    public static NotificationController contr;

    public static NotificationController getContr() {
        return contr;
    }

    public static void setContr(NotificationController contr) {
        NotificationController.contr = contr;
    }


    public NotificationController() {
       loadResource("/fxml/notification.fxml");
   }

    public void set_content(Node node)
    {
        notification_container.getChildren().clear();
        notification_container.getChildren().add(node);
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


}
