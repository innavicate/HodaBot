package datamodel;

import javafx.beans.property.SimpleIntegerProperty;

public class BattleRecordDetails {

    SimpleIntegerProperty total_dmg;

    public BattleRecordDetails() {
        this.total_dmg = new SimpleIntegerProperty(0);
    }

    @Override
    public String toString() {
        return total_dmg.getValue() + " dmg";
    }
}
