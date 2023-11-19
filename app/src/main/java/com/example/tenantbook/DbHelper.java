package com.example.tenantbook;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 1;


    // table names and column names here for userdetails
    public static final String TABLE_USER_DETAILS = "UserDetails"; //table name
    public static final String USERDETAILS_USERID = "UserId";
    public static final String USERDETAILS_USERNAME = "UserName"; // one of the column of UserDetails
    public static final String USERDETAILS_FULLNAME = "FullName";// one of the column of UserDetails
    public static final String USERDETAILS_EMAILADDRESS = "EmailAddress";// one of the column of UserDetails
    public static final String USERDETAILS_PASSWORD = "Passwords";// one of the column of UserDetails

    //tables and names for Property
    public static final String TABLE_PROPERTY = "Properties";
    public static final String PROPERTY_ID = "PropertyId";
    public static final String PROPERTY_NAME = "PropertyName";

    //string with sql Query to create table
    private static final String create_table_UserDetails = "CREATE TABLE " +
            TABLE_USER_DETAILS + " (" +
            USERDETAILS_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERDETAILS_FULLNAME + " TEXT, " +
            USERDETAILS_USERNAME + " TEXT, " +
            USERDETAILS_EMAILADDRESS + " TEXT, " +
            USERDETAILS_PASSWORD + " TEXT);";

    private static final String create_table_properties = "CREATE TABLE " +
            TABLE_PROPERTY + " (" +
            PROPERTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PROPERTY_NAME + " TEXT);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table
        db.execSQL(create_table_UserDetails);
        db.execSQL(create_table_properties);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }
    // Method to load buttons from the Properties table
    public Cursor loadButtons() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {PROPERTY_ID, PROPERTY_NAME};
        return db.query(TABLE_PROPERTY, projection, null, null, null, null, null);
    }

    public int deleteButtonFromDatabase(String buttonName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = PROPERTY_NAME + " = ?";
        String[] selectionArgs = {buttonName};
        return db.delete(TABLE_PROPERTY, selection, selectionArgs);
    }

    // Method to save a button to the Properties table
    public long saveButtonToDatabase(String buttonName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROPERTY_NAME, buttonName);
        return db.insert(TABLE_PROPERTY, null, values);
    }
}
