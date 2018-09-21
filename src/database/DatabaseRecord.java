package database;

import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class DatabaseRecord<T> {
    String table_name;
    Constructor<?> constructor;

    public DatabaseRecord(String table_name) {
        this.table_name = table_name;
    }

    public abstract void addRecord( T object) throws SQLException;

    public ResultSet query(String columnName, Object neddle) throws SQLException {
        Connection con = DatabaseController.getInstance().getConnection();
        PreparedStatement prepareStatement = con.prepareStatement("SELECT * FROM "+table_name +" WHERE "+ columnName +" = (?)");
        prepareStatement.setObject(1,neddle);

        ResultSet resultSet =  prepareStatement.executeQuery();

        return resultSet;
    }

    public ResultSet queryBytes(String columnName, Object neddle) throws SQLException {

        byte[] bytes = ((String)neddle).getBytes();
        byte[] queryBytes = Arrays.copyOfRange(bytes,0,bytes.length-1);
        Connection con = DatabaseController.getInstance().getConnection();
        PreparedStatement prepareStatement = con.prepareStatement("SELECT * FROM "+table_name +" WHERE "+ columnName +" = ?)");
        prepareStatement.setBytes(1,queryBytes);

        ResultSet resultSet =  prepareStatement.executeQuery();

        return resultSet;
    }





    public boolean recordAlreadyExists(String columnName, Object neddle) throws SQLException {
        ResultSet resultsExists = query(columnName, neddle);
        int columnNumber = resultsExists.getMetaData().getColumnCount();
        return resultsToObject(columnNumber, resultsExists).isEmpty();
    }


    public ArrayList<T> resultsToObject(int columnCount , ResultSet res) throws SQLException {
        ArrayList<T> resultList = new ArrayList<>();

        while (res.next()) {
            resultList.add(toObject(columnCount,res));
        }
        return resultList;
    }

    public T toObject(int columnNumber, ResultSet resultSet) throws SQLException {
        String columnTypes[] = new String[columnNumber];

        for(int i =0; i<columnNumber; i++)
            columnTypes[i] = resultSet.getMetaData().getColumnTypeName(i+1);

        Object[] items = new Object[columnNumber];

        for (int i = 0; i < columnNumber; i++){
            //System.out.println(columnTypes[i]);

            if(columnTypes[i].equals("TIMESTAMP WITH TIME ZONE"))
                items[i] = resultSet.getTimestamp((i + 1));
            else
                items[i] = resultSet.getObject((i + 1));

            System.out.println(items[i]);
        }
        try {
            T returned = (T) constructor.newInstance(items);
            return returned;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<T> getAll() throws SQLException {
        Connection con = DatabaseController.getInstance().getConnection();
        Statement s = con.createStatement();

        ResultSet resultSet = s.executeQuery("SELECT * FROM " + table_name);
        int columns = resultSet.getMetaData().getColumnCount();
        ArrayList<T> list =  resultsToObject(columns, resultSet);


        s.close();
        return list;
    }

    public Constructor<?> getConstructor() {
        return constructor;
    }
}
