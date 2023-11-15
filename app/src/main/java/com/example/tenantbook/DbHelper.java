package com.example.tenantbook;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 1;


    // table names and column names here
    public static final String TABLE_USER_DETAILS = "UserDetails"; //table name
    public static final String USERDETAILS_USERID = "UserId";
    public static final String USERDETAILS_USERNAME = "UserName"; // one of the column of UserDetails
    public static final String USERDETAILS_FULLNAME = "FullName";// one of the column of UserDetails
    public static final String USERDETAILS_EMAILADDRESS = "EmailAddress";// one of the column of UserDetails
    public static final String USERDETAILS_PASSWORD = "Passwords";// one of the column of UserDetails

    //string with sql Query to create table
    private static final String create_table_UserDetails = "CREATE TABLE " +
            TABLE_USER_DETAILS + " (" +
            USERDETAILS_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERDETAILS_FULLNAME + " TEXT, " +
            USERDETAILS_USERNAME + " TEXT, " +
            USERDETAILS_EMAILADDRESS + " TEXT, " +
            USERDETAILS_PASSWORD + " TEXT);";
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table
        db.execSQL(create_table_UserDetails);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }
}