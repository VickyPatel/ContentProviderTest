package com.example.vickypatel.contentprovider;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vickypatel.contentprovider.data.StudentContract;
import com.example.vickypatel.contentprovider.data.StudentContract.StudentEntry;
import com.example.vickypatel.contentprovider.data.StudentDBHelper;
import com.example.vickypatel.contentprovider.data.StudentUtilities;
import com.example.vickypatel.contentprovider.pojo.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int GET_DATA_LOADER_ID = 1;
    private static final int GET_DATA_BY_NAME_LOADER_ID = 2;
    private static final int GET_DATA_BY_ZIP_CODE_LOADER_ID = 3;

    private ArrayList<Student> studentArrayList = new ArrayList<>();
    private ListViewAdapter adapter;
    private ListViewCursorAdapter cursorAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setDataButton = (Button) findViewById(R.id.insert_data_button);
        Button getDataButton = (Button) findViewById(R.id.get_data_button);
        Button getDataByNameButton = (Button) findViewById(R.id.get_data_by_name_button);
        Button getDataByZIPCodeButton = (Button) findViewById(R.id.get_data_by_zip_code_button);
        Button createNotificationButton = (Button) findViewById(R.id.create_notification);

        setDataButton.setOnClickListener(this);
        getDataButton.setOnClickListener(this);
        getDataByNameButton.setOnClickListener(this);
        getDataByZIPCodeButton.setOnClickListener(this);
        createNotificationButton.setOnClickListener(this);

        Cursor cursor = getContentResolver().query(
                StudentEntry.CONTENT_URI,
                null, null, null, null);

        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ListViewAdapter(this, studentArrayList);
        cursorAdapter = new ListViewCursorAdapter(this, cursor, false);
//        listView.setAdapter(adapter);
        listView.setAdapter(cursorAdapter);

//        getDataButton.performClick();
//        getSupportLoaderManager().initLoader(
//                GET_DATA_LOADER_ID, null, this);

    }


    @Override
    public void onClick(View v) {
        Cursor cur;
        ArrayList<Student> studentArrayList;

        switch (v.getId()) {
            case R.id.insert_data_button:
                studentArrayList = StudentUtilities.getStudentList();
                for (Student student :
                        studentArrayList) {
                    Uri uri = getApplicationContext().getContentResolver().insert(
                            StudentEntry.CONTENT_URI,
                            StudentUtilities.createStudentValues(student));
                    long studentId = ContentUris.parseId(uri);
                    Toast.makeText(getApplicationContext(), "Student with id " + studentId + " inserted", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.get_data_button:
                getSupportLoaderManager().initLoader(
                        GET_DATA_LOADER_ID, null, this);
                break;
            case R.id.get_data_by_name_button:
                getSupportLoaderManager().initLoader(
                        GET_DATA_BY_NAME_LOADER_ID, null, this);
                break;
            case R.id.get_data_by_zip_code_button:
                getSupportLoaderManager().initLoader(
                        GET_DATA_BY_ZIP_CODE_LOADER_ID, null, this);
            case R.id.create_notification:
                createNotification();
                break;
        }
    }

    private void createNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.art_snow)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        System.out.println("Loader id " + id);
        CursorLoader cursorLoader = null;
        switch (id) {
            case GET_DATA_LOADER_ID:
                cursorLoader = new CursorLoader(
                        this,
                        StudentEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
                break;
            case GET_DATA_BY_NAME_LOADER_ID:
                cursorLoader = new CursorLoader(
                        this,
                        Uri.withAppendedPath(StudentEntry.CONTENT_URI, "name"),
                        null,
                        null,
                        new String[]{"Jacky"},
                        null);
                break;
            case GET_DATA_BY_ZIP_CODE_LOADER_ID:
                cursorLoader = new CursorLoader(
                        this,
                        Uri.withAppendedPath(StudentEntry.CONTENT_URI, "zipcode"),
                        null,
                        null,
                        new String[]{"396110"},
                        null);
                break;
        }
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cur) {

        System.out.println("MainActivity.onLoadFinished");
        System.out.println("Loader id  " + loader.getId());

//        listView.setAdapter(
//                new ListViewAdapter(this, studentArrayList));

        cursorAdapter.swapCursor(cur);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
