package com.example.vickypatel.contentprovider.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Vicky Patel on 2/6/2017.
 */

public  class StudentContract {

    //content://com.example.vickypatel.contentprovider/
    public static final String CONTENT_AUTHORITY = "com.example.vickypatel.contentprovider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_STUDENT = "student";

    public static class StudentEntry implements BaseColumns{

        //content://com.example.vickypatel.contentprovider/student
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_STUDENT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUDENT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUDENT;

        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_ZIP_CODE = "zip_code";

        public static Uri buildStudentUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
