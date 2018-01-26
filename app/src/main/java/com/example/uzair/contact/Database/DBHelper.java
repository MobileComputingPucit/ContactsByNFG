package com.example.uzair.contact.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.uzair.contact.Models.ContactInfo;

import java.util.ArrayList;

/**
 * @package com.example.uzair.contact
 * @project Database
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "contacts";

    //COLUMN NAMES
    public static final String ID = "ID";
    public static final String NAME = "name";
    public static final String PHONE = "phoneno";
    public static final String EMAIL = "email";

    //COLUMN TYPES
    public static final String TYPE_TEXT = " TEXT ";
    public static final String TYPE_INT = " INT ";
    public static final String SEPERATOR = ", ";
    private static final String DATABASE_NAME = "MainDB.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_query = "Create table " + TABLE_NAME + " ("
                + ID + TYPE_INT + " AUTO_INCREMENT PRIMARY KEY " + SEPERATOR
                + NAME + TYPE_TEXT + SEPERATOR
                + PHONE + TYPE_TEXT + SEPERATOR
                + EMAIL + TYPE_TEXT + ");";
        db.execSQL(create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_query = "drop database if exists " + DATABASE_NAME;
        db.execSQL(drop_query);
        onCreate(db);
    }

    /**
     * This function inserts data into database
     *
     * @param name    takes a string user name
     * @param phoneno   takes a string type email
     * @param email takes a string type address
     * @return true when data inserted false when failed
     */
    public long insert(String name, String phoneno, String email) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(PHONE, phoneno);
        cv.put(EMAIL, email);
        long i = db.insert(TABLE_NAME, null, cv);
        Log.d("Database_helper", String.valueOf(i));
        //be sure to close database after work is done
        db.close();
        return i;
    }

    public boolean delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=" + id, null) > 0;
    }

    public Cursor read(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{ID, NAME}, null, null, null, null, null);//"SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id);
        return c;
    }

    public ArrayList getAll() {
        ArrayList<ContactInfo> ci = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            ci.add(new ContactInfo(cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(NAME)),
                    cursor.getString(cursor.getColumnIndex(PHONE)),
                    cursor.getString(cursor.getColumnIndex(EMAIL))));
        }
        return ci;
    }

    public void update(int id, String name, String phoneno, String email)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(PHONE, phoneno);
        cv.put(EMAIL, email);
        db.update(TABLE_NAME,cv,ID + "=" + id,null);
    }


}