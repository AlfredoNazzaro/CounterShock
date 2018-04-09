package com.giorgione.nazzaro.countershock.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.giorgione.nazzaro.countershock.share.User;
import com.giorgione.nazzaro.countershock.database.DatabaseSchema.userEntry;
import com.giorgione.nazzaro.countershock.database.DatabaseSchema.roadEntry;
import com.giorgione.nazzaro.countershock.database.DatabaseSchema.feedbackEntry;
import com.giorgione.nazzaro.countershock.share.Percorso;
import com.giorgione.nazzaro.countershock.share.Feedback;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by 91juv on 16/09/2016.
 */
@SuppressWarnings("ALL")
public class DBManager {

    DbHelper dbhelper;
    private final Context context;
    public  SQLiteDatabase db;

    static final String DATABASE_NAME = "counter.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    public DBManager(Context ctx) {
        context=ctx;
        dbhelper=new DbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public  DBManager open() throws SQLException
    {
        db = dbhelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertUser(String email,String name,String surname,String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("email", email);
        newValues.put("name", name);
        newValues.put("surname", surname);
        newValues.put("password", password);

        // Insert the row into your table
        db.insert("utenti", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public void insertRoad(String partenza,String destinazione,int num_fossi,double km)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("partenza", partenza);
        newValues.put("destinazione", destinazione);
        newValues.put("numero_fossi", num_fossi);
        newValues.put("km", km);

        // Insert the row into your table
        db.insert(roadEntry.TABLE_NAME, null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public void insertFeedback(String email,int road,String title,String body,float vote)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("email",email);
        newValues.put("road", road);
        newValues.put("title", title);
        newValues.put("body", body);
        newValues.put("vote", vote);

        // Insert the row into your table
        db.insert(feedbackEntry.TABLE_NAME, null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public boolean getUserByEmail(String email){

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String selectQuery= "SELECT * FROM " +userEntry.TABLE_NAME+ " WHERE "+userEntry.KEY_email+"= '"+email+"'";

        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.getCount()<1){
            cursor.close();
            return false;
        }
        else
            cursor.moveToFirst();
            cursor.close();
            return true;
    }

    public User login(String email, String password){

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String selectQuery= "SELECT * FROM " +userEntry.TABLE_NAME+ " WHERE "+userEntry.KEY_email+"= '"+email+"'" +
                " and "+userEntry.KEY_password+"= '"+password+"'";

        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.getCount()<1){
            cursor.close();
            return null;
        }
        else {
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex(userEntry.KEY_nome));
            String surname = cursor.getString(cursor.getColumnIndex(userEntry.KEY_cognome));
            User user = new User(email, name, surname, password);
            cursor.close();
            return user;
        }
    }
   public ArrayList<Feedback> getFeedbackByRoad(int road){

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String selectQuery= "SELECT * FROM " +feedbackEntry.TABLE_NAME+ " WHERE "+feedbackEntry.KEY_road+"= '"+road+"'";

        ArrayList<Feedback> feedList = new ArrayList<Feedback>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                int id=cursor.getInt(cursor.getColumnIndex(feedbackEntry.KEY_ID));
                String email=cursor.getString(cursor.getColumnIndex(feedbackEntry.KEY_email));
                int r=cursor.getInt(cursor.getColumnIndex(feedbackEntry.KEY_road));
                String title=cursor.getString(cursor.getColumnIndex(feedbackEntry.KEY_title));
                String body=cursor.getString(cursor.getColumnIndex(feedbackEntry.KEY_body));
                float voto=cursor.getFloat(cursor.getColumnIndex(feedbackEntry.KEY_vote));
                Feedback feedback=new Feedback(id,email,r,title,body,voto);
                feedList.add(feedback);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return feedList;
    }

    public ArrayList<User> getAllUser(){
        //Open connection to read only
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + userEntry.TABLE_NAME;

        //Student student = new Student();
        ArrayList<User> userList = new ArrayList<User>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                String email=cursor.getString(cursor.getColumnIndex(userEntry.KEY_email));
                String name=cursor.getString(cursor.getColumnIndex(userEntry.KEY_nome));
                String surname=cursor.getString(cursor.getColumnIndex(userEntry.KEY_cognome));
                String password=cursor.getString(cursor.getColumnIndex(userEntry.KEY_password));
                User user=new User(email,name,surname,password);
                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }

    public ArrayList<Percorso> getAllRoad(){
        //Open connection to read only
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + roadEntry.TABLE_NAME;

        //Student student = new Student();
        ArrayList<Percorso> roadList = new ArrayList<Percorso>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                int id=cursor.getInt(cursor.getColumnIndex(roadEntry.KEY_ID));
                String partenza=cursor.getString(cursor.getColumnIndex(roadEntry.KEY_partenza));
                String destinazione=cursor.getString(cursor.getColumnIndex(roadEntry.KEY_destinazione));
                int num_fossi=cursor.getInt(cursor.getColumnIndex(roadEntry.KEY_fossi));
                double km=cursor.getDouble(cursor.getColumnIndex(roadEntry.KEY_km));
                Percorso road=new Percorso(id,partenza,destinazione,num_fossi,km);
                roadList.add(road);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return roadList;
    }
}

