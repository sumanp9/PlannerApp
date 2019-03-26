package com.pleasedo.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.pleasedo.dbClass.Event;
import com.pleasedo.dbClass.Note;
import com.pleasedo.planner.welcome;

import java.util.ArrayList;

public class userActivityDB extends SQLiteOpenHelper {
    private static final  int DATABASE_VERSION =2;
    private static String DATABASE_NAME = "";


    //Event Table
    private static final String eventTable = "Event";
    private static final String col_eventId = "ID";
    private static final String col_eventTitle = "Event_Title";
    private static final String col_eventStartDate = "Event_Start_Date";
    private static final String col_eventEndDate = "Event_End_Date";
    private static final String col_eventDetails = "Event_Details";
    private static final String col_eventTime = "Event_Time";

    //Note Table
    private static final String noteTable = "Note";
    private static final String col_noteId = "ID";
    private static final String col_noteTitle = "Note_Title";
    private static final String col_noteDate = "Note_Date";
    private static final String col_noteDetails = "Note_Details";

    public userActivityDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,getdbName(name), factory, DATABASE_VERSION);

// getdbName(name)
    }

    private static String getdbName(String name) {
        DATABASE_NAME = name+".db";
        //eventTable+=name+"_Event";
        return  DATABASE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String eventQuery = "CREATE TABLE "+eventTable+"("+col_eventId+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+col_eventTitle+ " TEXT ,"+
            col_eventDetails+ " TEXT ,"+col_eventStartDate+ " DATE , "+col_eventEndDate+ " DATE ,"+ col_eventTime+ " TIME "+ ");";

        db.execSQL(eventQuery);

        String noteQuery = "CREATE TABLE "+noteTable+"("+col_noteId+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+col_noteTitle+ " TEXT ,"+
            col_noteDetails+ " TEXT ,"+col_noteDate+ " DATE  "+ ");";

        db.execSQL(noteQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ eventTable);
        onCreate(db);

    }

    public void addEvent(Event event){
        ContentValues values = new ContentValues();
        values.put(col_eventTitle, event.getEventTitle());
        values.put(col_eventDetails,event.getEventDetails());
        values.put(col_eventStartDate,event.getEventStartDate());
        values.put(col_eventEndDate,event.getEventEndDate());
        values.put(col_eventTime, event.getEventTime());

        SQLiteDatabase db =  getWritableDatabase();
        db.insert(eventTable, null, values);
        db.close();
    }

    public void addNote(Note note){
        ContentValues values = new ContentValues();
        values.put(col_noteTitle, note.getNoteTitle());
        values.put(col_noteDetails, note.getNoteDescription());
        values.put(col_noteDate,note.getNoteDate());


        SQLiteDatabase db =  getWritableDatabase();
        db.insert(noteTable, null, values);
        db.close();
    }

    public ArrayList<Event> getName(){
        //return "";

        ArrayList<Event> arrayEvent = new ArrayList<>();

        String dbString= "";
        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM "+ eventTable + " WHERE 1";



        //Cursor point to a location in your results
        Cursor c =  db.rawQuery(query,null);

        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()){
            /*

            if (c.getString(c.getColumnIndex("Event_Title"))!=null){

                dbString += c.getString(c.getColumnIndex("Event_Title"));
            }*/
            Event e = new Event();
            e.setEventTitle(c.getString(c.getColumnIndex("Event_Title")));
            e.setEventDetails(c.getString(c.getColumnIndex("Event_Details")));
            e.setEventTime(c.getString(c.getColumnIndex("Event_Time")));
            e.setEventEndDate(c.getString(c.getColumnIndex("Event_End_Date")));
            e.setEventStartDate(c.getString(c.getColumnIndex("Event_Start_Date")));
            arrayEvent.add(e);

            c.moveToNext();
        }


        db.close();


        return arrayEvent;
    }

    public void examp(){
        //System.out.print(DATABASE_NAME);
        }
}
