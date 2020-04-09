package com.example.practic491;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBUsers {

    private static final String DATABASE_NAME = "simple.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_NAME = 1;
    private static final int NUM_COLUMN_SURNAME = 2;
    private static final int NUM_COLUMN_EMAIL = 3;
    private static final int NUM_COLUMN_PASSWORD = 4;

    private SQLiteDatabase mDataBase;

    public DBUsers(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(String name,String surname,String email,String password) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SURNAME, surname);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD,password);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(User user) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME, user.getName());
        cv.put(COLUMN_SURNAME, user.getSurname());
        cv.put(COLUMN_EMAIL, user.getEmail());
        cv.put(COLUMN_PASSWORD,user.getPassword());
        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?",new String[] {"" + user.getId()});
    }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] {"" + id});
    }

    public User select(long id) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            return null;
        }
        String name = mCursor.getString(NUM_COLUMN_NAME);
        String surname = mCursor.getString(NUM_COLUMN_SURNAME);
        String email = mCursor.getString(NUM_COLUMN_EMAIL);
        String password=mCursor.getString(NUM_COLUMN_PASSWORD);
        return new User(id, name, surname, email, password);
    }

    public ArrayList<User> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<User> arr = new ArrayList<User>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String name = mCursor.getString(NUM_COLUMN_NAME);
                String surname = mCursor.getString(NUM_COLUMN_SURNAME);
                String email = mCursor.getString(NUM_COLUMN_EMAIL);
                String password=mCursor.getString(NUM_COLUMN_PASSWORD);
                arr.add(new User(id, name, surname, email, password));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_SURNAME + " TEXT, " +
                    COLUMN_EMAIL + " TEXT,"+
                    COLUMN_PASSWORD +" TEXT);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
