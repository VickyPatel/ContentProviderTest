package com.example.vickypatel.contentprovider;

import android.content.ContentUris;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vickypatel.contentprovider.data.StudentContract;
import com.example.vickypatel.contentprovider.data.StudentContract.StudentEntry;
import com.example.vickypatel.contentprovider.data.StudentDBHelper;
import com.example.vickypatel.contentprovider.data.StudentUtilities;
import com.example.vickypatel.contentprovider.pojo.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setDataButton = (Button) findViewById(R.id.insert_data_button);
        Button getDataButton = (Button) findViewById(R.id.get_data_button);
        Button getDataByNameButton = (Button) findViewById(R.id.get_data_by_name_button);
        Button getDataByZIPCodeButton = (Button) findViewById(R.id.get_data_by_zip_code_button);

        setDataButton.setOnClickListener(this);
        getDataButton.setOnClickListener(this);
        getDataByNameButton.setOnClickListener(this);
        getDataByZIPCodeButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Cursor cur;
        ArrayList<Student> studentsList;

        switch (v.getId()) {
            case R.id.insert_data_button:
                studentsList = StudentUtilities.getStudentList();
                for (Student student :
                        studentsList) {
                    Uri uri = getApplicationContext().getContentResolver().insert(
                            StudentEntry.CONTENT_URI,
                            StudentUtilities.createStudentValues(student));
                    long studentId = ContentUris.parseId(uri);
                    Toast.makeText(getApplicationContext(), "Student with id " + studentId + " inserted", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.get_data_button:
                cur = getContentResolver().query(StudentEntry.CONTENT_URI,
                        null, null, null, null);
                ArrayList<Student> studentArrayList = new ArrayList<>();
                try {
                    cur.moveToFirst();
                    while (!cur.isAfterLast()) {
                        Student student = new Student();
                        student.setStudentId(
                                cur.getInt(
                                        cur.getColumnIndex(StudentEntry._ID))
                        );
                        student.setName(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_NAME)));
                        student.setSurname(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_SURNAME)));
                        student.setAddress(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_ADDRESS)));
                        student.setZipCode(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_ZIP_CODE)));
                        studentArrayList.add(student);
                        cur.moveToNext();
                    }

                } finally {
                    cur.close();
                }

                for (Student student :
                        studentArrayList) {
                    System.out.println(student.toString());
                }
                break;
            case R.id.get_data_by_name_button:
                cur = getContentResolver().query(
                        Uri.withAppendedPath(StudentEntry.CONTENT_URI, "name"),
                        null,
                        null,
                        new String[]{"Jacky"},
                        null);

                studentArrayList = new ArrayList<>();
                try {
                    cur.moveToFirst();
                    while (!cur.isAfterLast()) {
                        Student student = new Student();
                        student.setStudentId(
                                cur.getInt(
                                        cur.getColumnIndex(StudentEntry._ID)));
                        student.setName(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_NAME)));
                        student.setSurname(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_SURNAME)));
                        student.setAddress(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_ADDRESS)));
                        student.setZipCode(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_ZIP_CODE)));
                        studentArrayList.add(student);
                        cur.moveToNext();
                    }

                } finally {
                    cur.close();
                }

                for (Student student :
                        studentArrayList) {
                    System.out.println(student.toString());
                }
                break;
            case R.id.get_data_by_zip_code_button:
                cur = getContentResolver().query(
                        Uri.withAppendedPath(StudentEntry.CONTENT_URI, "zipcode"),
                        null,
                        null,
                        new String[]{"396110"},
                        null);

                studentArrayList = new ArrayList<>();
                try {
                    cur.moveToFirst();
                    while (!cur.isAfterLast()) {
                        Student student = new Student();
                        student.setStudentId(
                                cur.getInt(
                                        cur.getColumnIndex(StudentEntry._ID)));
                        student.setName(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_NAME)));
                        student.setSurname(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_SURNAME)));
                        student.setAddress(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_ADDRESS)));
                        student.setZipCode(
                                cur.getString(
                                        cur.getColumnIndex(StudentEntry.COLUMN_ZIP_CODE)));
                        studentArrayList.add(student);
                        cur.moveToNext();
                    }

                } finally {
                    cur.close();
                }

                for (Student student :
                        studentArrayList) {
                    System.out.println(student.toString());
                }
                break;
        }
    }
}
