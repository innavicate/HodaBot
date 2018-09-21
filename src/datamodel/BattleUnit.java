package datamodel;

public class BattleUnit {
    Toon battle_unit_toon;
    BattlePosition battle_unit_position;


    public Toon getBattle_unit_toon() {
        return battle_unit_toon;
    }

    public void setBattle_unit_toon(Toon battle_unit_toon) {
        this.battle_unit_toon = battle_unit_toon;
    }

    public BattlePosition getBattle_unit_position() {
        return battle_unit_position;
    }

    public void setBattle_unit_position(BattlePosition battle_unit_position) {
        this.battle_unit_position = battle_unit_position;
    }
}
