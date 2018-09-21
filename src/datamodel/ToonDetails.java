package datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ToonDetails {
    public SimpleStringProperty toon_detail_no;
    public SimpleIntegerProperty toon_attack_points;
    public SimpleIntegerProperty toon_health_points;
    public SimpleIntegerProperty toon_initiative;

    public ToonDetails() {
        this.toon_detail_no = new SimpleStringProperty(" NO");
    }

    public int getToon_attack_points() {
        return toon_attack_points.get();
    }

    public void setToon_attack_points(int toon_attack_points) {
        this.toon_attack_points.set(toon_attack_points);
    }

    public SimpleIntegerProperty toon_attack_pointsProperty() {
        return toon_attack_points;
    }

    public int getToon_health_points() {
        return toon_health_points.get();
    }

    public void setToon_health_points(int toon_health_points) {
        this.toon_health_points.set(toon_health_points);
    }

    public SimpleIntegerProperty toon_health_pointsProperty() {
        return toon_health_points;
    }

    public int getToon_initiative() {
        return toon_initiative.get();
    }

    public void setToon_initiative(int toon_initiative) {
        this.toon_initiative.set(toon_initiative);
    }

    public SimpleIntegerProperty toon_initiativeProperty() {
        return toon_initiative;
    }

    public String getToon_detail_no() {
        return toon_detail_no.get();
    }

    public void setToon_detail_no(String toon_detail_no) {
        this.toon_detail_no.set(toon_detail_no);
    }

    public SimpleStringProperty toon_detail_noProperty() {
        return toon_detail_no;
    }
}
