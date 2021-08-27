package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import java.util.ArrayList;
import java.util.Collections;

public class DataHelperContacts {

    public ArrayList<Contact> initializeContacts(){
        ArrayList<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact(
                "Sabrina Mykel C. Dela Cruz",
                "09992222012",
                00001));

        contacts.add(new Contact(
                "Anna Patricia Desembrana",
                "09991234567",
                00001));

        contacts.add(new Contact(
                "Clarissa Mandadero",
                "09123456789",
                00001));

        Collections.shuffle(contacts);

        return contacts;
    }
}
