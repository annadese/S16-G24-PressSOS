package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.lifecycle.LiveData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DbHelper extends SQLiteOpenHelper {
    public static DbHelper instance = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date;

    public DbHelper(Context context) {
        super(context, DbReferences.DATABASE_NAME, null, DbReferences.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DbReferences.CREATE_CONTACT_TABLE_STATEMENT);
        sqLiteDatabase.execSQL(DbReferences.CREATE_LOCATION_TABLE_STATEMENT);
    }

    // Called when a new version of the DB is present
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DbReferences.DROP_CONTACT_TABLE_STATEMENT);
        sqLiteDatabase.execSQL(DbReferences.DROP_LOCATION_TABLE_STATEMENT);
        onCreate(sqLiteDatabase);
    }

    public static synchronized DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context.getApplicationContext());
        }

        return instance;
    }

    public ArrayList<Contact> getAllContactsDefault() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.query(
                DbReferences.TABLEc_NAME,
                null,
                null,
                null,
                null,
                null,
                DbReferences.COLUMN_NAME + " ASC",
                null
        );
        ArrayList<Contact> contacts = new ArrayList<>();
        while(c.moveToNext()) {
            contacts.add(new Contact(
                    c.getString(c.getColumnIndexOrThrow(DbReferences.COLUMN_NAME)),
                    c.getString(c.getColumnIndexOrThrow(DbReferences.COLUMN_NUMBER))
            ));
        }
        c.close();
        database.close();

        return contacts;
    }

    public Contact getOneContact(String query) {
        SQLiteDatabase database = this.getReadableDatabase();
        Contact contact;

        Cursor TuplePointer = database.rawQuery(
                "SELECT * FROM " + DbReferences.TABLEc_NAME +
                        " WHERE " + DbReferences.COLUMN_NUMBER +
                        " = '" + query + "'",
                null);

        TuplePointer.moveToFirst();
        contact = new Contact(
                TuplePointer.getString(TuplePointer.getColumnIndexOrThrow((DbReferences.COLUMN_NAME))),
                TuplePointer.getString(TuplePointer.getColumnIndexOrThrow((DbReferences.COLUMN_NUMBER))),
                TuplePointer.getLong(TuplePointer.getColumnIndexOrThrow((DbReferences._IDc)))
        );

        return contact;
    }

    public ArrayList<Location> getAllLocationsDefault() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.query(
                DbReferences.TABLEl_NAME,
                null,
                null,
                null,
                null,
                null,
                DbReferences.COLUMN_DATE + " ASC",
                null
        );
        ArrayList<Location> locations = new ArrayList<>();
        while(c.moveToNext()) {
            try {
                date = dateFormat.parse(c.getString(c.getColumnIndexOrThrow(DbReferences.COLUMN_DATE)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            locations.add(new Location(
                c.getString(c.getColumnIndexOrThrow(DbReferences.COLUMN_LAT)), c.getString(c.getColumnIndexOrThrow(DbReferences.COLUMN_LONG)),
                date
            ));
        }
        c.close();
        database.close();

        return locations;
    }

    public synchronized boolean insertContact(Contact c) {
        SQLiteDatabase database = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DbReferences.COLUMN_NAME, c.getName());
        values.put(DbReferences.COLUMN_NUMBER, c.getContactNumber());

        // Insert the new row
        // Inserting returns the primary key value of the new row, but we can ignore that if we don’t need it
        long result = database.insert(DbReferences.TABLEc_NAME, null, values);

        //database.close();

        if(result == -1)
            return false;
        else
            return true;

    }

    public synchronized void insertLocation(Location l) {
        SQLiteDatabase database = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DbReferences.COLUMN_LAT, l.getLatitude());
        values.put(DbReferences.COLUMN_LONG, l.getLongitude());
        values.put(DbReferences.COLUMN_DATE, dateFormat.format(l.getDate()));

        // Insert the new row
        // Inserting returns the primary key value of the new row, but we can ignore that if we don’t need it
        database.insert(DbReferences.TABLEc_NAME, null, values);

        database.close();
    }

    // Performs an UPDATE operation by comparing the old contact with the new contact. This method
    // tries to reduce the length of the update statement by only including attributes that have
    // been changed. If no changed are present, the update statement is simply not called.
    public void updateContact(Contact cOld, Contact cNew) {
        boolean withChanges = false;
        ContentValues values = new ContentValues();

        if(!cNew.getName().equals(cOld.getName())) {
            values.put(DbReferences.COLUMN_NAME, cNew.getName());
            withChanges = true;
        }

        if(!cNew.getContactNumber().equals(cOld.getContactNumber())) {
            values.put(DbReferences.COLUMN_NUMBER, cNew.getContactNumber());
            withChanges = true;
        }

        if(withChanges) {
            SQLiteDatabase database = this.getWritableDatabase();
            database.update(
                    DbReferences.TABLEc_NAME,
                    values,
                    DbReferences._IDc + " = ?",
                    new String[]{String.valueOf(cOld.getId())});

        }
    }

    // The delete contact method that takes in a contact object and uses its ID to find and delete
    // the entry.
    public void deleteContact(Context context, long id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String strSQL = "DELETE FROM contacts WHERE idc = " + id;
        database.execSQL(strSQL);
    }

    public void deleteAllLocations(Contact c) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from "+ DbReferences.TABLEl_NAME);
        database.close();
    }

    private final class DbReferences {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "my_database.db";

        private static final String
                TABLEc_NAME = "contacts",
                TABLEl_NAME = "locations",
                _IDc = "idc",
                _IDl = "idl",
                COLUMN_NAME = "name",
                COLUMN_NUMBER = "number",
                COLUMN_LAT = "latitude",
                COLUMN_LONG = "longitude",
                COLUMN_DATE = "date";

        private static final String CREATE_CONTACT_TABLE_STATEMENT =
                "CREATE TABLE IF NOT EXISTS " + TABLEc_NAME + " (" +
                        _IDc + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_NUMBER + " TEXT)";

        private static final String CREATE_LOCATION_TABLE_STATEMENT =
                "CREATE TABLE IF NOT EXISTS " + TABLEl_NAME + " (" +
                        _IDl + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_LAT + " TEXT, " +
                        COLUMN_LONG + " TEXT, " +
                        COLUMN_DATE + " TEXT)";

        private static final String DROP_CONTACT_TABLE_STATEMENT =
                "DROP TABLE IF EXISTS " + TABLEc_NAME;

        private static final String DROP_LOCATION_TABLE_STATEMENT =
                "DROP TABLE IF EXISTS " + TABLEl_NAME;
    }
}

