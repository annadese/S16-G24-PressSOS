package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

public class Account {
    private String name;
    private String num;
    private int pin;

    public Account(String name, String num, int pin){
        this.name = name;
        this.num = num;
        this.pin = pin;
    }

    public String getName(){ return name; }
    public String getNum(){ return num; }
    public int getPin(){ return pin; }

    public void setName(String name){ this.name = name; }
    public void setNum(String num){ this.num = num; }
    public void setPin(int pin){ this.pin = pin; }
}
