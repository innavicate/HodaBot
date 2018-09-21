package datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BattlePosition {

    static BattlePosition FRONT_ROW_1 = new BattlePosition(0, "FR 1");
    static BattlePosition FRONT_ROW_2 = new BattlePosition(0, "FR 2");
    static BattlePosition BACK_ROW_1 = new BattlePosition(0, "BR 1");
    static BattlePosition BACK_ROW_2 = new BattlePosition(0, "BR 2");
    static BattlePosition LC = new BattlePosition(0, "LC");
    SimpleIntegerProperty battle_position_id;
    SimpleStringProperty battle_postion_name;


    private BattlePosition(Integer id, String name) {
        this.battle_position_id = new SimpleIntegerProperty(id);
        this.battle_postion_name = new SimpleStringProperty(name);
    }


    public int getBattle_position_id() {
        return battle_position_id.get();
    }

    public void setBattle_position_id(int battle_position_id) {
        this.battle_position_id.set(battle_position_id);
    }

    public SimpleIntegerProperty battle_position_idProperty() {
        return battle_position_id;
    }

    public String getBattle_postion_name() {
        return battle_postion_name.get();
    }

    public void setBattle_postion_name(String battle_postion_name) {
        this.battle_postion_name.set(battle_postion_name);
    }

    public SimpleStringProperty battle_postion_nameProperty() {
        return battle_postion_name;
    }
}
