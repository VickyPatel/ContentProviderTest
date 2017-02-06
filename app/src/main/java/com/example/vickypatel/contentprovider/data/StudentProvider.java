package com.example.vickypatel.contentprovider.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.vickypatel.contentprovider.data.StudentContract.StudentEntry;

/**
 * Created by Vicky Patel on 2/6/2017.
 */

public class StudentProvider extends ContentProvider{

    private StudentDBHelper mOpenHelper;

    //Uri constants
    static final int STUDENT = 100;
    static final int STUDENT_WITH_NAME = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        mOpenHelper = new StudentDBHelper(getContext());
        return true;
    }

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = StudentContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, StudentContract.PATH_STUDENT, STUDENT);
        matcher.addURI(authority, StudentContract.PATH_STUDENT + "/*", STUDENT_WITH_NAME);
        return matcher;
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            // Here's the switch statement that, given a URI, will determine what kind of request it is,
            // and query the database accordingly.
            Cursor retCursor;
            switch (sUriMatcher.match(uri)) {
                // "weather"
                case STUDENT: {
                    retCursor = mOpenHelper.getReadableDatabase().query(
                            StudentEntry.TABLE_NAME,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder
                    );
                    break;
                }

                default:
                    throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
            retCursor.setNotificationUri(getContext().getContentResolver(), uri);
            return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);

        switch (match) {
            // Student: Uncomment and fill out these two cases
            case STUDENT:
                return StudentEntry.CONTENT_TYPE;
            case STUDENT_WITH_NAME:
                return StudentEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case STUDENT: {
                long _id = db.insert(StudentEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = StudentEntry.buildStudentUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
