package view;

import datamodel.Battle;
import datamodel.BattleRecordDetails;
import datamodel.BattleResult;
import javafx.beans.property.SimpleObjectProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TableHistoryItem  {
    Battle battle;

    SimpleObjectProperty table_history_item_player;
    SimpleObjectProperty<BattleResult> table_history_item_result;
    SimpleObjectProperty<String> table_history_item_time;
    SimpleObjectProperty<BattleRecordDetails> table_history_item_details;
    static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");


    public TableHistoryItem(Battle b) {
        this.battle = b;
        table_history_item_player = new SimpleObjectProperty(battle.getEnemyPlayer().getPlayer_name());
        table_history_item_result = new SimpleObjectProperty(battle.getBattle_result());
        table_history_item_time = new SimpleObjectProperty(battle.getBattle_date());
        table_history_item_details = new SimpleObjectProperty<BattleRecordDetails>(new BattleRecordDetails());
    }

    public Object getTable_history_item_player() {
        return table_history_item_player.get();
    }

    public SimpleObjectProperty table_history_item_playerProperty() {
        return table_history_item_player;
    }

    public void setTable_history_item_player(Object table_history_item_player) {
        this.table_history_item_player.set(table_history_item_player);
    }

    public void setTable_history_item_player(int table_history_item_player) {
        this.table_history_item_player.set(table_history_item_player);
    }


    public BattleResult getTable_history_item_result() {
        return table_history_item_result.get();
    }

    public void setTable_history_item_result(BattleResult table_history_item_result) {
        this.table_history_item_result.set(table_history_item_result);
    }

    public SimpleObjectProperty<BattleResult> table_history_item_resultProperty() {
        return table_history_item_result;
    }

    public String getTable_history_item_time() {
        return table_history_item_time.get();
    }

    public SimpleObjectProperty<String> table_history_item_timeProperty() {
        return table_history_item_time;
    }

    public void setTable_history_item_time(String table_history_item_time) {
        this.table_history_item_time.set(table_history_item_time);
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(DateFormat dateFormat) {
        TableHistoryItem.dateFormat = dateFormat;
    }

    public BattleRecordDetails getTable_history_item_details() {
        return table_history_item_details.get();
    }

    public void setTable_history_item_details(BattleRecordDetails table_history_item_details) {
        this.table_history_item_details.set(table_history_item_details);
    }

    public SimpleObjectProperty<BattleRecordDetails> table_history_item_detailsProperty() {
        return table_history_item_details;
    }
}
