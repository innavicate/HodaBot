package datamodel;

import database.DatabaseController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Toon {

    private static Toon default_toon  = new Toon(0, "Default name");

    public SimpleIntegerProperty toon_id;
    public SimpleStringProperty toon_name;
    public Avatar toon_avatar;
    public ToonDetails toon_details;



    public Toon(int id, String name) {
        toon_id = new SimpleIntegerProperty(id);
        toon_name = new SimpleStringProperty(name);
        toon_details = new ToonDetails();
    }

    public static Toon getToon(String valueFromScreenReader){
        //GET TOON FROM DAMN DATABASE BY NAME
        Toon toon = DatabaseController.getInstance().getToonControler().getToon(valueFromScreenReader);
        if(toon != null)
            return toon;
        else
            return Toon.default_toon;
    }

    public boolean isDefaultToon(){
        return this.equals(default_toon);
    }


    public void setToon_avatar(Avatar toon_avatar) {
        this.toon_avatar = toon_avatar;
    }

    public int getToon_id() {
        return toon_id.get();
    }

    public void setToon_id(int toon_id) {
        this.toon_id.set(toon_id);
    }

    public SimpleIntegerProperty toon_idProperty() {
        return toon_id;
    }

    public String getToon_name() {
        return toon_name.get();
    }

    public void setToon_name(String toon_name) {
        this.toon_name.set(toon_name);
    }

    public SimpleStringProperty toon_nameProperty() {
        return toon_name;
    }

    public ToonDetails getToon_details() {
        return toon_details;
    }

    public void setToon_details(ToonDetails toon_details) {
        this.toon_details = toon_details;
    }

    public static Toon getDefault_toon() {
        return default_toon;
    }

    public Avatar getToon_avatar() {
        return toon_avatar;
    }
}
