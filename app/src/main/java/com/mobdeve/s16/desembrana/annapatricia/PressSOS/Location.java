package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import java.util.Date;

public class Location {
    private long id;
    private String locationName;
    private Date timestamp;

    public Location (String locationName, Date timestamp, long id) {
        this.locationName = locationName;
        this.timestamp = timestamp;
        this.id = id;
    }

    public String getLocationName() { return locationName; }
    public Date getTime() { return timestamp; }

    public void setLocationName() {this.locationName = locationName; }
    public void setTime(Date timestamp) { this.timestamp = timestamp; }
}
