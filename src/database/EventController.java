package database;

import datamodel.Event;

import java.sql.*;
import java.util.ArrayList;


public class EventController extends DatabaseRecord<Event> {

    public EventController(String name) {
        super(name);

        try {
            constructor = Event.class.getConstructor(int.class, String.class, String.class, java.util.Date.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void addRecord( Event event) throws SQLException {
        Connection con = DatabaseController.getInstance().getConnection();

        if (recordAlreadyExists("EVENT_NAME",event.getEvent_name())) {
            System.out.println("EVENT NOT FOUND - > ADDING NEW ONE");
            PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO " + table_name + " VALUES (?,?,?,?) ");

            prepareStatement.setNull(1, Types.INTEGER);
            prepareStatement.setString(2, event.getEvent_name());
            prepareStatement.setString(3, event.getEvent_desciption());
            prepareStatement.setDate(4,new java.sql.Date(event.getEvent_date().getTime()));
            prepareStatement.executeUpdate();
            System.out.println(prepareStatement.toString());
            con.commit();
        }


    }

    @Override
    public ResultSet query(String columnName, Object neddle) throws SQLException {
        return super.query(columnName, neddle);
    }



    @Override
    public Event toObject(int columnNumber, ResultSet resultSet) throws SQLException {
        return super.toObject(columnNumber, resultSet);
    }

    @Override
    public ArrayList<Event> getAll() throws SQLException {
        return super.getAll();
    }
}
