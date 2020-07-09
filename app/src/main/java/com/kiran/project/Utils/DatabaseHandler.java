package com.kiran.project.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.kiran.project.pojo.EntriesPojo;
import com.kiran.project.pojo.UserPojo;
import com.kiran.project.pojo.EntriesPojo;
import com.kiran.project.pojo.UserPojo;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "DBPro";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_DATA = "all_data";
    /////USERS table
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";

    /////USERS table
    private static final String KEY_DID = "did";
    private static final String KEY_TITLE = "name";
    private static final String KEY_PWD = "pwd";
    private static final String KEY_DESC = "description";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ISSUE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "("
                + KEY_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_PASSWORD + " TEXT"
                + ")";
        db.execSQL(CREATE_ISSUE_TABLE);


        String CREATE_DATA_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_DATA + "("
                + KEY_DID + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " TEXT,"
                + KEY_PWD + " TEXT,"
                + KEY_DESC + " TEXT"
                + ")";
        db.execSQL(CREATE_DATA_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPPORTUNITIES);
        // Create tables again
        onCreate(db);
    }

    public Long insertUser(
            String name,
            String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_PASSWORD, password);
        Long i = db.insert(TABLE_USERS, null, contentValues);
        db.close();
        return i;
    }

    public Long insertEntry(
            String title,
            String pass,
            String desc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TITLE, title);
        contentValues.put(KEY_PWD, pass);
        contentValues.put(KEY_DESC, desc);
        Long i = db.insert(TABLE_DATA, null, contentValues);
        db.close();
        return i;
    }
    public boolean IsUserExist(String unm,String pwd) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor c = db.rawQuery("SELECT * FROM users WHERE name ='"+unm+"' AND password ='"+pwd+"'" , null);
            if (c.getCount() >= 1)
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor c = db.rawQuery("SELECT * FROM users", null);
            if (c.getCount() >= 1)
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<UserPojo> getUsers() {
        ArrayList<UserPojo> all_users = new ArrayList<UserPojo>();
        UserPojo usrs;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT  * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                usrs = new UserPojo();
                usrs.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                usrs.setDate(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                usrs.setResult(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                all_users.add(usrs);
            } while (cursor.moveToNext());
        }
        db.close();
        return all_users;
    }
    public ArrayList<EntriesPojo> getEntries() {
        ArrayList<EntriesPojo> all_entries = new ArrayList<EntriesPojo>();
        EntriesPojo entry;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT  * FROM " + TABLE_DATA;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                entry = new EntriesPojo();
                entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_DID)));
                entry.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                entry.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PWD)));
                entry.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESC)));
                all_entries.add(entry);
            } while (cursor.moveToNext());
        }
        db.close();
        return all_entries;
    }
}
