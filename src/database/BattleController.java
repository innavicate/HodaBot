package database;


import datamodel.Battle;

import java.sql.*;

public class BattleController extends DatabaseRecord<Battle> {



    public BattleController(String name) {
        super(name);
        try {
           constructor = Battle.class.getConstructor(int.class, int.class, java.util.Date.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public void addRecord(Battle battle) throws SQLException {
        Connection con = DatabaseController.getInstance().getConnection();
        if (recordAlreadyExists("BATTLE_DATE", battle.getBattle_date())) {
            PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO " + table_name + " VALUES (?,?,?,?)");
            prepareStatement.setNull(1, Types.INTEGER);
            prepareStatement.setInt(2, battle.getEnemyPlayer().getPlayer_id());
            prepareStatement.setTimestamp(3,new Timestamp(battle.getBattle_date().getTime()));
            prepareStatement.setInt(4, battle.getBattle_result().intValue());
            prepareStatement.executeUpdate();
        }
        else
            System.out.println("BATTLE ALREADY EXISTS IN DATABSE");
    }








}
