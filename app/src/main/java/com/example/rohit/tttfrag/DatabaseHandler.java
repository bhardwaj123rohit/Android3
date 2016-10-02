package com.example.rohit.tttfrag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "quotesManager";

    // Contacts table name
    private static final String TABLE_QUOTES = "quotes";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TEXT = "text";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_QUOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TEXT + " TEXT )";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUOTES);

        // Create tables again
        onCreate(db);
    }

    // resetting database
    public void onReset(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DELETE FROM " + TABLE_QUOTES);

        // Create tables again
        //onCreate(db);
    }

    // Adding new contact
    public void addQuote(FrndDetail det) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEXT, det.getText()); // Quote text

        // Inserting Row
        db.insert(TABLE_QUOTES, null, values);
        db.close(); // Closing database connection
    }

    public FrndDetail getQuote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

//        Cursor cursor = db.query(TABLE_QUOTES, new String[] { KEY_ID,
//                        KEY_ID, KEY_TEXT }, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
        String getquotequery = "SELECT * FROM " + TABLE_QUOTES + " WHERE "+KEY_ID+" = "+id;
        Cursor cursor = db.rawQuery(getquotequery,null);

        if (cursor != null)
            cursor.moveToFirst();

        FrndDetail quote = new FrndDetail(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
        // return contact
        return quote;
    }

    // Getting All Contacts
    public List<FrndDetail> getAllQuotes() {
        List<FrndDetail> fds = new ArrayList<FrndDetail>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUOTES;
        Log.v("GHANTA","Begins");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FrndDetail fd = new FrndDetail();
                fd.set_id(Integer.parseInt(cursor.getString(0)));
                fd.setText(cursor.getString(1));
                // Adding contact to list
                Log.v("GHANTA",cursor.getString(1));
                fds.add(fd);
            } while (cursor.moveToNext());
        }

        // return quotes list
        return fds;
    }

}
