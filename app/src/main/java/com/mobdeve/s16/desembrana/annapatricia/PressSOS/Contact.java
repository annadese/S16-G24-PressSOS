package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

public class Contact {

    private int id;
    private String name;
    private String contactNumber;

    public Contact (String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public Contact (String name, String contactNumber, int id) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.id = id;
    }

    public String getName() { return name; }
    public String getContactNumber() { return contactNumber; }
    public int getId() {
        return id;
    }

    public void setName(String name) { this.name = name; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
}
