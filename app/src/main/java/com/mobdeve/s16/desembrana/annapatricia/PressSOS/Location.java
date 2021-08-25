package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

public class Location {
    // work in progress
    private String name;
    private int time;

    public Location (String name, int contactNumber) {
        this.name = name;
        this.time = contactNumber;
    }

    public String getName() { return name; }

    public int getTime() { return time; }
}
