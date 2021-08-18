package sg.edu.rp.c346.id20023837.wisheslist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "islands.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_WISHES = "item";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Song
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT,
        // singers TEXT, stars INTEGER, year INTEGER );
        String createSongTableSql = "CREATE TABLE " + TABLE_WISHES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_LOCATION + " TEXT, "
                + COLUMN_PRICE + " FLOAT, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createSongTableSql);
        Log.i("info", createSongTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISHES);
        onCreate(db);
    }

    public long insertItem(String name, String location, float price, int stars) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_SONG
        long result = db.insert(TABLE_WISHES, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<item> getAllItems() {
        ArrayList<item> itemList = new ArrayList<item>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_LOCATION + ","
                + COLUMN_PRICE + ","
                + COLUMN_STARS + " FROM " + TABLE_WISHES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String location = cursor.getString(2);
                float price = cursor.getInt(3);
                int stars = cursor.getInt(4);

                item newItem = new item(id, name, location, price, stars);
                itemList.add(newItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return itemList;
    }

    public ArrayList<item> getAllItemsByStars(int starsFilter) {
        ArrayList<item> itemList = new ArrayList<item>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_PRICE, COLUMN_LOCATION, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(starsFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_WISHES, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String location = cursor.getString(2);
                float price = cursor.getInt(3);
                int stars = cursor.getInt(4);

                item newItem = new item(id, name, location, price, stars);
                itemList.add(newItem);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return itemList;
    }



    public int updateItem(item data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_LOCATION, data.getLocation());
        values.put(COLUMN_PRICE, data.getPrice());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_WISHES, values, condition, args);
        db.close();
        return result;
    }


    public int deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_WISHES, condition, args);
        db.close();
        return result;
    }

    public ArrayList<String> getLocation() {
        ArrayList<String> codes = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_LOCATION};

        Cursor cursor;
        cursor = db.query(true, TABLE_WISHES, columns, null, null, null, null, null, null);
        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                codes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return codes;
    }

    public ArrayList<item> getAllItemsByLocation(String locationFilter) {
        ArrayList<item> itemList = new ArrayList<item>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_LOCATION, COLUMN_PRICE, COLUMN_STARS};
        String condition = COLUMN_LOCATION + "= ?";
        String[] args = {String.valueOf(locationFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_WISHES, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String location = cursor.getString(2);
                float price = cursor.getInt(3);
                int stars = cursor.getInt(4);

                item newItem = new item(id, name, location, price, stars);
                itemList.add(newItem);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return itemList;
    }
}
