package view;

import app.Utility;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DatabaseCategoryItem extends AnchorPane implements Initializable {

    @FXML
    private AnchorPane item;

    @FXML
    private ImageView item_image;

    @FXML
    private Label item_name;

    @FXML
    private Label item_description;


    String item_fullname_string, item_description_string;



    public DatabaseCategoryItem(String name, String desc)
    {
        item_fullname_string = name;
        item_description_string = desc;
        Utility.loadResource("/fxml/databaseCategoryItem.fxml",this);

    }

    private void setIcon( String icon_name) {
        ImageView icon = new ImageView();
        try {
            BufferedImage image = ImageIO.read(getClass().getResource(icon_name));
            item_image.setImage(SwingFXUtils.toFXImage(image, null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        icon.setFitWidth(70.0);
        icon.setFitHeight(70.0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        item_name.setText(item_fullname_string);
        item_description.setText(item_description_string);
        setIcon("/styles/icons/icon_quest.png");
    }

    public AnchorPane getItem() {
        return item;
    }

    public void setItem(AnchorPane item) {
        this.item = item;
    }

    public ImageView getItem_image() {
        return item_image;
    }

    public void setItem_image(ImageView item_image) {
        this.item_image = item_image;
    }

    public Label getItem_name() {
        return item_name;
    }

    public void setItem_name(Label item_name) {
        this.item_name = item_name;
    }

    public Label getItem_description() {
        return item_description;
    }

    public void setItem_description(Label item_description) {
        this.item_description = item_description;
    }

    public String getItem_fullname_string() {
        return item_fullname_string;
    }

    public void setItem_fullname_string(String item_fullname_string) {
        this.item_fullname_string = item_fullname_string;
    }

    public String getItem_description_string() {
        return item_description_string;
    }

    public void setItem_description_string(String item_description_string) {
        this.item_description_string = item_description_string;
    }
}
