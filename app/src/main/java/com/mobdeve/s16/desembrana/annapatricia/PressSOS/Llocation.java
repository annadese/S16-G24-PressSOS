package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

public class Llocation {

    private String lat, longt, dDate;

    public Llocation (String lat, String longt, String dDate) {
        this.lat = lat;
        this.longt = longt;
        this.dDate = dDate;
    }

    public String getLatitude() { return lat; }
    public String getLongitude() { return longt; }
    public String getDate() { return dDate; }

    public void setLatitude(String lat) {this.lat = lat; }
    public void setLongitude(String longt) {this.longt = longt; }
    public void setDate(String dDate) {this.dDate = dDate; }
}
