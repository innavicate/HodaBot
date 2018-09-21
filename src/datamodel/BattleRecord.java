package datamodel;

import java.util.Calendar;

public class BattleRecord {
    Player battle_record_enemy;
    Calendar battle_record_date;
    BattleResult battle_record_result;
    BattleRecordDetails battle_record_details;

    public BattleRecord() {
        this.battle_record_enemy = null;
    }


    public Player getBattle_record_enemy() {
        return battle_record_enemy;
    }

    public void setBattle_record_enemy(Player battle_record_enemy) {
        this.battle_record_enemy = battle_record_enemy;
    }

    public Calendar getBattle_record_date() {
        return battle_record_date;
    }

    public void setBattle_record_date(Calendar battle_record_date) {
        this.battle_record_date = battle_record_date;
    }

    public BattleResult getBattle_record_result() {
        return battle_record_result;
    }

    public void setBattle_record_result(BattleResult battle_record_result) {
        this.battle_record_result = battle_record_result;
    }

    public BattleRecordDetails getBattle_record_details() {
        return battle_record_details;
    }

    public void setBattle_record_details(BattleRecordDetails battle_record_details) {
        this.battle_record_details = battle_record_details;
    }
}
