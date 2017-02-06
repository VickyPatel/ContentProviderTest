package com.example.vickypatel.contentprovider.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vickypatel.contentprovider.data.StudentContract.StudentEntry;

/**
 * Created by Vicky Patel on 2/6/2017.
 */

public class StudentDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "student.db";

    public StudentDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_STUDENT_TABLE = "CREATE TABLE " + StudentEntry.TABLE_NAME + " (" +
                StudentEntry._ID + " INTEGER AUTO INCREMENT PRIMARY KEY," +
                StudentEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                StudentEntry.COLUMN_SURNAME + " TEXT NOT NULL, " +
                StudentEntry.COLUMN_ADDRESS + " TEXT NOT NULL, " +
                StudentEntry.COLUMN_ZIP_CODE + " TEXT NOT NULL " +
                " );";

        db.execSQL(SQL_CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME);
        onCreate(db);
    }
}
