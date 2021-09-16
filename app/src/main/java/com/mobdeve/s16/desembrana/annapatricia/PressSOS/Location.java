package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import java.util.Date;

public class Location {

    private String lat, longt;
    private Date dDate;

    public Location (String lat, String longt, Date dDate) {
        this.lat = lat;
        this.longt = longt;
        this.dDate = dDate;
    }

    public String getLatitude() { return lat; }
    public String getLongitude() { return longt; }
    public Date getDate() { return dDate; }

    public void setLatitude(String lat) {this.lat = lat; }
    public void setLongitude(String longt) {this.longt = longt; }
    public void setLatitude(Date dDate) {this.dDate = dDate; }
}
