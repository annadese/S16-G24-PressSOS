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
        this.sosText = "This is an EMERGENCY! Your friend, " + this.name + ", needs help! Click the link to see his/her current location: ";
    }

    public String getName(){ return name; }
    public String getNum(){ return num; }
    public String getPin(){ return pin; }
    public String getSosText() { return sosText; }

    public void setName(String name){ this.name = name; }
    public void setNum(String num){ this.num = num; }
    public void setPin(String pin){ this.pin = pin; }
    public void setSosText(String sosText) { this.sosText = sosText; }
}