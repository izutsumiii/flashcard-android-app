package com.example.flashcard.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "flashcard";
    private static final int DB_VERSION = 1;

    private static final String FOLDERS_TABLE_NAME = "folders";
    private static final String WORDS_TABLE_NAME = "words";
    private static final String ID_COL = "id";
    private static final String FOLDER_ID_COL = "folder_id";
    private static final String FOLDER_NAME_COL = "folder_name";
    private static final String WORD_COL = "word";
    private static final String DESCRIPTION_COL = "description";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createFoldersTable(db);
        createWordsTable(db);
    }

    private void createFoldersTable(SQLiteDatabase db) {
        String foldersQuery = "CREATE TABLE " + FOLDERS_TABLE_NAME + "(" +
                FOLDER_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FOLDER_NAME_COL + " TEXT)";
        db.execSQL(foldersQuery);
    }

    private void createWordsTable(SQLiteDatabase db) {
        String wordsQuery = "CREATE TABLE " + WORDS_TABLE_NAME + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                WORD_COL + " TEXT," +
                DESCRIPTION_COL + " TEXT," +
                FOLDER_ID_COL + " INTEGER," +
                "FOREIGN KEY(" + FOLDER_ID_COL + ") REFERENCES " + FOLDERS_TABLE_NAME + "(" + FOLDER_ID_COL + "))";
        db.execSQL(wordsQuery);
    }

    public void addFolder(String folderName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FOLDER_NAME_COL, folderName);
        db.insert(FOLDERS_TABLE_NAME, null, cv);
        db.close();
    }

    public void addWord(String word, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WORD_COL, word);
        cv.put(DESCRIPTION_COL, description);
        db.insert(WORDS_TABLE_NAME, null, cv);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WORDS_TABLE_NAME);
        onCreate(db);
    }
}
