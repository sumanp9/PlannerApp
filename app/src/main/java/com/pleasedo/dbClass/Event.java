package com.pleasedo.dbClass;

public class Event {

    private String eventID;
    private String eventTitle;
    private String eventDetails;
    private String eventStartDate;
    private String eventEndDate;
    private String eventTime;


    public Event(){}

    public Event(String evenTitle, String eventDetails, String eventStartDate, String eventEndDate, String eventTime){
        this.eventTitle = evenTitle;
        this.eventDetails =eventDetails;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventTime = eventTime;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(String eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(String eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
