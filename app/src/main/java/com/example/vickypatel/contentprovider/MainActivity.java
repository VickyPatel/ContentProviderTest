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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.insert_data_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Student> studentsList = StudentUtilities.getStudentList();
                for (Student student :
                        studentsList) {
                    Uri uri = getApplicationContext().getContentResolver().insert(
                            StudentEntry.CONTENT_URI,
                            StudentUtilities.createStudentValues(student));
                    long studentId = ContentUris.parseId(uri);
                    Toast.makeText(getApplicationContext(), "Student with id " + studentId + " inserted", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button getButton = (Button) findViewById(R.id.get_data_button);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = getContentResolver().query(StudentEntry.CONTENT_URI,
                        null, null, null, null);
                ArrayList<Student> studentArrayList = new ArrayList<>();
                try {
                    cur.moveToFirst();
                    while (!cur.isAfterLast()) {
                        Student student = new Student();
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

            }
        });


    }


}
