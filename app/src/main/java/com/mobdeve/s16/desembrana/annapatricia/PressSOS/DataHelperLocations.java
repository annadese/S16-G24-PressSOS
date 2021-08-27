package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class DataHelperLocations {

    public ArrayList<Location> initializeLocations() throws ParseException {

        ArrayList<Location> locations = new ArrayList<>();
        String[] date = {"31/12/1998", "31/12/1998", "31/12/1998"};
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date[1]);
        Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(date[2]);
        Date date3=new SimpleDateFormat("dd/MM/yyyy").parse(date[3]);

        locations.add(new Location(
                "Pasig City",
                date1,
                01));

        locations.add(new Location(
                "Pasig City",
                date2,
                01));

        locations.add(new Location(
                "Pasig City",
                date3,
                01));
        Collections.shuffle(locations);

        return locations;
    }
}
