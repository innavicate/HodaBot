package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseController  {

    private PlayerController playerController;
    private GuildController guildController;
    private BattleController battleController;
    private EventController eventController;
    private ToonController toonControler;

   static public DatabaseController instance;


   private DatabaseCreator databaseCreator;
   private Connection connection;



    public DatabaseController() {

        try {
            setConnection(DriverManager.getConnection("jdbc:h2:~/fileDB", "", ""));
            databaseCreator = new DatabaseCreator(connection);
            setupControllers();

            boolean connected = !connection.isClosed();
            System.out.println("Connected? : " + connected);

            setInstance(this);


            Statement stmt = connection.createStatement();
            databaseCreator.createStatements.forEach(statement -> {
                try {
                    stmt.executeUpdate(statement.getSQL());
                    connection.commit();
                    System.out.println(statement.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            stmt.close();

            System.out.println("EXIT");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setupControllers() throws NoSuchMethodException {
        battleController = new BattleController("BATTLE");
        eventController = new EventController("EVENT");
        guildController = new GuildController("GUILD");
        playerController = new PlayerController("PLAYER");
        toonControler = new ToonController("TOON");
    }


    public static DatabaseController getInstance() {
        return instance;
    }

    public static void setInstance(DatabaseController instance) {
        DatabaseController.instance = instance;
    }


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private boolean isExecuted(int val) {
        return val > 0;
    }


    public PlayerController getPlayerController() {
        return playerController;
    }

    public GuildController getGuildController() {
        return guildController;
    }

    public BattleController getBattleController() {
        return battleController;
    }

    public EventController getEventController() {
        return eventController;
    }

    public ToonController getToonControler() { return toonControler; }
}


