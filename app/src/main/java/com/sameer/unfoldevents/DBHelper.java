package com.sameer.unfoldevents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sameer.unfoldevents.model.EventModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "unfoldEvents.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(email TEXT primary key, password TEXT, fullname TEXT, number TEXT )");
        db.execSQL("create table events(user TEXT, event_type TEXT, event_date TEXT , guests INTEGER , venue TEXT, food TEXT, budget INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists events");
    }

    public Boolean registerUser(String email, String password, String fullname, String number ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("email", email);
        values.put("password", password);
        values.put("fullname", fullname);
        values.put("number", number);

        long result = db.insert("users", null, values);
        if(result == -1) return false;
        else
            return true;
    }

    public Boolean checkUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email=?", new String[] {email});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean loginUser(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email=? and password=?", new String[] {email, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean addEvent(String email, String eventType, String eventDate, Integer guests, String venue, String food, Integer budget ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("user", email);
        values.put("event_type", eventType);
        values.put("event_date", eventDate);
        values.put("guests", guests);
        values.put("venue", venue);
        values.put("food", food);
        values.put("budget", budget);

        long result = db.insert("events", null, values);
        if(result == -1) return false;
        else
            return true;
    }

    public List<EventModel> readUserEvents(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from events where user=?", new String[] {email});

        List<EventModel> eventModel = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                EventModel bm = new EventModel();
                bm.setEventType(cursor.getString(1));
                bm.setEventDate(cursor.getString(2));
                bm.setGuests(cursor.getString(3));
                bm.setVenue(cursor.getString(4));
                bm.setFood(cursor.getString(5));
                bm.setBudget(cursor.getString(6));
                eventModel.add(bm);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return eventModel;
    }

}