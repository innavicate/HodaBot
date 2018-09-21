package datamodel;


import java.util.Date;


public class Event {

    int event_id;
    String event_name;
    String event_desciption;
    Date event_date;


    public Event(int event_id, String event_name, String event_desciption, Date event_date) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_desciption = event_desciption;
        this.event_date = event_date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event_id=" + event_id +
                ", event_name='" + event_name + '\'' +
                ", event_date=" + event_date +
                ", event_desciption='" + event_desciption + '\'' +
                '}';
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_desciption() {
        return event_desciption;
    }

    public void setEvent_desciption(String event_desciption) {
        this.event_desciption = event_desciption;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }
}
