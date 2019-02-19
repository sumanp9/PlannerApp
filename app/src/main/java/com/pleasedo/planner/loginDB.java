package com.pleasedo.planner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.pleasedo.dbClass.Login;

import java.util.ArrayList;

public class loginDB extends SQLiteOpenHelper {

    private static final  int DATABASE_VERSION =2;
    private static final String DATABASE_NAME = "userLogin.db"; //file name saving on device



    public static final String TABLE_LOGIN = "Login";
    private static final String col_id = "ID";
    private static final String  col_username= "Username";
    private static final String col_fName = "First_Name";
    private static final String col_lName = "Last_Name";
    private static final String col_email = "Email";
    private static final String col_password = "Password";

    public loginDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_LOGIN + "(" + col_id + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+ col_username + " TEXT ,"+
                col_fName + " TEXT ,"+ col_lName + " TEXT ," + col_email+ " TEXT ,"+ col_password +" TEXT "+ ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_LOGIN);
        onCreate(db);

    }

    public void addProduct(Login login){
        //makes inserting rows into table easy
        ContentValues values = new ContentValues();
        values.put(col_username, login.getCol_username());
        values.put(col_fName, login.getCol_fName());
        values.put(col_lName, login.getCol_lName());
        values.put(col_email, login.getCol_email());
        values.put(col_password, login.getCol_password());
        SQLiteDatabase db =  getWritableDatabase();
        db.insert(TABLE_LOGIN, null,values);
        db.close();
    }

    public ArrayList<String> userList(){
        ArrayList<String> userArray = new ArrayList<String>();
        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_LOGIN + " WHERE 1";
        Cursor c =  db.rawQuery(query,null);

        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("Username"))!=null){

                userArray.add(c.getString(c.getColumnIndex("Username")));

            }
            c.moveToNext();
        }


        db.close();

        return userArray;
    }

    public String databaseToString(){
        String dbString= "";
        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_LOGIN + " WHERE 1";

        //Cursor point to a location in your results
        Cursor c =  db.rawQuery(query,null);

        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("Username"))!=null){

                dbString += c.getString(c.getColumnIndex("Username"));
                dbString+="\n";

            }
            c.moveToNext();
        }


        db.close();


        return dbString;

    }


}

