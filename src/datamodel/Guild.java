package datamodel;

public class Guild {

    int guild_id;
    String guild_name;

    public Guild(int guild_id, String guild_name) {
        this.guild_id = guild_id;
        this.guild_name = guild_name;
    }



    public int getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(int guild_id) {
        this.guild_id = guild_id;
    }

    public String getGuild_name() {
        return guild_name;
    }

    public void setGuild_name(String guild_name) {
        this.guild_name = guild_name;
    }

    @Override
    public String toString() {
        return "Guild{" +
                "guild_id=" + guild_id +
                ", guild_name='" + guild_name + '\'' +
                '}';
    }
}
