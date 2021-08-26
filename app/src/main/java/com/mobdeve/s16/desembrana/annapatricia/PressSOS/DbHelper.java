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

    // Called when a new version of the DB is present; hence, an "upgrade" to a newer version
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
                    c.getString(c.getColumnIndexOrThrow(DbReferences.COLUMN_NUMBER)),
                    c.getLong(c.getColumnIndexOrThrow(DbReferences._IDc))
            ));
        }
        c.close();
        database.close();

        return contacts;
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
                DbReferences.COLUMN_NAME + " ASC",
                null
        );
        ArrayList<Location> locations = new ArrayList<>();
        while(c.moveToNext()) {
            try {
                date = dateFormat.parse(c.getString(c.getColumnIndexOrThrow(DbReferences.COLUMN_TIME)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            locations.add(new Location(
                c.getString(c.getColumnIndexOrThrow(DbReferences.COLUMN_LOCATION)),
                date,
                c.getLong(c.getColumnIndexOrThrow(DbReferences._IDl))
            ));
        }
        c.close();
        database.close();

        return locations;
    }

    public synchronized void insertContact(Contact c) {
        SQLiteDatabase database = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DbReferences.COLUMN_NAME, c.getName());
        values.put(DbReferences.COLUMN_NUMBER, c.getContactNumber());

        // Insert the new row
        // Inserting returns the primary key value of the new row, but we can ignore that if we don’t need it
        database.insert(DbReferences.TABLEc_NAME, null, values);

        database.close();
    }

    public synchronized void insertLocation(Location l) {
        SQLiteDatabase database = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DbReferences.COLUMN_LOCATION, l.getLocationName());
        values.put(DbReferences.COLUMN_TIME, dateFormat.format(l.getTime()));

        // Insert the new row
        // Inserting returns the primary key value of the new row, but we can ignore that if we don’t need it
        database.insert(DbReferences.TABLEc_NAME, null, values);

        database.close();
    }

    private final class DbReferences {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "my_database.db";

        private static final String
                TABLEc_NAME = "contacts",
                TABLEl_NAME = "locations",
                _IDc = "id",
                _IDl = "id",
                COLUMN_NAME = "name",
                COLUMN_NUMBER = "number",
                COLUMN_LOCATION = "location",
                COLUMN_TIME = "time";

        private static final String CREATE_CONTACT_TABLE_STATEMENT =
                "CREATE TABLE IF NOT EXISTS " + TABLEc_NAME + " (" +
                        _IDc + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_NUMBER + " TEXT)";

        private static final String CREATE_LOCATION_TABLE_STATEMENT =
                "CREATE TABLE IF NOT EXISTS " + TABLEl_NAME + " (" +
                        _IDl + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_LOCATION + " TEXT)";

        private static final String DROP_CONTACT_TABLE_STATEMENT =
                "DROP TABLE IF EXISTS " + TABLEc_NAME;

        private static final String DROP_LOCATION_TABLE_STATEMENT =
                "DROP TABLE IF EXISTS " + TABLEl_NAME;
    }
}

