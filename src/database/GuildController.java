package database;

import datamodel.Guild;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class GuildController extends DatabaseRecord<Guild> {

    public GuildController(String table_name) {
        super(table_name);

        try {
            constructor = Guild.class.getConstructor(int.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void addRecord(Guild guild) throws SQLException {
        Connection con = DatabaseController.getInstance().getConnection();

        if (recordAlreadyExists("GUILD_NAME",guild.getGuild_name())) {
            System.out.println("GUILD NOT FOUND - > ADDING NEW ONE");
            PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO " + table_name + " VALUES (?,?)");
            prepareStatement.setNull(1, Types.INTEGER);
            prepareStatement.setString(2, guild.getGuild_name());
            prepareStatement.executeUpdate();
            prepareStatement.close();
        }
    }

}



