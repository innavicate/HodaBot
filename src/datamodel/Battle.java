package datamodel;

import database.DatabaseController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Date;
import java.util.Objects;

public class Battle {

    SimpleIntegerProperty battle_id;
    SimpleObjectProperty<Player> enemyPlayer;

    SimpleObjectProperty<Date> battle_date;
    SimpleObjectProperty<BattleResult> battle_result;





    public Battle(int enemyPlayer, Date battle_date, int battle_result) {
        this.enemyPlayer = new SimpleObjectProperty<>(getPlayer(enemyPlayer));
        this.battle_date =  new SimpleObjectProperty<>(battle_date);
        this.battle_result = new SimpleObjectProperty<>(BattleResult.get(battle_result));
    }

    public Battle(int battle_id, int enemyPlayer, Date battle_date, int battle_result) {
        this.battle_id = new SimpleIntegerProperty(battle_id);
        this.enemyPlayer = new SimpleObjectProperty<>(getPlayer(enemyPlayer));
        this.battle_date =  new SimpleObjectProperty<>(battle_date);
        this.battle_result = new SimpleObjectProperty<>(BattleResult.get(battle_result));
    }

    public Player getPlayer(int id) {
        return DatabaseController.getInstance().getPlayerController().getPlayer(id);
    }


    public int getBattle_id() {
        return battle_id.get();
    }

    public SimpleIntegerProperty battle_idProperty() {
        return battle_id;
    }

    public void setBattle_id(int battle_id) {
        this.battle_id.set(battle_id);
    }

    public Player getEnemyPlayer() {
        return enemyPlayer.get();
    }

    public SimpleObjectProperty<Player> enemyPlayerProperty() {
        return enemyPlayer;
    }

    public void setEnemyPlayer(Player enemyPlayer) {
        this.enemyPlayer.set(enemyPlayer);
    }

    public Date getBattle_date() {
        return battle_date.get();
    }

    public SimpleObjectProperty<Date> battle_dateProperty() {
        return battle_date;
    }

    public void setBattle_date(Date battle_date) {
        this.battle_date.set(battle_date);
    }

    public BattleResult getBattle_result() {
        return battle_result.get();
    }

    public SimpleObjectProperty<BattleResult> battle_resultProperty() {
        return battle_result;
    }

    public void setBattle_result(BattleResult battle_result) {
        this.battle_result.set(battle_result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battle battle = (Battle) o;
        return Objects.equals(enemyPlayer, battle.enemyPlayer) &&
                Objects.equals(battle_date, battle.battle_date) &&
                Objects.equals(battle_result, battle.battle_result);
    }

}
