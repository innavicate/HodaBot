package view;

import app.Utility;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DatabaseRecord extends AnchorPane implements Initializable {

    @FXML
    private ImageView record_image;

    @FXML
    private Label label_name;

    @FXML
    private Label label_showmore;

    SimpleStringProperty name;

    void setImage()
    {
        Utility.setIcon(50,50, record_image, "/styles/unknown2.png");
    }



    public DatabaseRecord(String name)
    {
        Utility.loadResource("/fxml/databaseRecord.fxml",this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImage();
    }
}
