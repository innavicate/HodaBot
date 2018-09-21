package datamodel;

public class Player {

    int player_id;
    String player_name;
    int player_guild_id;


    public Player(int player_id, String player_name, int player_guild_id) {
        this.player_id = player_id;
        this.player_name = player_name;
        this.player_guild_id = player_guild_id;
    }

    public Player( String player_name, int player_guild_id) {
        this.player_name = player_name;
        this.player_guild_id = player_guild_id;
    }




    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public int getPlayer_guild_id() {
        return player_guild_id;
    }

    public void setPlayer_guild_id(int player_guild_id) {
        this.player_guild_id = player_guild_id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }


}