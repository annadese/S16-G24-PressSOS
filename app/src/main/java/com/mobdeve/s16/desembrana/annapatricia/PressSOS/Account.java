package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

public class Account {
    private String name;
    private String num;
    private String pin;
    private String sosText;

    public Account(String name, String num, String pin){
        this.name = name;
        this.num = num;
        this.pin = pin;

        // default SOS message
        this.sosText = this.name + " is in danger! This is their location: ";
    }

    public String getName(){ return name; }
    public String getNum(){ return num; }
    public String getPin(){ return pin; }
    public String getSosText() { return sosText; }

    public void setName(String name){ this.name = name; }
}