package com.giorgione.nazzaro.countershock.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.giorgione.nazzaro.countershock.database.DatabaseSchema.userEntry;
import com.giorgione.nazzaro.countershock.database.DatabaseSchema.roadEntry;
import com.giorgione.nazzaro.countershock.database.DatabaseSchema.feedbackEntry;


public class DbHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "appData.db";
//    private static final String TEXT_TYPE = " TEXT";
//    private static final String REAL_TYPE = " REAL";
//    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String COMMA_SEP = ",";

   /* static final String  = "create table "+userEntry.TABLE_NAME+
            "( " +userEntry.KEY_email+" text primary key,"+
            userEntry.KEY_nome+ "  text,"+
            userEntry.KEY_cognome+ "text," +
            userEntry.KEY_password+ "text );";
*/
    static final String CREATE_USER = "create table "+userEntry.TABLE_NAME+
            "( " +userEntry.KEY_email+" text primary key,"+ userEntry.KEY_nome+"  text,"
                +userEntry.KEY_cognome+" text,"+userEntry.KEY_password+"  text); ";

    static final String CREATE_ROAD = "create table "+roadEntry.TABLE_NAME+
            "( " +roadEntry.KEY_ID+" integer primary key autoincrement,"+ roadEntry.KEY_partenza+"  text,"
            +roadEntry.KEY_destinazione+" text,"+roadEntry.KEY_fossi+" integer,"+roadEntry.KEY_km+" double);";

    static final String CREATE_FEEDBACK = "create table "
            +feedbackEntry.TABLE_NAME+ "( "
            +feedbackEntry.KEY_ID+" integer primary key autoincrement,"
            +feedbackEntry.KEY_email+"  text,"
            +feedbackEntry.KEY_road+" integer,"
            +feedbackEntry.KEY_title+" text,"
            +feedbackEntry.KEY_body+"  text,"
            +feedbackEntry.KEY_vote+" real,"
            +"FOREIGN KEY ("+feedbackEntry.KEY_email+") REFERENCES "+userEntry.TABLE_NAME+" ("+userEntry.KEY_email+"),"
            +"FOREIGN KEY ("+feedbackEntry.KEY_road+") REFERENCES "+roadEntry.TABLE_NAME+" ("+roadEntry.KEY_ID+")); ";

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + userEntry.TABLE_NAME;
    private static final String SQL_DELETE_ROAD =
            "DROP TABLE IF EXISTS " + roadEntry.TABLE_NAME;
    private static final String SQL_DELETE_FEEDBACK =
            "DROP TABLE IF EXISTS " + roadEntry.TABLE_NAME;

    public DbHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ROAD);
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_FEEDBACK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_USER);
        db.execSQL(SQL_DELETE_ROAD);
       // db.execSQL(SQL_DELETE_FEEDBACK);

        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}