package com.example.flashcard.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "flashcard";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "words";
    private static final String ID_COL = "id";
    private static final String WORD_COL = "word";
    private static final String DESCRIPTION_COL = "description";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                WORD_COL + " TEXT," +
                DESCRIPTION_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addWord(String word, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WORD_COL, word);
        cv.put(DESCRIPTION_COL, description);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
