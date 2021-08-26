package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

public class Contact {

    private long id;
    private String name;
    private String contactNumber;

    public Contact (String name, String contactNumber, long id) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
    }


    public long getId() { return id; }
    public String getName() { return name; }
    public String getContactNumber() { return contactNumber; }

    public void setName(String name) { this.name = name; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
}
