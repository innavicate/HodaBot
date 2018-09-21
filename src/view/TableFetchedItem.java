package view;

import database.DatabaseController;
import database.ToonController;
import datamodel.Avatar;
import datamodel.Toon;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class TableFetchedItem {
    public SimpleObjectProperty fetched_toon_avatar;
    public SimpleStringProperty fetched_toon_name;
    public SimpleStringProperty fetched_toon_position;
    public SimpleStringProperty fetched_toon_details;

    @FXML
    private ImageView img;


    public TableFetchedItem(Toon toon) {
        fetched_toon_avatar = new SimpleObjectProperty(new ImageView());
        fetched_toon_name = new SimpleStringProperty(toon.toon_name.getValue());
        fetched_toon_position = new SimpleStringProperty("###");
        fetched_toon_details = new SimpleStringProperty("#");

        ImageView img = (ImageView) fetched_toon_avatar.getValue();
        img.setFitWidth(55);
        img.setFitHeight(42);
        img.setImage(new Avatar(toon.getToon_id()).getAvatar());
    }


    public Object getFetched_toon_avatar() {
        return fetched_toon_avatar.get();
    }

    public void setFetched_toon_avatar(Object fetched_toon_avatar) {
        this.fetched_toon_avatar.set(fetched_toon_avatar);
    }

    public SimpleObjectProperty fetched_toon_avatarProperty() {
        return fetched_toon_avatar;
    }

    public String getFetched_toon_name() {
        return fetched_toon_name.get();
    }

    public void setFetched_toon_name(String fetched_toon_name) {
        this.fetched_toon_name.set(fetched_toon_name);
    }

    public SimpleStringProperty fetched_toon_nameProperty() {
        return fetched_toon_name;
    }

    public String getFetched_toon_position() {
        return fetched_toon_position.get();
    }

    public void setFetched_toon_position(String fetched_toon_position) {
        this.fetched_toon_position.set(fetched_toon_position);
    }

    public SimpleStringProperty fetched_toon_positionProperty() {
        return fetched_toon_position;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public String getFetched_toon_details() {
        return fetched_toon_details.get();
    }

    public void setFetched_toon_details(String fetched_toon_details) {
        this.fetched_toon_details.set(fetched_toon_details);
    }

    public SimpleStringProperty fetched_toon_detailsProperty() {
        return fetched_toon_details;
    }

    @Override
    public String toString() {
        return "TableFetchedItem{" +
                "fetched_toon_avatar=" + fetched_toon_avatar +
                ", fetched_toon_name=" + fetched_toon_name +
                ", fetched_toon_position=" + fetched_toon_position +
                ", fetched_toon_details=" + fetched_toon_details +
                ", img=" + img +
                ", fetchedToon=" +
                '}';
    }
}
