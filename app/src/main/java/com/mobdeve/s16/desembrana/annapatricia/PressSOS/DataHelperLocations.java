package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class DataHelperLocations {

    public ArrayList<Location> initializeLocations() throws ParseException {

        ArrayList<Location> locations = new ArrayList<>();
        String[] date = {"15/09/1998", "31/12/2021", "05/11/1999"};
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date[0]);
        Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(date[1]);
        Date date3=new SimpleDateFormat("dd/MM/yyyy").parse(date[2]);

        locations.add(new Location(
                "Pasig City",
                date1));

        locations.add(new Location(
                "Quezon City",
                date2));

        locations.add(new Location(
                "Tagaytay City",
                date3));
        Collections.shuffle(locations);

        return locations;
    }
}
