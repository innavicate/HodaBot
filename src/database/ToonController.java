package database;

import datamodel.Toon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToonController extends DatabaseRecord<Toon> {

    public ToonController(String name){
     super(name);

        try {
            constructor = Toon.class.getConstructor(int.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRecord(Toon object) throws SQLException {
    }

    public Toon getToon(String name){
        try {
            ResultSet results= DatabaseController.getInstance().getToonControler().query("TOON_NAME",name);
            int columnNumber = results.getMetaData().getColumnCount();
            ArrayList<Toon> toons = resultsToObject(columnNumber,results);
            if(toons!= null && !toons.isEmpty()){
                System.out.println(toons.get(0));
                return toons.get(0);
            }
            else{
                System.out.println("Toon not found");
                return Toon.getDefault_toon();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
