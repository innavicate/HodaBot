package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;

public class DatabaseCreator {

    Gson gson;
    Type listType;
    ArrayList<JsonStatement> createStatements;

    static String defaultLocation = "create.json";

    public DatabaseCreator(Connection con) {
        gson = new GsonBuilder().create();
        listType = new TypeToken<ArrayList<JsonStatement>>(){}.getType();

        if(Files.exists(Paths.get(defaultLocation))) {
            try {
                fromJSON(defaultLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            createStatements = new ArrayList<>();
            createTables();
            try {
                toJSON(defaultLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


   private void fromJSON(String loc) throws IOException {
       System.out.println("FROM JSON");
       Reader reader = new FileReader(defaultLocation);
       createStatements = gson.fromJson(reader,listType);
       reader.close();
    }

    private void toJSON(String loc) throws IOException {
        System.out.println("TO JSON");
//        FileChooser save_file = new FileChooser();
//        save_file.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        File save_loc = new File(loc);

        if(save_loc == null) return;

        Writer writer = new FileWriter(save_loc);
        gson.toJson(createStatements,listType,writer);
        writer.close();
        System.out.println("Zapisywanie pliku json zakonczone");
    }



    public void createTables(){
        JsonStatement createGuildTable = new JsonStatement();

        createGuildTable.setTableName("GUILD");
        createGuildTable.addColumn(new Column("GUILD_ID", "int", "IDENTITY"));
        createGuildTable.addColumn(new Column("GUILD_NAME", "varchar(45)", "UNIQUE"));


        JsonStatement createPlayerTable = new JsonStatement();
        createPlayerTable.setTableName("PLAYER");
        createPlayerTable.addColumn(   new Column("PLAYER_ID", "int", "IDENTITY"));
        createPlayerTable.addColumn(   new Column("PLAYER_NAME", "varchar(45)", "UNIQUE"));
        createPlayerTable.addColumn(   new Column("PLAYER_GUILD_ID", "int", "")
                .setFK("GUILD", "GUILD_ID"));

        JsonStatement createBattleTable = new JsonStatement();
        createBattleTable.setTableName("BATTLE");
        createBattleTable.addColumn(new Column("BATTLE_ID", "int", "IDENTITY"));
        createBattleTable.addColumn(new Column("BATTLE_ENEMY_ID", "int", "")
                .setFK("PLAYER", "PLAYER_ID"));
        createBattleTable.addColumn(new Column("BATTLE_DATE", "TIMESTAMP", ""));
        createBattleTable.addColumn(new Column("BATTLE_RESULT_ID", "int", ""));

        JsonStatement createEventTable = new JsonStatement();
        createEventTable.setTableName("EVENT");
        createEventTable.addColumn(new Column("EVENT_ID", "int", "IDENTITY"));
        createEventTable.addColumn(new Column("EVENT_NAME", "varchar(200)", "UNIQUE"));
        createEventTable.addColumn(new Column("EVENT_DESC", "varchar(300)", ""));
        createEventTable.addColumn(new Column("EVENT_DATE", "TIMESTAMP", ""));


        JsonStatement createToonTable = new JsonStatement();
        createToonTable.setTableName("TOON");
        createToonTable.addColumn(new Column("TOON_ID", "int", "IDENTITY"));
        createToonTable.addColumn(new Column("TOON_NAME", "varchar(100)", "UNIQUE"));

        if(createGuildTable  !=  null) createStatements.add(createGuildTable);
        if(createPlayerTable !=  null) createStatements.add(createPlayerTable);
        if(createBattleTable !=  null) createStatements.add(createBattleTable);
        if(createEventTable  !=  null) createStatements.add(createEventTable);
        if(createToonTable   !=  null) createStatements.add(createToonTable);

    }


    private static class FK {
        String table;
        String from;
        String to;

        public FK(String table, String from, String to) {
            this.table = table;
            this.from = from;
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }

    public static class JsonStatement{

        String tableName;
        ArrayList<Column> columnsToCreate;

        public JsonStatement()
        {
            columnsToCreate = new ArrayList<>();
        }

        public JsonStatement(String tableName, ArrayList<Column> columnsToCreate) {
            this.tableName = tableName;
            this.columnsToCreate = columnsToCreate;
        }

        @Override
        public String toString() {
            return getSQL();
        }

        String getSQL() {
            String builder = "";
            builder += "CREATE TABLE IF NOT EXISTS " + getTableName() + " ( ";

            for (Column col : columnsToCreate) {
                builder += col.name + " " + col.type + " ";
                if (col.optional != null) builder += col.optional;
                builder += ",";
            }

            builder += " PRIMARY KEY (" + columnsToCreate.get(0).name + ")";
            boolean haveFK = false;
            for (Column col : columnsToCreate) {
                if (col.isFK()) {
                    FK key = col.getReference();
                    builder += ", FOREIGN KEY (" + key.from + ") REFERENCES " + key.table + "(" + key.to + ")";
                }
            }
                builder += ")";
//                System.out.println(builder);
                return builder;
        }

        public void addColumn(Column column){
            columnsToCreate.add(column);
        }

        public void addColumn(int index,Column column){
            columnsToCreate.add(index , column);
        }

        public ArrayList<Column> getColumnsToCreate() {
            return columnsToCreate;
        }

        void setTableName(String tableName){
             this.tableName = tableName;
        }

         String getTableName(){
           return tableName;
        }
    }

    public static class Column {
        String type;
        String name;
        String optional;
        FK reference;


        public Column(String name, String type, String optional) {
            this.type = type;
            this.name = name;
            this.reference = null;
            if (optional != null)
                this.optional = optional;
        }

       Column setFK(String tableName, String columnName) {
            Column returned = this;
            returned.reference = new FK(tableName, name, columnName);
            return returned;
        }

        FK getReference() {
            return reference;
        }

        boolean isFK() {
            return (reference != null);
        }

    }


}
