package database;

import datamodel.Player;

import java.sql.*;
import java.util.ArrayList;


public class PlayerController extends DatabaseRecord<Player>{

    public PlayerController(String table_name) {
        super(table_name);

        try {
            constructor = Player.class.getConstructor(int.class, String.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRecord(Player player) throws SQLException {
        Connection con = DatabaseController.getInstance().getConnection();
        if (recordAlreadyExists("PLAYER_NAME", player.getPlayer_name())) {
            PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO " + table_name + " VALUES (?,?,?)");

            prepareStatement.setNull(1, Types.INTEGER);
            prepareStatement.setString(2, player.getPlayer_name());
            prepareStatement.setInt(3, player.getPlayer_guild_id());
            prepareStatement.executeUpdate();
            prepareStatement.close();
        }
    }

    public Player getPlayer(int id){
        try {
            ResultSet results= DatabaseController.getInstance().getPlayerController().query("PLAYER_ID",id);
            int columnNumber = results.getMetaData().getColumnCount();
            ArrayList<Player> players = resultsToObject(columnNumber,results);
            if(players!= null){
                System.out.println(players.get(0));
                return players.get(0);
            }
            else{
                System.out.println("Player not found");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}

